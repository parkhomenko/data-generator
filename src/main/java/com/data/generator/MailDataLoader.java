package com.data.generator;

import com.data.generator.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mailsLoader")
public class MailDataLoader {

    private final AnnotationConfigApplicationContext applicationContext;
    private final MailService mailService;

    @Autowired
    public MailDataLoader(AnnotationConfigApplicationContext applicationContext, MailService mailService) {
        this.applicationContext = applicationContext;
        this.mailService = mailService;
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            mailService.uploadMails();

            int code = SpringApplication.exit(applicationContext);
            System.exit(code);
        };
    }
}
