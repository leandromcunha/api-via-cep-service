package br.com.cs.feign.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StringWebApp {

	public static void main(String[] args) {
		SpringApplication.run(StringWebApp.class, args);
	}
}