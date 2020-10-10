package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.repositories.CraftRepository;
import com.loofah.graph.api.repositories.GradeRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.loofah.graph.api.helpers.TestHelpers.getDefaultCraftBuilder;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultGradeBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradesQueryTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private GradesQuery gradesQuery;

    @Before
    public void setUp() throws Exception {
        gradesQuery = new GradesQuery(gradeRepository);
    }

    @Test
    public void get_findsAllCraftsFromRepository() {

        final List<Grade> expectedGrades = Collections.singletonList(
                getDefaultGradeBuilder().build()
        );
        when(gradeRepository.findAll()).thenReturn(expectedGrades);

        final List<Grade> actualGrades = gradesQuery.get(dataFetchingEnvironment);

        assertEquals(expectedGrades, actualGrades);
    }


}