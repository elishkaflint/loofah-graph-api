package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillsRepository extends MongoRepository<Skill, String> {
}
