package com.springcloud.book.provider.controller;

import javax.servlet.http.HttpServletRequest;

import com.springcloud.book.provider.model.CachedUser;
import com.springcloud.book.provider.model.User;
import com.springcloud.book.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getUser")
    public String getUser(@RequestParam String username) throws Exception {
        return userService.getUser(username);
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public CachedUser getUser(@PathVariable("id") Integer id) {
        System.out.println("ProviderController, id is" + id);
        if (id == 1) {
            return new CachedUser("Toy", "123456", 10);
        } else if (id == 2) {
            return new CachedUser("Sky", "000000", 20);
        }
        return new CachedUser("San", "543210", 30);
    }

    @GetMapping("/add")
    public String add(Integer a, Integer b, HttpServletRequest request) {
        return " From Port: " + request.getServerPort() + ", Result: " + (a + b);

    }

    @GetMapping("/div")
    public Integer div(Integer a, Integer b){
        return a / b;
    }
}
