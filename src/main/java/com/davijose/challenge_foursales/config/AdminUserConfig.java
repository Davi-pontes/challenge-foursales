package com.davijose.challenge_foursales.config;

import com.davijose.challenge_foursales.domain.user.RoleUser;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findByName("admin").isEmpty()) {
            var user = new User();
            user.setName("admin");
            user.setEmail("admin@admin.com");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRoleUser(RoleUser.ADMIN);

            userService.save(user);

            System.out.println("Usuário admin criado com sucesso.");
        } else {
            System.out.println("Usuário admin já existe.");
        }
    }
}