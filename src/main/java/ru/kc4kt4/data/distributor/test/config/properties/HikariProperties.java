package ru.kc4kt4.data.distributor.test.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties("distribution.bd.datasource.hikari")
public class HikariProperties {

    @NotNull
    private Integer minPoolSize;
    @NotNull
    private Integer maxPoolSize;
    @NotBlank
    private String preferredTestQuery;
}
