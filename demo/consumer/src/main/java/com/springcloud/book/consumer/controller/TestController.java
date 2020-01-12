package com.springcloud.book.consumer.controller;

import com.springboot.cloud.common.core.util.UserContextHolder;
import com.springcloud.book.consumer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/test")
    public String getUser() throws Exception {
        System.out.println(UserContextHolder.getInstance().getUsername());
        return userService.getUser("spring");
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username) throws Exception {
        return userService.getUser(username);
    }

    @GetMapping("/add")
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        String result = restTemplate
                .getForObject("http://provider/add?a=" + a + "&b=" + b, String.class);
        System.out.println(result);
        return result;
    }
}