package com.devtalk.carparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CarParkingApplication {

    
    public static void main(String[] args) {
        SpringApplication.run(CarParkingApplication.class, args);
    }

}
