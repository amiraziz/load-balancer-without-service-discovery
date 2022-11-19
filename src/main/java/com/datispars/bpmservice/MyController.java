package com.datispars.bpmservice;

import com.datispars.bpmservice.feign.AnotherFeignClient;
import com.datispars.bpmservice.feign.GreetingFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.datispars.bpmservice.config.WebClientConfig.ANOTHER_SERVICE;
import static com.datispars.bpmservice.config.WebClientConfig.GREETING_SERVICE;

@RestController
@Slf4j
public class MyController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GreetingFeignClient greetingFeignClient;
    @Autowired
    private AnotherFeignClient anotherFeignClient;

    /*usable in 'direct' profile*/
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("greeting")
    public void greeting() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(GREETING_SERVICE);
        String url = serviceInstance.getHost() + ':' + serviceInstance.getPort();
        String body = restTemplate.getForEntity("http://" + url + "/name", String.class).getBody();
        log.info(body);
    }

    @GetMapping("another")
    public void another() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(ANOTHER_SERVICE);
        String url = serviceInstance.getHost() + ':' + serviceInstance.getPort();
        String body = restTemplate.getForEntity("http://" + url + "/name", String.class).getBody();
        log.info(body);
    }

    @GetMapping("greeting-feign")
    public void name_feign() {
        String out = greetingFeignClient.name();
        log.info(out);
    }

    @GetMapping("another-feign")
    public void name_feign2() {
        String out = anotherFeignClient.name();
        log.info(out);
    }
}