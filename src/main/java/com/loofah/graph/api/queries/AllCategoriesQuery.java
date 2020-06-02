package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.CategoryWithSkills;
import com.loofah.graph.api.repositories.CategoryRepository;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllCategoriesQuery implements DataFetcher<List<CategoryWithSkills>> {

    private CategoryRepository categoryRepository;
    private SkillRepository skillRepository;

    @Autowired
    public AllCategoriesQuery(CategoryRepository categoryRepository, SkillRepository skillRepository) {
        this.categoryRepository = categoryRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public List<CategoryWithSkills> get(DataFetchingEnvironment dataFetchingEnvironment) {
        List<Category> categories = categoryRepository.findAll();
        List<Skill> skills = skillRepository.findAll();

        return categories.stream()
                .map(category -> {
                    List<Skill> skillsInThisCategory = getSkillsFilteredByCategoryId(skills, category.getId());
                    return new CategoryWithSkills(category.getId(), category.getTitle(), skillsInThisCategory);
                }).collect(Collectors.toList());
    }

    private List<Skill> getSkillsFilteredByCategoryId(List<Skill> skills, int categoryId) {
        return skills
                .stream()
                .filter(skill -> skill.getCategoryId() == categoryId)
                .collect(Collectors.toList());
    }

}
