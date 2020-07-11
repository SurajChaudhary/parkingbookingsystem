package com.devtalk.carparking.configuration.jwt;

import com.google.common.net.HttpHeaders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application.jwt")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer expirationDays;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
