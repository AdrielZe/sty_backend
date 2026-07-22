package com.example.sty_backend_def;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Import the required packages

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

@SpringBootApplication
public class StyBackendDefApplication {

	public static void main(String[] args) {
		SpringApplication.run(StyBackendDefApplication.class, args);
	}

}
