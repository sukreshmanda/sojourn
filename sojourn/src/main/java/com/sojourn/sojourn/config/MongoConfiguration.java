package com.sojourn.sojourn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.sojourn.sojourn")
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "app";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
