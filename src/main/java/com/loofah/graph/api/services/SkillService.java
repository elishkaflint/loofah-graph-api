package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.dto.GroupedSkillDTO;
import com.loofah.graph.api.models.dto.SkillDTO;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.retrievers.DataRetriever;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
public class SkillService {

    private final DataRetriever dataRetriever;
    private final CategoryService categoryService;
    private final GradeService gradeService;
    private final CraftService craftService;

    @Autowired
    public SkillService(DataRetriever dataRetriever, CategoryService categoryService, GradeService gradeService, CraftService craftService) {
        this.dataRetriever = dataRetriever;
        this.categoryService = categoryService;
        this.gradeService = gradeService;
        this.craftService = craftService;
    }

    public SkillDTO getById(String id) {
        Skill skill = dataRetriever.getSkillById(id).orElseThrow(() -> new DataNotFoundException("No skill found with id ["+id+"]"));
        return transformToSkillDTO(skill);
    }

    // This method will return an empty list if no skills match the given filter.
    // If the filter returns skills without a grade / category / craft id,
    // a GraphQlException message will be returned in the response error message.
    // If the filter returns skills with grade / category / craft ids which do not return grades categories or crafts respectively,
    // a DataNotFoundException message will be returned in the response error message.
    public List<SkillDTO> getWithFilter(SkillFilter skillFilter) {
        List<Skill> skills = dataRetriever.getSkillWithFilter(skillFilter);
        return skills.stream().map(this::transformToSkillDTO).collect(Collectors.toList());
    }

    public List<GroupedSkillDTO> getGroupedWithFilter(SkillFilter skillFilter) {

        List<Skill> skills = dataRetriever.getSkillWithFilter(skillFilter);

        Map<String, List<Skill>> skillsGroupedByTitle = skills.stream().collect(groupingBy(Skill::getTitle));

        return skillsGroupedByTitle.entrySet().stream().map(entry -> {
            String title = entry.getKey();
            List<SkillDTO> skills1 = entry.getValue().stream().map(this::transformToSkillDTO).collect(Collectors.toList());
            return new GroupedSkillDTO(title, skills1);
        }).collect(Collectors.toList());
    }

    private SkillDTO transformToSkillDTO(Skill skill) {
        Category category = retrieveCategoryForSkill(skill);
        Grade grade = retrieveGradeForSkill(skill);
        List<Craft> crafts = retrieveCraftsForSkill(skill);
        return new SkillDTO(skill, category, grade, crafts);
    }

    private List<Craft> retrieveCraftsForSkill(Skill skill) {
        if (skill.getCraftTitles().isEmpty()) {
            throw new GraphQLException("craftTitle is empty for skill with id ["+skill.getTitle()+"]");
        }
        return skill.getCraftTitles().stream().map(craftService::getByTitle).collect(Collectors.toList());
    }

    private Category retrieveCategoryForSkill(Skill skill) {
        if (skill.getCategoryTitle() == null) {
            throw new GraphQLException("categoryTitle is null for skill with id ["+skill.getTitle()+"]");
        }
        return categoryService.getByTitle(skill.getCategoryTitle());
    }

    private Grade retrieveGradeForSkill(Skill skill) {
        if (skill.getGradeTitle() == null) {
            throw new GraphQLException("gradeTitle is null for skill with id ["+skill.getTitle()+"]");
        }
        return gradeService.getByTitle(skill.getGradeTitle());
    }

}
