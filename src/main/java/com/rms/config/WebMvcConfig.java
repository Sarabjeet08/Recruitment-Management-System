// WebMvcConfig - Spring MVC configuration class
// Demonstrates:
// - Spring Configuration (@Configuration)
// - WebMvcConfigurer interface implementation
// - Resource handling configuration
// - File system resource mapping
// - Dynamic path resolution
package com.rms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // Configure static resource handling
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map URL pattern /uploads/** to filesystem uploads directory
        // Uses dynamic path resolution based on application working directory
        registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
    }
}
