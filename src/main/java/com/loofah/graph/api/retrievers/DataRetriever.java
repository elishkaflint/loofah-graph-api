package com.loofah.graph.api.retrievers;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;

import java.util.List;

public interface DataRetriever {

    Skill getSkillById(String id);

    List<Skill> getSkillWithFilter(SkillFilter skillFilter);

    Grade getGradeById(String id);

    List<Grade> getAllGrades();

    Craft getCraftById(String id);

    List<Craft> getAllCrafts();

    Category getCategoryById(String id);

    List<Category> getAllCategories();

}
