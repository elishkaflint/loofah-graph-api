package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.CategoryWithSkills;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.repositories.CategoryRepository;
import com.loofah.graph.api.repositories.SkillRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static com.loofah.graph.api.helpers.TestHelpers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllCategoriesQueryTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    private AllCategoriesQuery allCategoriesQuery;

    @Before
    public void setUp() throws Exception {
        allCategoriesQuery = new AllCategoriesQuery(categoryRepository, skillRepository);
    }

    @Test
    public void get_findsAllCategoriesFromRepositoryAndPopulatesWithSkills() {
        List<Skill> expectedSkills = Arrays.asList(
                getDefaultSkillBuilder().build()
        );

        List<Category> expectedCategories = Arrays.asList(
                getDefaultCategoryBuilder().build()
        );
        when(skillRepository.findAll()).thenReturn(expectedSkills);
        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<CategoryWithSkills> actualCategoriesResponse = allCategoriesQuery.get(dataFetchingEnvironment);

        Category expectedCategoryOne = getCategoryById(expectedCategories, 1);
        CategoryWithSkills actualCategoryOne = getCategoryResponseById(actualCategoriesResponse, 1);

        assertEquals(expectedCategories.size(), actualCategoriesResponse.size());
        assertEquals(expectedCategoryOne.getId(), actualCategoryOne.getId());
        assertEquals(expectedCategoryOne.getTitle(), actualCategoryOne.getTitle());
        assertEquals(filterSkillsByCategoryId(expectedSkills, 1), actualCategoryOne.getSkills());

        assertTrue(allSkillIdsMatchAssociatedCategory(actualCategoriesResponse));
    }


}