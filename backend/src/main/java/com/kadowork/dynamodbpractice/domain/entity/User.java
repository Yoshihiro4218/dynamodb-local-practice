package com.kadowork.dynamodbpractice.domain.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class User {
    @Getter(AccessLevel.NONE)
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
