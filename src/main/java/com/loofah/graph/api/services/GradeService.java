package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeService {

    private GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade getById(String id) {
        return gradeRepository.findById(id).get();
    }

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }
}
