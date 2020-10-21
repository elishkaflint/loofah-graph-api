package com.loofah.graph.api.queries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_ID;
import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillsQueryTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @Mock
    private ObjectMapper objectMapper;

    private SkillsQuery allSkillsQuery;

    @Before
    public void setUp() {
        allSkillsQuery = new SkillsQuery(skillRepository, objectMapper);
    }

    @Test
    public void get_findsSkillsWithNoFilter() {

        when(dataFetchingEnvironment.getArgument("filter")).thenReturn(null);

        allSkillsQuery.get(dataFetchingEnvironment);

        Query expectedQuery = new Query();
        verify(skillRepository).findWithQuery(expectedQuery, Skill.class);
    }

    @Test
    public void get_findsSkillsWithGradeIdFilter() {

        Map<String, Object> skillFilterMap = new LinkedHashMap<String, Object>() {{
            put("gradeId", GRADE_ID);
        }};
        when(dataFetchingEnvironment.getArgument("filter")).thenReturn(skillFilterMap);
        when(objectMapper.convertValue(skillFilterMap, SkillFilter.class)).thenReturn(SkillFilter.builder().withGradeId(GRADE_ID).build());

        allSkillsQuery.get(dataFetchingEnvironment);

        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("gradeId").is(GRADE_ID));
        verify(skillRepository).findWithQuery(expectedQuery, Skill.class);
    }

    @Test
    public void get_findsSkillsWithCategoryIdFilter() {

        Map<String, Object> skillFilterMap = new LinkedHashMap<String, Object>() {{
            put("categoryId", CATEGORY_ID);
        }};
        when(dataFetchingEnvironment.getArgument("filter")).thenReturn(skillFilterMap);
        when(objectMapper.convertValue(skillFilterMap, SkillFilter.class)).thenReturn(SkillFilter.builder().withCategoryId(CATEGORY_ID).build());

        allSkillsQuery.get(dataFetchingEnvironment);

        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("categoryId").is(CATEGORY_ID));
        verify(skillRepository).findWithQuery(expectedQuery, Skill.class);
    }

    @Test
    public void get_findsSkillsWithGradeIdAndCategoryIdFilter() {

        Map<String, Object> skillFilterMap = new LinkedHashMap<String, Object>() {{
            put("gradeId", GRADE_ID);
            put("categoryId", CATEGORY_ID);
        }};
        when(dataFetchingEnvironment.getArgument("filter")).thenReturn(skillFilterMap);
        when(objectMapper.convertValue(skillFilterMap, SkillFilter.class)).thenReturn(SkillFilter.builder().withGradeId(GRADE_ID).withCategoryId(CATEGORY_ID).build());

        allSkillsQuery.get(dataFetchingEnvironment);

        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("gradeId").is(GRADE_ID));
        expectedQuery.addCriteria(Criteria.where("categoryId").is(CATEGORY_ID));
        verify(skillRepository).findWithQuery(expectedQuery, Skill.class);
    }

}