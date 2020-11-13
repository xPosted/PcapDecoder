package com.configuration;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

//    @EventListener
    public void customListener(ApplicationEvent event) {
        System.out.println(event);
    }

    @Bean
    public Integer sampleInt() {
        return 10;
    }
}
