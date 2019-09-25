package com.springcloud.book.consumer.config.hystrix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixThreadContextConfiguration {
	@Bean
	public SpringCloudHystrixConcurrencyStrategy springCloudHystrixConcurrencyStrategy() {
		return new SpringCloudHystrixConcurrencyStrategy();
	}
}