package com.datispars.bpmservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "another-service")
public interface AnotherFeignClient {

    @GetMapping("name")
    String name();
}