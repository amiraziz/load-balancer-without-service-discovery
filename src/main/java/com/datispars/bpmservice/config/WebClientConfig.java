package com.datispars.bpmservice.config;

import com.datispars.bpmservice.AppProperties;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebClientConfig {
    public static final String GREETING_SERVICE = "greeting-service";
    public static final String ANOTHER_SERVICE = "another-service";

    @Bean
    @Profile("feign")
    ServiceInstanceListSupplier serviceInstanceListSupplier(AppProperties appProperties) {
        return new AppInstanceSupplier("", appProperties);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}