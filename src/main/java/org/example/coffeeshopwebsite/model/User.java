package org.example.coffeeshopwebsite.model;

public class User {
    private int userId;
    private int roleId;
    private String username;
    private String password;

    public User() {
    }

    public User(int userId, int roleId, String username, String password) {
        this.userId = userId;
        this.roleId = roleId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
