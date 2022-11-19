package com.datispars.bpmservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "greeting-service")
public interface GreetingFeignClient {

    @GetMapping("name")
    String name();
}