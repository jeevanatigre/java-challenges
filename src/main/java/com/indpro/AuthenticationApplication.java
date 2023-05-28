package com.indpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EntityScan("com.indpro.entity")
@EnableJpaRepositories(basePackages = {"com.indpro.repository", "com.indpro.repositoryImpl"})
public class AuthenticationApplication {
		public static void main(String[] args) {
			SpringApplication.run(AuthenticationApplication.class, args);
		}
}
