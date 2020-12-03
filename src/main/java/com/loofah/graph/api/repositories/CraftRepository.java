package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.database.Craft;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CraftRepository extends MongoRepository<Craft, String> {

    Optional<Craft> findByTitle(String title);

}
