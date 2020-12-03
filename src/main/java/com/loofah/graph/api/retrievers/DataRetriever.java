package com.loofah.graph.api.retrievers;

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

    Optional<Grade> getGradeByTitle(String title);

    List<Grade> getAllGrades();

    Optional<Craft> getCraftById(String id);

    Optional<Craft> getCraftByTitle(String title);

    List<Craft> getAllCrafts();

    Optional<Category> getCategoryById(String id);

    Optional<Category> getCategoryByTitle(String title);

    List<Category> getAllCategories();

}
