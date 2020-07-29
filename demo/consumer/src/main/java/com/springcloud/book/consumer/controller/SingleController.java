package com.springcloud.book.consumer.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sam Gao
 * @date 07/29/20 15:24
 */
@RestController("/single")
public class SingleController {
    @GetMapping
    public String single() {
        return "single test success!";
    }
}
