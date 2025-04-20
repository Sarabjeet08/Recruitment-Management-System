// Main application class - Entry point of the Spring Boot application
// Demonstrates: 
// - Spring Boot Auto-configuration (@SpringBootApplication)
// - Main method as application entry point
// - Spring Boot's convention over configuration principle
package com.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class RecruitmentSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentSystemApplication.class, args);
    }
}
