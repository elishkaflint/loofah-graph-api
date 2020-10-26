package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.repositories.GradeRepository;
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
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getById() {

        Grade expectedGrade = Grade.builder().build();
        when(gradeRepository.findById("id")).thenReturn(Optional.of(expectedGrade));

        Grade actualGrade = gradeService.getById("id");

        assertEquals(expectedGrade, actualGrade);
    }

    @Test
    public void getAll() {

        List<Grade> expectedGrades = Collections.singletonList(Grade.builder().build());
        when(gradeRepository.findAll()).thenReturn(expectedGrades);

        List<Grade> actualGrades = gradeService.getAll();

        assertEquals(expectedGrades, actualGrades);
    }
}