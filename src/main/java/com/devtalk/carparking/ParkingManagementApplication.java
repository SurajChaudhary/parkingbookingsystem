package com.devtalk.carparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
//@EnableJpaRepositories
public class ParkingManagementApplication {


    public static void main(String[] args) {
        SpringApplication.run(ParkingManagementApplication.class, args);
    }

}
