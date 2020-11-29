package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.database.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {

    Optional<Category> findByTitle(String title);

}
