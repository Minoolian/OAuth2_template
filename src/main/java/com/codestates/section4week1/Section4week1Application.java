package com.codestates.section4week1;

import com.codestates.section4week1.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Section4week1Application {

    public static void main(String[] args) {
        SpringApplication.run(Section4week1Application.class, args);
    }

}
