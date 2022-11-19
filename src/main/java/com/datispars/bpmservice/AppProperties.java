package com.datispars.bpmservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private Map<String, List<String>> clients;

    public Map<String, List<String>> getClients() {
        return clients;
    }

    public void setClients(Map<String, List<String>> clients) {
        this.clients = clients;
    }
}