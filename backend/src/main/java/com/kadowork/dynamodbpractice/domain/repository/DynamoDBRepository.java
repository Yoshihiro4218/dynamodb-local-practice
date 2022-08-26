package com.kadowork.dynamodbpractice.domain.repository;

import lombok.*;
import org.springframework.stereotype.*;
import software.amazon.awssdk.enhanced.dynamodb.*;

import java.util.*;

import static software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.keyEqualTo;

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

    // index で引くとき
    public <T> T getByXXX(int key, Class<T> recordClass) {
        String tableName = recordClass.getSimpleName().toLowerCase(Locale.ENGLISH);
        String indexName = recordClass.getSimpleName().toLowerCase(Locale.ENGLISH) + "_index"; // 適当
        DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(recordClass));
        DynamoDbIndex<T> index = table.index(indexName);
        return index.query(r -> r.queryConditional(keyEqualTo(k -> k.partitionValue(key))))
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("exception")) // 適当
                    .items().get(0);
    }
}
