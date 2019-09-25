package com.springcloud.book.consumer.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.book.consumer.service.exception.PSFallbackBadRequestExpcetion;
import com.springcloud.book.consumer.service.exception.PSFallbackOtherExpcetion;
import com.springcloud.book.consumer.service.exception.ProviderServiceCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class ExceptionController {
    private static Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @GetMapping("/getProviderServiceCommand")
    public String providerServiceCommand() {
        String result = new ProviderServiceCommand("World").execute();
        return result;
    }


    @GetMapping("/getPSFallbackBadRequestExpcetion")
    public String providerServiceFallback() {
        String result = new PSFallbackBadRequestExpcetion().execute();
        return result;
    }


    @GetMapping("/getPSFallbackOtherExpcetion")
    public String pSFallbackOtherExpcetion() {
        String result = new PSFallbackOtherExpcetion().execute();
        return result;
    }

    @GetMapping("/getFallbackMethodTest")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getFallbackMethodTest(@RequestParam String id) {
        throw new RuntimeException("getFallbackMethodTest failed");
    }

    public String fallback(String id, Throwable throwable) {
        log.error(throwable.getMessage());
        return "this is fallback message";
    }

}
