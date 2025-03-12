package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    private User save(User user){
        return  userRepository.save(user);
    }
}
