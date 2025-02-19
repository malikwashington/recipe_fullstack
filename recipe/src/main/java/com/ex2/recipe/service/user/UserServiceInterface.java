package com.ex2.recipe.service.user;

import com.ex2.recipe.model.User;

public interface UserServiceInterface {
    User registerUser(User user);

    String findByUsername(String username);
}
