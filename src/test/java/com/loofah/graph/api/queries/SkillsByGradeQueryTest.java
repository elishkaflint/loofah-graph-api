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

import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_ID;
import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillsByGradeQueryTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private SkillsByGradeQuery skillsByGradeQuery;

    @Before
    public void setUp() {
        skillsByGradeQuery = new SkillsByGradeQuery(skillRepository);
    }

    @Test
    public void get_findsSkillsFromRepositoryWithGivenCategoryId() {

        final List<Skill> expectedSkills = Collections.singletonList(
                getDefaultSkillBuilder().build()
        );
        when(dataFetchingEnvironment.getArgument("gradeId")).thenReturn(GRADE_ID);
        when(skillRepository.findByGradeId(GRADE_ID)).thenReturn(expectedSkills);

        final List<Skill> actualSkills = skillsByGradeQuery.get(dataFetchingEnvironment);

        assertEquals(expectedSkills, actualSkills);
    }

}