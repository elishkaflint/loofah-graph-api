package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.services.GradeService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultGradeBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradesQueryTest {

    @Mock
    private GradeService gradeService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @InjectMocks
    private GradesQuery gradesQuery;

    @Test
    public void get_findsAllCraftsFromRepository() {

        final List<Grade> expectedGrades = Collections.singletonList(
                getDefaultGradeBuilder().build()
        );
        when(gradeService.getAll()).thenReturn(expectedGrades);

        final List<Grade> actualGrades = gradesQuery.get(dataFetchingEnvironment);

        assertEquals(expectedGrades, actualGrades);
    }


}