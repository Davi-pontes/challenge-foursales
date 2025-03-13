package com.davijose.challenge_foursales.config;

import com.davijose.challenge_foursales.domain.user.RoleUser;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findByName("user").isEmpty()) {
            var user = new User();
            user.setName("user");
            user.setEmail("user@user.com");
            user.setPassword(passwordEncoder.encode("5678"));
            user.setRoleUser(RoleUser.USER);

            userService.save(user);

            System.out.println("Usuário user criado com sucesso.");
        } else {
            System.out.println("Usuário user já existe.");
        }
    }
}
