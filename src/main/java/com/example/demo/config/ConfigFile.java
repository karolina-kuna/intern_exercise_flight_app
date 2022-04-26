package com.example.demo.config;

import com.example.demo.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo.services")
public class ConfigFile {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CalculateWeightService calculateWeightService(){
        return new CalculateWeightServiceImpl();
    }

    @Bean
    public DataParsingService dataParsingService(){
        return new DataParsingServiceImpl(objectMapper());
    }

    @Bean
    public DataFilterService dataFilterService(){
        return new DataFilterServiceImpl();
    }

    @Bean
    public CalculateAmountService calculateBaggageAmountService(){
        return new CalculateAmountServiceImpl() ;
    }


}
