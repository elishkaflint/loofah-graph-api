package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.database.Craft;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CraftRepository extends MongoRepository<Craft, String> {
}
