package com.my.oauth.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@EnableOAuth2Sso
@SpringBootApplication
public class MyOauth2ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOauth2ClientApplication.class, args);
    }

}
