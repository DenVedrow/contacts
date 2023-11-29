package org.example;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.Locale;
import java.util.Scanner;

@Configuration
@ComponentScan("org.example")
@PropertySource(value = "classpath:application.properties")
public class AppConfig {

    @Bean
    public static MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public Validator validator() {
        Locale.setDefault(Locale.ENGLISH);
        var validationFactory = Validation.buildDefaultValidatorFactory();
        return validationFactory.getValidator();
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
