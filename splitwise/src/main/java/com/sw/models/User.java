package com.sw.models;

/**
 * @author lakshay
 * @since 26/12/21
 */
public class User {
    String userId;
    String name;
    String email;
    int mobile;

    public User(String userId, String name, String email, int mobile) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
