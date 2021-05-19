package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Craft;
import com.loofah.graph.api.models.database.Grade;
import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.dto.SkillDTO;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.retrievers.DataRetriever;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.loofah.graph.api.helpers.TestHelpers.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @Mock
    private DataRetriever dataRetriever;

    @Mock
    private CategoryService categoryService;

    @Mock
    private GradeService gradeService;

    @Mock
    private CraftService craftService;

    @InjectMocks
    private SkillService skillService;

    @Test
    public void getById_whenSkillExists_thenReturnSkill() {

        final Skill expectedSkillInDb = getDefaultSkillBuilder().build();
        when(dataRetriever.getSkillById("id")).thenReturn(Optional.of(expectedSkillInDb));
        final SkillDTO expectedSkillDTO = getDefaultSkillDTO();
        when(categoryService.getByTitle(expectedSkillInDb.getCategoryTitle())).thenReturn(expectedSkillDTO.getCategory());
        when(gradeService.getByTitle(expectedSkillInDb.getGradeTitle())).thenReturn(expectedSkillDTO.getGrade());
        when(craftService.getByTitle(expectedSkillInDb.getCraftTitles().get(0))).thenReturn(expectedSkillDTO.getCrafts().get(0));

        final SkillDTO actualSkill = skillService.getById("id");

        assertEquals(expectedSkillDTO, actualSkill);
    }

    @Test(expected = DataNotFoundException.class)
    public void getById_whenSkillDoesNotExist_thenThrowDataNotFoundException() {

        when(dataRetriever.getSkillById("0")).thenReturn(Optional.empty());
        skillService.getById("0");
    }

    @Test
    public void getWithFilter() {

        final SkillFilter skillFilter = SkillFilter.builder().withGradeTitles(Collections.singletonList(GRADE_TITLE_VALUE_1)).build();
        final Skill expectedSkillInDb = getDefaultSkillBuilder().build();
        final SkillDTO expectedSkillDTO = getDefaultSkillDTO();

        final List<Skill> expectedSkills = Collections.singletonList(expectedSkillInDb);
        final List<SkillDTO> expectedSkillDTOs = Collections.singletonList(expectedSkillDTO);

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);
        when(categoryService.getByTitle(expectedSkillInDb.getCategoryTitle())).thenReturn(expectedSkillDTO.getCategory());
        when(gradeService.getByTitle(expectedSkillInDb.getGradeTitle())).thenReturn(expectedSkillDTO.getGrade());
        when(craftService.getByTitle(expectedSkillInDb.getCraftTitles().get(0))).thenReturn(expectedSkillDTO.getCrafts().get(0));

        final List<SkillDTO> actualSkills = skillService.getWithFilter(skillFilter);

        assertEquals(expectedSkillDTOs, actualSkills);
    }

    @Test
    public void getWithFilter_sorts_skills() {

        final SkillFilter skillFilter = mock(SkillFilter.class);
        final Category expectedCategory = getDefaultCategoryBuilder().build();
        final Grade expectedGrade = getDefaultGradeBuilder().build();
        final Craft expectedCraft = getDefaultCraftBuilder().build();
        final List<Skill> unorderedSkills = asList(getDefaultSkillBuilder().build(), getDefaultSkillBuilder().build());
        final List<SkillDTO> orderedSkillDTOs = unorderedSkills.stream()
                .map(skill -> new SkillDTO(skill, expectedCategory, expectedGrade, Collections.singletonList(expectedCraft)))
                .sorted()
                .collect(Collectors.toList());

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(unorderedSkills);
        when(categoryService.getByTitle(any())).thenReturn(expectedCategory);
        when(gradeService.getByTitle(any())).thenReturn(expectedGrade);
        when(craftService.getByTitle(any())).thenReturn(expectedCraft);

        final List<SkillDTO> actualSkillDTOs = skillService.getWithFilter(skillFilter);

        assertEquals(orderedSkillDTOs, actualSkillDTOs);
    }

    @Test(expected = DataNotFoundException.class)
    public void getWithFilter_whenCategoryCannotBeFoundForCategoryId_throwDataNotFoundException() {

        final SkillFilter skillFilter = SkillFilter.builder()
                .withCategoryTitle(CATEGORY_TITLE_VALUE_1)
                .withGradeTitles(Collections.singletonList(GRADE_TITLE_VALUE_1))
                .build();

        final List<Skill> expectedSkills = Collections.singletonList(getDefaultSkillBuilder().build());

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);
        when(categoryService.getByTitle(CATEGORY_TITLE_VALUE_1)).thenThrow(DataNotFoundException.class);

        skillService.getWithFilter(skillFilter);
    }

    @Test(expected = DataNotFoundException.class)
    public void getWithFilter_whenGradeCannotBeFoundForGradeId_throwDataNotFoundException() {

        final SkillFilter skillFilter = SkillFilter.builder()
                .withCategoryTitle(CATEGORY_ID_VALUE_1)
                .withGradeTitles(Collections.singletonList(GRADE_TITLE_VALUE_1))
                .build();

        final List<Skill> expectedSkills = Collections.singletonList(getDefaultSkillBuilder().build());

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);
        when(categoryService.getByTitle(CATEGORY_TITLE_VALUE_1)).thenReturn(Category.builder().build());
        when(gradeService.getByTitle(GRADE_TITLE_VALUE_1)).thenThrow(DataNotFoundException.class);

        skillService.getWithFilter(skillFilter);
    }

}