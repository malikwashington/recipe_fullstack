package com.ex2.recipe.service.user;

import com.ex2.recipe.model.User;
import com.ex2.recipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{
    private final UserRepository userRepository;


    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return user.getUsername();
        }
        return "";
    }
}
