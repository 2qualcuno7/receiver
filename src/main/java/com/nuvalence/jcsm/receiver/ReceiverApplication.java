package com.nuvalence.jcsm.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReceiverApplication {

	@GetMapping("/poc")
	public String home() {
		return "Test endpoint GKE POC";
	}
	public static void main(String[] args) {
		SpringApplication.run(ReceiverApplication.class, args);
	}

}
