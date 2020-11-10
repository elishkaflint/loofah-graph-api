package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.services.GradeService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultGradeBuilder;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradeQueryTest {

    @Mock
    private GradeService gradeService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @InjectMocks
    private GradeQuery gradeQuery;

    @Test
    public void get_findsGradeFromRepositoryWithGivenId() {
        final Grade expectedGrade = getDefaultGradeBuilder().build();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(GRADE_ID_VALUE_1);
        when(gradeService.getById(GRADE_ID_VALUE_1)).thenReturn(expectedGrade);

        final Grade actualCraft = gradeQuery.get(dataFetchingEnvironment);
        assertEquals(expectedGrade, actualCraft);
    }
}
