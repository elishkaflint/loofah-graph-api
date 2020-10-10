package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.repositories.GradeRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.loofah.graph.api.helpers.TestHelpers.CRAFT_ID;
import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultGradeBuilder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradeQueryTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private GradeQuery gradeQuery;

    @Before
    public void setUp() {
        gradeQuery = new GradeQuery(gradeRepository);
    }

    @Test
    public void get_findsGradeFromRepositoryWithGivenId() {
        final Grade expectedGrade = getDefaultGradeBuilder().build();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(CRAFT_ID);
        when(gradeRepository.findById(GRADE_ID)).thenReturn(Optional.of(expectedGrade));

        final Grade actualCraft = gradeQuery.get(dataFetchingEnvironment);
        assertEquals(expectedGrade, actualCraft);
    }
}
