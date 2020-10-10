package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.database.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GradeRepository extends MongoRepository<Grade, String> {
}
