package com.springcloud.book.consumer.service;

import com.springcloud.book.consumer.service.impl.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider", fallback = UserServiceFallback.class)
public interface IUserService {
	
	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username);
    
}
