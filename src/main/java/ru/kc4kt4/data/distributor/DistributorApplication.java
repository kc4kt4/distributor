package ru.kc4kt4.data.distributor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.kc4kt4.data.distributor"})
public class DistributorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributorApplication.class, args);
    }
}

