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

import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_ID;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillsByCategoryQueryTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private SkillsByCategoryQuery skillsByCategoryQuery;

    @Before
    public void setUp() {
        skillsByCategoryQuery = new SkillsByCategoryQuery(skillRepository);
    }

    @Test
    public void get_findsSkillsFromRepositoryWithGivenCategoryId() {

        List<Skill> expectedSkills = Arrays.asList(
                getDefaultSkillBuilder().build()
        );
        when(dataFetchingEnvironment.getArgument("categoryId")).thenReturn(CATEGORY_ID);
        when(skillRepository.findByCategoryId(CATEGORY_ID)).thenReturn(expectedSkills);

        List<Skill> actualSkills = skillsByCategoryQuery.get(dataFetchingEnvironment);

        assertEquals(expectedSkills, actualSkills);
    }

}