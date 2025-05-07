package com.tesh.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.tesh"})
@EntityScan( basePackages = {"com.tesh"})
@EnableJpaRepositories(basePackages = {"com.tesh"})
@PropertySource(value = "classpath:application.properties")
@SpringBootApplication
public class ProfileAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileAnalysisApplication.class, args);
	}

}
