package com.loofah.graph.api.services;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    @Mock
    private DataRetriever dataRetriever;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getById() {

        Grade expectedGrade = Grade.builder().build();
        when(dataRetriever.getGradeById("id")).thenReturn(expectedGrade);

        Grade actualGrade = gradeService.getById("id");

        assertEquals(expectedGrade, actualGrade);
    }

    @Test
    public void getAll() {

        List<Grade> expectedGrades = Collections.singletonList(Grade.builder().build());
        when(dataRetriever.getAllGrades()).thenReturn(expectedGrades);

        List<Grade> actualGrades = gradeService.getAll();

        assertEquals(expectedGrades, actualGrades);
    }
}