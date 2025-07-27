package io.nicolau.finances_gateway.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UrlProperties.class)
public class UrlConfiguration {
}
