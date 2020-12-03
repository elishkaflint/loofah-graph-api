package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.retrievers.DataRetriever;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SkillService {

    private final DataRetriever dataRetriever;
    private final CategoryService categoryService;
    private final GradeService gradeService;

    @Autowired
    public SkillService(DataRetriever dataRetriever, CategoryService categoryService, GradeService gradeService) {
        this.dataRetriever = dataRetriever;
        this.categoryService = categoryService;
        this.gradeService = gradeService;
    }

    public SkillDTO getById(String id) {
        Skill skill = dataRetriever.getSkillById(id).orElseThrow(() -> new DataNotFoundException("No skill found with id ["+id+"]"));
        return transformToSkillDTO(skill);
    }


    // This method will return an empty list if no skills match the current filter.
    // If the filter returns skills without a grade or category id,
    // a GraphQlException message will be returned in the response error message.
    // If the filter returns skills with grade or category ids which do not return grades or categories respectively,
    // a DataNotFoundException message will be returned in the response error message.
    public List<SkillDTO> getWithFilter(SkillFilter skillFilter) {
        List<Skill> skills = dataRetriever.getSkillWithFilter(skillFilter);
        return skills.stream().map(this::transformToSkillDTO).collect(Collectors.toList());
    }

    private SkillDTO transformToSkillDTO(Skill skill) {
        Category category = retrieveCategoryForSkill(skill);
        Grade grade = retrieveGradeForSkill(skill);
        return new SkillDTO(skill, category, grade);
    }

    private Category retrieveCategoryForSkill(Skill skill) {
        if (skill.getCategoryId() == null) {
            throw new GraphQLException("categoryId is null for skill with id ["+skill.getId()+"]");
        }
        return categoryService.getById(skill.getCategoryId());
    }

    private Grade retrieveGradeForSkill(Skill skill) {
        if (skill.getGradeId() == null) {
            throw new GraphQLException("gradeId is null for skill with id ["+skill.getId()+"]");
        }
        return gradeService.getById(skill.getGradeId());
    }

}
