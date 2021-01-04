package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    @Mock
    private DataRetriever dataRetriever;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getById_whenGradeExists_thenReturnGrade() {

        final Grade expectedGrade = Grade.builder().build();
        when(dataRetriever.getGradeById("id")).thenReturn(Optional.of(expectedGrade));

        final Grade actualGrade = gradeService.getById("id");

        assertEquals(expectedGrade, actualGrade);
    }

    @Test(expected = DataNotFoundException.class)
    public void getById_whenGradeDoesNotExist_thenThrowDataNotFoundException() {

        when(dataRetriever.getGradeById("0")).thenReturn(Optional.empty());
        gradeService.getById("0");
    }

    @Test
    public void getAll() {

        final List<Grade> expectedGrades = Collections.singletonList(Grade.builder().build());
        when(dataRetriever.getAllGrades()).thenReturn(expectedGrades);

        final List<Grade> actualGrades = gradeService.getAll();

        assertEquals(expectedGrades, actualGrades);
    }

    @Test
    public void getAll_sortsGrades() {

        final List<Grade> sortedGrades = asList(
                Grade.builder().withTitle("analystDeveloper").build(),
                Grade.builder().withTitle("developer").build(),
                Grade.builder().withTitle("seniorDeveloper").build()
        );
        final List<Grade> unsortedGrades = asList(
                Grade.builder().withTitle("seniorDeveloper").build(),
                Grade.builder().withTitle("developer").build(),
                Grade.builder().withTitle("analystDeveloper").build()
        );
        when(dataRetriever.getAllGrades()).thenReturn(unsortedGrades);

        final List<Grade> actualGrades = gradeService.getAll();

        assertEquals(sortedGrades, actualGrades);
    }
}