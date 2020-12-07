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
    public void getSkillById_whenSkillRepositoryReturnsASkillOptional_thenReturnThatSkillOptional() {

        Skill expectedSkill = Skill.builder().build();
        when(skillRepository.findById("id")).thenReturn(Optional.of(expectedSkill));

        Optional<Skill> actualSkill = mongoBackedDataRetriever.getSkillById("id");

        assertEquals(Optional.of(expectedSkill), actualSkill);
    }

    @Test
    public void getSkillById_whenSkillRepositoryReturnsEmptyOptional_thenReturnEmptyOptional() {

        when(skillRepository.findById("id")).thenReturn(Optional.empty());

        Optional<Skill> actualSkill = mongoBackedDataRetriever.getSkillById("id");

        assertEquals(Optional.empty(), actualSkill);
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
    public void getCategoryById_whenCategoryRepositoryReturnCategoryOptional_thenReturnThatCategoryOptional() {

        Category expectedCategory = Category.builder().build();
        when(categoryRepository.findById("id")).thenReturn(Optional.of(expectedCategory));

        Optional<Category> actualCategory = mongoBackedDataRetriever.getCategoryById("id");

        assertEquals(Optional.of(expectedCategory), actualCategory);
    }

    @Test
    public void getCategoryById_whenCategoryRepositoryReturnsEmptyOptional_thenReturnEmptyOptional() {

        when(categoryRepository.findById("id")).thenReturn(Optional.empty());

        Optional<Category> actualCategory = mongoBackedDataRetriever.getCategoryById("id");

        assertEquals(Optional.empty(), actualCategory);
    }

    @Test
    public void getAllCategories_getsCategoriesViaCategoryRepository() {

        List<Category> expectedCategories = Collections.singletonList(Category.builder().build());
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategories = mongoBackedDataRetriever.getAllCategories();

        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    public void getGradeById_whenGradeRepositoryReturnsGradeOptional_thenReturnThatGradeOptional() {

        Grade expectedGrade = Grade.builder().build();
        when(gradeRepository.findById("id")).thenReturn(Optional.empty());

        Optional<Grade> actualGrade = mongoBackedDataRetriever.getGradeById("id");

        assertEquals(Optional.empty(), actualGrade);
    }

    @Test
    public void getAllGrades_getsGradesViaGradeRepository() {

        List<Grade> expectedGrades = Collections.singletonList(Grade.builder().build());
        when(gradeRepository.findAll()).thenReturn(expectedGrades);

        List<Grade> actualGrades = mongoBackedDataRetriever.getAllGrades();

        assertEquals(expectedGrades, actualGrades);
    }

    @Test
    public void getCraftById_whenCraftRepositoryReturnCraftOptional_thenReturnThatCraftOptional() {

        Craft expectedCraft = Craft.builder().build();
        when(craftRepository.findById("id")).thenReturn(Optional.of(expectedCraft));

        Optional<Craft> actualCraft = mongoBackedDataRetriever.getCraftById("id");

        assertEquals(Optional.of(expectedCraft), actualCraft);
    }

    @Test
    public void getCraftById_whenCraftRepositoryReturnsEmptyOptional_thenReturnEmptyOptional() {

        when(craftRepository.findById("id")).thenReturn(Optional.empty());

        Optional<Craft> actualCraft = mongoBackedDataRetriever.getCraftById("id");

        assertEquals(Optional.empty(), actualCraft);
    }

    @Test
    public void getAllCrafts_getsCraftsViaCraftRepository() {

        List<Craft> expectedCrafts = Collections.singletonList(Craft.builder().build());
        when(craftRepository.findAll()).thenReturn(expectedCrafts);

        List<Craft> actualCrafts = mongoBackedDataRetriever.getAllCrafts();

        assertEquals(expectedCrafts, actualCrafts);
    }

}