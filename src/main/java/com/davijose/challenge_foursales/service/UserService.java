package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return  userRepository.save(user);
    }
    public User findByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow(() -> new BadCredentialsException("Usuário ou senha incorreto."));
    }
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
    public Optional<User> findById(UUID id){
        return  userRepository.findById(id);
    }
}
