package com.loofah.graph.api.queries;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.services.SkillService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillBuilder;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillsQueryTest {

    @Mock
    private SkillService skillService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private SkillsQuery skillsQuery;

    @Test
    public void get_findsAllSkills() {

        Map<String, Object> map = new HashMap<String, Object>() {{ put("gradeId","1"); }};
        when(dataFetchingEnvironment.getArgument("filter")).thenReturn(map);

        SkillFilter skillFilter = SkillFilter.builder().build();
        when(objectMapper.convertValue(map, SkillFilter.class)).thenReturn(skillFilter);

        final List<SkillDTO> expectedSkills = Collections.singletonList(getDefaultSkillDTO());
        when(skillService.getWithFilter(skillFilter)).thenReturn(expectedSkills);

        final List<SkillDTO> actualSkills = skillsQuery.get(dataFetchingEnvironment);

        assertEquals(expectedSkills, actualSkills);
    }

}

