package com.springcloud.book.consumer.controller;

import com.springcloud.book.consumer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username) throws Exception{
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