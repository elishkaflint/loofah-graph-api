package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillsQueryTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private SkillsQuery allSkillsQuery;

    @Before
    public void setUp() {
        allSkillsQuery = new SkillsQuery(skillRepository);
    }

    @Test
    public void get_findsAllSkillsFromRepository() {

        final List<Skill> expectedSkills = Collections.singletonList(
                getDefaultSkillBuilder().build()
        );
        when(skillRepository.findAll()).thenReturn(expectedSkills);

        final List<Skill> actualSkills = allSkillsQuery.get(dataFetchingEnvironment);

        assertEquals(expectedSkills, actualSkills);
    }
}