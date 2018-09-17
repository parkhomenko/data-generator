package com.data.generator;

import com.data.generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("usersLoader")
public class UserDataLoader {

    private final AnnotationConfigApplicationContext applicationContext;
    private final UserService userService;

    @Autowired
    public UserDataLoader(AnnotationConfigApplicationContext applicationContext, UserService userService) {
        this.applicationContext = applicationContext;
        this.userService = userService;
    }

    @Bean
    CommandLineRunner runner () {
        return args -> {
            userService.uploadUsers();

            int code = SpringApplication.exit(applicationContext);
            System.exit(code);
        };
    }
}
