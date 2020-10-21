package com.loofah.graph.api.repositories.impl;

import com.loofah.graph.api.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomRepositoryImpl implements CustomRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public CustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public <T> List<T> findWithQuery(Query query, Class<T> tClass) {
        return mongoTemplate.find(query, tClass);
    }

}
