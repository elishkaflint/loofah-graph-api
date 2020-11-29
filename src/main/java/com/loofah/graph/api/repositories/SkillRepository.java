package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.database.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SkillRepository extends MongoRepository<Skill, String>, CustomRepository {



}
