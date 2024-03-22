package br.com.rochadev.fit_progress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FitProgressApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitProgressApplication.class, args);
	}

}
