package com.example.supakiassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;

@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = {"com.example.supakiassignment.repository.*"})
//@EntityScan("com.example.supakiassignment.*")
public class SupakiassignmentApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(SupakiassignmentApplication.class, args);
	}

}
