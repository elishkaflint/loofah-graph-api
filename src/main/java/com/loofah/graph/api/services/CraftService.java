package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.repositories.CraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CraftService {

    private CraftRepository craftRepository;

    @Autowired
    public CraftService(CraftRepository craftRepository) {
        this.craftRepository = craftRepository;
    }

    public Craft getById(String id) {
        return craftRepository.findById(id).get();
    }

    public List<Craft> getAll() {
        return craftRepository.findAll();
    }
}
