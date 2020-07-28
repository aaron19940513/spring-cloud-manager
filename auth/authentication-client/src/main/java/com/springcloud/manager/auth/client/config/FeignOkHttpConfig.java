package com.springcloud.manager.auth.client.config;

import java.util.concurrent.TimeUnit;

import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 需要修改成OKHTTP的客户端，需要在配置文件增加
 * feign.httpclient.enabled=false
 * feign.okhttp.enabled=true
 */
@AutoConfigureBefore(FeignAutoConfiguration.class)
@Configuration
@ConditionalOnClass(Feign.class)
public class FeignOkHttpConfig {

    private int feignOkHttpReadTimeout = 60;
    private int feignConnectTimeout = 60;
    private int feignWriteTimeout = 120;

    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
            .readTimeout(feignOkHttpReadTimeout, TimeUnit.SECONDS)
            .connectTimeout(feignConnectTimeout, TimeUnit.SECONDS)
            .writeTimeout(feignWriteTimeout, TimeUnit.SECONDS)
            .build();
    }
}
