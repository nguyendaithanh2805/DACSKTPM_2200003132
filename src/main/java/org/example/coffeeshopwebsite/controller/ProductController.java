package org.example.coffeeshopwebsite.controller;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.service.CategoryService;
import org.example.coffeeshopwebsite.service.FileUploadService;
import org.example.coffeeshopwebsite.service.ProductService;
import org.example.coffeeshopwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final FileUploadService fileUploadService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public ProductController(FileUploadService fileUploadService, ProductService productService, CategoryService categoryService, UserService userService) {
        this.fileUploadService = fileUploadService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    // READ
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        // Trich xuat ten danh muc san pham tuong ung voi tung san pham
        List<String> categoryNames = products.stream()
                .map(product -> categoryService.findCategoryById(product.getCategoryId()).getName())
                .toList();
        model.addAttribute("products", products);
        model.addAttribute("categoryName", categoryNames);
        return "admin/products";
    }

    // CREATE
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/add-product";
    }

    @PostMapping("/add")
    public String addProduct(Product product, @ModelAttribute("categoryId") int categoryId, @RequestParam("imageFile") MultipartFile file) {
        product.setCategoryId(categoryId);
        fileUploadService.handleImageUpload(product, file);
        User user = userService.getCurrentUser();
        productService.saveProduct(product, user);
        return "redirect:/admin/products";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/edit-product";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, Product product, @ModelAttribute("categoryId") int categoryId, @RequestParam("imageFile") MultipartFile file) {
        product.setProductId(id);
        product.setCategoryId(categoryId);
        User user = userService.getCurrentUser();
        fileUploadService.handleImageUpload(product, file);
        productService.updateProduct(product, user);
        return "redirect:/admin/products";
    }

    // DELETE
    @GetMapping("/confirm-delete/{id}")
    public String showDeleteConfirmationPage(@PathVariable int id, Model model) {
        Product product = productService.findProductById(id);
        if (product == null) return "redirect:/admin/products";
        model.addAttribute("entityName", "product");
        model.addAttribute("entityDisplayName", product.getName());
        model.addAttribute("entityId", product.getProductId());
        model.addAttribute("deleteUrl", "/admin/products/delete");
        return "admin/delete";
    }

    @GetMapping("/delete")
    public String deleteConfirm(@RequestParam("id") int id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }
}