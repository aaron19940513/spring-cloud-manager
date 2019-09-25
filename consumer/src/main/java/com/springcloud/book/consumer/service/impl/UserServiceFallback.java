package com.springcloud.book.consumer.service.impl;


import com.springcloud.book.consumer.service.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements IUserService {
	/**
	  * 出错则调用该方法返回友好错误
	  * @param username
	  * @return
	  */
	public String getUser(String username){
		return "The user does not exist in this system, please confirm username";
	}
}
