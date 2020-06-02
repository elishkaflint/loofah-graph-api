package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllSkillsQueryTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private AllSkillsQuery allSkillsQuery;

    @Before
    public void setUp() throws Exception {
        allSkillsQuery = new AllSkillsQuery(skillRepository);
    }

    @Test
    public void get_findsAllSkillsFromRepository() {
        List<Skill> expectedSkills = Arrays.asList(
                getDefaultSkillBuilder().build()
        );
        when(skillRepository.findAll()).thenReturn(expectedSkills);

        List<Skill> actualSkills = allSkillsQuery.get(dataFetchingEnvironment);
        assertEquals(expectedSkills, actualSkills);
    }
}