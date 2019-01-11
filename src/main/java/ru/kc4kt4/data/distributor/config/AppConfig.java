package ru.kc4kt4.data.distributor.config;

import feign.Feign;
import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.kc4kt4.data.distributor.feign.BankDetailClient;


@Configuration
public class AppConfig {

    @Value("${distribution.bik.host}")
    private String host;

    @Bean
    BankDetailClient bankDetailClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .target(BankDetailClient.class, host);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
