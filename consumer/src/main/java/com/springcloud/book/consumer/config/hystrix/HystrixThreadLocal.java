package com.springcloud.book.consumer.config.hystrix;

public class HystrixThreadLocal {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
}