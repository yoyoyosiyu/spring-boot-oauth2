package com.huyutech.client1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
@SpringBootApplication
public class Client1Application {

//    @GetMapping("/user")
//    public String user(Authentication user) {
//        return "hello";
//    }

    public static void main(String[] args) {
        SpringApplication.run(Client1Application.class, args);
    }
}
