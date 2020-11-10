package com.loofah.graph.api.retrievers;

import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;

import java.util.List;
import java.util.Optional;

public interface DataRetriever {

    Optional<Skill> getSkillById(String id);

    List<Skill> getSkillWithFilter(SkillFilter skillFilter);

    Optional<Grade> getGradeById(String id);

    List<Grade> getAllGrades();

    Optional<Craft> getCraftById(String id);

    List<Craft> getAllCrafts();

    Optional<Category> getCategoryById(String id);

    List<Category> getAllCategories();

}
