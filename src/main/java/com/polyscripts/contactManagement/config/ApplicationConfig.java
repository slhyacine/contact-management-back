package com.polyscripts.contactManagement.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class ApplicationConfig {

    private String frontUrl;

}
