package com.configuration;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component
public class CustomAppListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(applicationEvent);
    }
}
