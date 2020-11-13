package com;

import com.configuration.CustomAppListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String args[]) {
      //  SpringApplication.run(TestApp.class,args).addApplicationListener(new CustomAppListener());
        new SpringApplicationBuilder().sources(Application.class).listeners(new CustomAppListener()).run(args);
    }
}
