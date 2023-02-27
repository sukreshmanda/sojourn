package com.sojourn.sojourn.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "com.sojourn.sojourn")
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Override
    protected String getDatabaseName() {
        return "app";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Override
    @Bean
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(List.of(new ServerAddress(mongoHost, mongoPort))))
                .build();
        return MongoClients.create(settings);
    }
}
