package com.springcloud.book.consumer.config;

import com.springcloud.manager.common.web.interceptor.FeignUserContextInterceptor;
import com.springcloud.book.consumer.interceptor.FeignRequestInterceptor;
import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreAutoConfiguration {

    @Bean
    public FeignRequestInterceptor charlesRequestInterceptor() {
        return new FeignRequestInterceptor();

    }

    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的用户上下文信息
     */
    @Bean
    @ConditionalOnClass(Feign.class)
    public FeignUserContextInterceptor feignUserContextInterceptor() {
        return new FeignUserContextInterceptor();
    }
}
