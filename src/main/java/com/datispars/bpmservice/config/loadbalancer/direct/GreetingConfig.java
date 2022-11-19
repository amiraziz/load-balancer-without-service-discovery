package com.datispars.bpmservice.config.loadbalancer.direct;

import com.datispars.bpmservice.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.datispars.bpmservice.config.WebClientConfig.GREETING_SERVICE;

@Configuration
@Profile("direct")
public class GreetingConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new AppInstanceSupplier(GREETING_SERVICE, appProperties);
    }
}