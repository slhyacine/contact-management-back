package com.polyscripts.contactManagement;

import com.polyscripts.contactManagement.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.polyscripts.contactManagement.security.config.SecurityProperties;

@SpringBootApplication()
@EnableConfigurationProperties({
		SecurityProperties.class,
		ApplicationConfig.class
})
public class ContactManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApplication.class, args);
	}

}
