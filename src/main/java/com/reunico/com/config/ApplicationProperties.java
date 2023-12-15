package com.reunico.com.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
@Setter
@Getter
public class ApplicationProperties {
    private String websiteUrl;
    private String crmUrl;
}
