package com.sesc.unistudycircle.student_service.services;

import org.apache.hc.core5.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .build();
    }
}
