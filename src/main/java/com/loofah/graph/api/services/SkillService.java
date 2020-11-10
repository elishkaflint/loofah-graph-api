package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.retrievers.DataRetriever;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SkillService {

    private final DataRetriever dataRetriever;

    @Autowired
    public SkillService(DataRetriever dataRetriever) {
        this.dataRetriever = dataRetriever;
    }

    public SkillDTO getById(String id) {
        Skill skill = dataRetriever.getSkillById(id).orElseThrow(() -> new DataNotFoundException("No skill found with id ["+id+"]"));
        return transformToSkillDTO(skill);
    }

    //TODO This method will return an empty list if any of the skills that match are not retrievable (eg if the category cannot be found)
    // Therefore in this case, an empty list will be returned to the calling client in the "data" field of the http response.
    // This should be improved so that instead our API returns the partial list of skills that could be retrieved in the
    // "data" field and details of those that could not be retrieved in the "errors" field of the response.
    public List<SkillDTO> getWithFilter(SkillFilter skillFilter) {
        List<Skill> skills = dataRetriever.getSkillWithFilter(skillFilter);
        return skills.stream().map(this::transformToSkillDTO).collect(Collectors.toList());
    }

    private SkillDTO transformToSkillDTO(Skill skill) {
        if (skill.getCategoryId() == null) {
            throw new GraphQLException("Category Id is null for skill with id ["+skill.getId()+"]");
        }

        Optional<Category> category = dataRetriever.getCategoryById(skill.getCategoryId());

        return category.map(value -> new SkillDTO(skill, value)).orElse(null);

    }

}
