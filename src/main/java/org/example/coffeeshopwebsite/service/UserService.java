package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.User;

public interface UserService {
    void addUser(User user);
    User getCurrentUser();

    User getUserIdAdmin();
}
