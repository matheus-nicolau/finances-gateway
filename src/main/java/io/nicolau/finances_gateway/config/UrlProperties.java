package io.nicolau.finances_gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "urls")
public record UrlProperties(
        String coreClient
) { }

