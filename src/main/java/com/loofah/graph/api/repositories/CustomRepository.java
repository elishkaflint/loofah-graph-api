package com.loofah.graph.api.repositories;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface CustomRepository {

    <T> List<T> findWithQuery(Query query, Class<T> tClass);

}
