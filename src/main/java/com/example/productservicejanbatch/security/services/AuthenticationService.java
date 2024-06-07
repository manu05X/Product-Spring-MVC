package com.example.productservicejanbatch.security.services;

import com.example.productservicejanbatch.security.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {
        private RestTemplate restTemplate;

    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean authenticate(String token) {
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity(("http://localhost:9000/users/validate/" + token),
                null, User.class);

        if (userResponseEntity.getBody() != null) {
            return true;
        }
        return false;
    }

/*
 Client --call-to-> User Service (Auth Server)
 give Token to ----> Client
 Client will call ---> Product Service with Token ,
 PeoductService to  validate will call ---> UserService /validate endpoint
 */

}
