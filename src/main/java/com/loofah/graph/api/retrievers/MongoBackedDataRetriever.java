package com.loofah.graph.api.retrievers;

import com.loofah.graph.api.models.DTO.SkillDTO;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.providers.MongoQueryProvider;
import com.loofah.graph.api.repositories.CategoryRepository;
import com.loofah.graph.api.repositories.CraftRepository;
import com.loofah.graph.api.repositories.GradeRepository;
import com.loofah.graph.api.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MongoBackedDataRetriever implements DataRetriever {

    private final SkillRepository skillRepository;
    private final GradeRepository gradeRepository;
    private final CraftRepository craftRepository;
    private final CategoryRepository categoryRepository;
    private final MongoQueryProvider mongoQueryProvider;

    @Autowired
    public MongoBackedDataRetriever(SkillRepository skillRepository,
                                    GradeRepository gradeRepository,
                                    CraftRepository craftRepository,
                                    CategoryRepository categoryRepository,
                                    MongoQueryProvider mongoQueryProvider) {
        this.skillRepository = skillRepository;
        this.gradeRepository = gradeRepository;
        this.craftRepository = craftRepository;
        this.categoryRepository = categoryRepository;
        this.mongoQueryProvider = mongoQueryProvider;
    }

    @Override
    public Optional<Skill> getSkillById(String id) {
        return skillRepository.findById(id);
    }

    @Override
    public List<Skill> getSkillWithFilter(SkillFilter skillFilter) {
        Query query = mongoQueryProvider.buildMongoQuery(skillFilter);
        return skillRepository.findWithQuery(query, Skill.class);
    }

    @Override
    public Optional<Grade> getGradeById(String id) {
        return gradeRepository.findById(id);
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Craft> getCraftById(String id) {
        return craftRepository.findById(id);
    }

    @Override
    public List<Craft> getAllCrafts() {
        return craftRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
