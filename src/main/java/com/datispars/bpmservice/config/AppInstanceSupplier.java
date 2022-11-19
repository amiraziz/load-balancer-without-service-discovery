package com.datispars.bpmservice.config;

import com.datispars.bpmservice.AppProperties;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.RequestDataContext;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppInstanceSupplier implements ServiceInstanceListSupplier {
    private final String serviceName;
    private final Map<String, List<String>> clients;

    public AppInstanceSupplier(String serviceName, AppProperties appProperties) {
        this.serviceName = serviceName;
        this.clients = appProperties.getClients();
    }

    @Override
    public String getServiceId() {
        return serviceName;
    }

    @Override
    public Flux<List<ServiceInstance>> get(Request request) {
        List<ServiceInstance> list = new ArrayList<>();
        String authority = ((RequestDataContext) request.getContext()).getClientRequest().getUrl().getAuthority();
        List<String> serviceAddresses = clients.get(authority);

        int i = 1;
        for (String serviceAddress : serviceAddresses) {
            String[] url = serviceAddress.split(":");

            list.add(new DefaultServiceInstance(authority + i, url[0], url[0], Integer.parseInt(url[1]), false));
            i++;
        }
        return Flux.just(list);
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return null;
    }
}