package com.loofah.graph.api.retrievers;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MongoBackedDataRetrieverTest {

    @Mock
    private MongoQueryProvider mongoQueryProvider;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private CraftRepository craftRepository;

    @InjectMocks
    private MongoBackedDataRetriever mongoBackedDataRetriever;

    @Test
    public void getSkillById_getsSkillViaSkillRepository() {

        Skill expectedSkill = Skill.builder().build();
        when(skillRepository.findById("id")).thenReturn(Optional.of(expectedSkill));

        Skill actualSkill = mongoBackedDataRetriever.getSkillById("id");

        assertEquals(expectedSkill, actualSkill);
    }

    @Test
    public void getSkillsWithFilter_getsSkillsViaSkillRepositoryAndMongoQueryProvider() {

        Query query = new Query();
        SkillFilter skillFilter = SkillFilter.builder().build();
        when(mongoQueryProvider.buildMongoQuery(skillFilter)).thenReturn(query);

        List<Skill> expectedSkills = Collections.singletonList(Skill.builder().build());
        when(skillRepository.findWithQuery(query, Skill.class)).thenReturn(expectedSkills);

        List<Skill> actualSkills = mongoBackedDataRetriever.getSkillWithFilter(skillFilter);

        assertEquals(expectedSkills, actualSkills);
    }

    @Test
    public void getCategoryById_getsCategoryViaCategoryRepository() {

        Category expectedCategory = Category.builder().build();
        when(categoryRepository.findById("id")).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = mongoBackedDataRetriever.getCategoryById("id");

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void getAllCategories_getsCategoriesViaCategoryRepository() {

        List<Category> expectedCategories = Collections.singletonList(Category.builder().build());
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategories = mongoBackedDataRetriever.getAllCategories();

        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    public void getGradeById_getsGradeViaGradeRepository() {

        Grade expectedGrade = Grade.builder().build();
        when(gradeRepository.findById("id")).thenReturn(Optional.of(expectedGrade));

        Grade actualGrade = mongoBackedDataRetriever.getGradeById("id");

        assertEquals(expectedGrade, actualGrade);
    }

    @Test
    public void getAllGrades_getsGradesViaGradeRepository() {

        List<Grade> expectedGrades = Collections.singletonList(Grade.builder().build());
        when(gradeRepository.findAll()).thenReturn(expectedGrades);

        List<Grade> actualGrades = mongoBackedDataRetriever.getAllGrades();

        assertEquals(expectedGrades, actualGrades);
    }

    @Test
    public void getCraftById_getsCraftViaCraftRepository() {

        Craft expectedCraft = Craft.builder().build();
        when(craftRepository.findById("id")).thenReturn(Optional.of(expectedCraft));

        Craft actualCraft = mongoBackedDataRetriever.getCraftById("id");

        assertEquals(expectedCraft, actualCraft);
    }

    @Test
    public void getAllCrafts_getsCraftsViaCraftRepository() {

        List<Craft> expectedCrafts = Collections.singletonList(Craft.builder().build());
        when(craftRepository.findAll()).thenReturn(expectedCrafts);

        List<Craft> actualCrafts = mongoBackedDataRetriever.getAllCrafts();

        assertEquals(expectedCrafts, actualCrafts);
    }

}