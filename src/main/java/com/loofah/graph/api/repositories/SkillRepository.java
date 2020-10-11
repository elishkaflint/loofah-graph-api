package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.database.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SkillRepository extends MongoRepository<Skill, String> {

    List<Skill> findByCategoryId(String categoryId);

    List<Skill> findByGradeId(String gradeId);

}
