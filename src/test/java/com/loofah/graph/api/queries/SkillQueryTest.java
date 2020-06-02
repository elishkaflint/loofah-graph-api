package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.loofah.graph.api.helpers.TestHelpers.SKILL_ID;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillBuilder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillQueryTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private SkillQuery skillQuery;

    @Before
    public void setUp() throws Exception {
        skillQuery = new SkillQuery(skillRepository);
    }

    @Test
    public void get_findSkillFromRepositoryWithIdFromDataFetchingEnvironment() {
        Skill expectedSkill = getDefaultSkillBuilder().build();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(SKILL_ID);
        when(skillRepository.findById(SKILL_ID)).thenReturn(Optional.of(expectedSkill));

        Skill actualSkill = skillQuery.get(dataFetchingEnvironment);
        assertEquals(expectedSkill, actualSkill);
    }
}
