package com.movieApp.moviesService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AuthService", url = "http://localhost:8080/api/v1/auth/validate")
public interface AuthClient {

    @GetMapping
    ResponseEntity<String> validate(@RequestHeader("Authorization") String token);

}
