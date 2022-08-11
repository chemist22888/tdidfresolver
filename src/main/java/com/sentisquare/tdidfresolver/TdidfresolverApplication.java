package com.sentisquare.tdidfresolver;

import com.sentisquare.tdidfresolver.service.WordServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class TdidfresolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdidfresolverApplication.class, args);
    }

}
