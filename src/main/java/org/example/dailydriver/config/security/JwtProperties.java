package org.example.dailydriver.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application.jwt")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtProperties {
    private String secret;
    private Long accessTokenExpire;
    private Long refreshTokenExpire;
}
