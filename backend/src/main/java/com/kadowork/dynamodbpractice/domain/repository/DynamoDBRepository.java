package com.kadowork.dynamodbpractice.domain.repository;

import lombok.*;
import org.springframework.stereotype.*;
import software.amazon.awssdk.enhanced.dynamodb.*;

import java.util.*;

@Repository
@AllArgsConstructor
public class DynamoDBRepository {
    private final DynamoDbEnhancedClient client;

    public <T> void save(T record, Class<T> recordClass) {
        String tableName = recordClass.getSimpleName().toLowerCase(Locale.ENGLISH);
        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(recordClass));
        table.putItem(record);
    }

    public <T> T get(String key, Class<T> recordClass) {
        String tableName = recordClass.getSimpleName().toLowerCase(Locale.ENGLISH);
        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(recordClass));
        return table.getItem(Key.builder().partitionValue(key).build());
    }

    public <T> T get(int key, Class<T> recordClass) {
        String tableName = recordClass.getSimpleName().toLowerCase(Locale.ENGLISH);
        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(recordClass));
        return table.getItem(Key.builder().partitionValue(key).build());
    }

    public <T> List<T> scan(Class<T> recordClass) {
        String tableName = recordClass.getSimpleName().toLowerCase(Locale.ENGLISH);
        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(recordClass));
        return table.scan().items().stream().toList();
    }
}
