package com.example.demo;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DemoApplication.class);
        application.setDefaultProperties(Collections.singletonMap("server.port", "9090"));
        application.run(args);
	}

	public void run(String... args) throws Exception {
	}

}
