package com.datispars.bpmservice.config.loadbalancer.direct;

import com.datispars.bpmservice.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.datispars.bpmservice.config.WebClientConfig.ANOTHER_SERVICE;
import static com.datispars.bpmservice.config.WebClientConfig.GREETING_SERVICE;

@Configuration
@LoadBalancerClients({
        @LoadBalancerClient(name = GREETING_SERVICE, configuration = GreetingConfig.class),
        @LoadBalancerClient(name = ANOTHER_SERVICE, configuration = AnotherApiConfig.class),
})
@Profile("direct")
public class AnotherApiConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    ServiceInstanceListSupplier serviceInstanceListSupplier2() {
        return new AppInstanceSupplier(ANOTHER_SERVICE, appProperties);
    }
}