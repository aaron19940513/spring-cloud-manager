package com.springcloud.manager.auth.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class Oauth2AuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthenticationApplication.class, args);
    }
}