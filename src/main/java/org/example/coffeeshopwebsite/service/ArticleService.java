package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Article;
import org.example.coffeeshopwebsite.model.User;

import java.util.List;

public interface ArticleService {
    void saveArticle(Article article);

    List<Article> getAllArticles();

    Article getArticleById(int id);

    void deleteArticleById(int id);

    void updateArticle(Article article, User user);
}
