package com.kadowork.dynamodbpractice.config;

import org.springframework.context.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.*;

import java.net.*;

@Configuration
public class DynamoDBConfig {
    private final String dynamoDbEndPointUrl;

    public DynamoDBConfig() {
        this.dynamoDbEndPointUrl = "http://localhost:4000";
    }

    @Bean
    public DynamoDbClient getDynamoDbClient() {
        return DynamoDbClient.builder()
                             .endpointOverride(URI.create(dynamoDbEndPointUrl))
                             .build();
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                                     .dynamoDbClient(getDynamoDbClient())
                                     .build();
    }
}