package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.dto.SkillDTO;
import com.loofah.graph.api.models.database.Category;
import com.loofah.graph.api.models.database.Skill;
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

import static com.loofah.graph.api.helpers.TestHelpers.*;
import static org.junit.Assert.assertEquals;
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

        Skill expectedSkillInDb = getDefaultSkillBuilder().build();
        when(dataRetriever.getSkillById("id")).thenReturn(Optional.of(expectedSkillInDb));
        SkillDTO expectedSkillDTO = getDefaultSkillDTO();
        when(categoryService.getByTitle(expectedSkillInDb.getCategoryTitle())).thenReturn(expectedSkillDTO.getCategory());
        when(gradeService.getByTitle(expectedSkillInDb.getGradeTitle())).thenReturn(expectedSkillDTO.getGrade());
        when(craftService.getByTitle(expectedSkillInDb.getCraftTitles().get(0))).thenReturn(expectedSkillDTO.getCrafts().get(0));

        SkillDTO actualSkill = skillService.getById("id");

        assertEquals(expectedSkillDTO, actualSkill);
    }

    @Test(expected = DataNotFoundException.class)
    public void getById_whenSkillDoesNotExist_thenThrowDataNotFoundException() {

        when(dataRetriever.getSkillById("0")).thenReturn(Optional.empty());
        skillService.getById("0");
    }

    @Test
    public void getWithFilter() {

        SkillFilter skillFilter = SkillFilter.builder().withGradeTitle(GRADE_TITLE_VALUE_1).build();
        Skill expectedSkillInDb = getDefaultSkillBuilder().build();
        SkillDTO expectedSkillDTO = getDefaultSkillDTO();

        List<Skill> expectedSkills = Collections.singletonList(expectedSkillInDb);
        List<SkillDTO> expectedSkillDTOs = Collections.singletonList(expectedSkillDTO);

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);
        when(categoryService.getByTitle(expectedSkillInDb.getCategoryTitle())).thenReturn(expectedSkillDTO.getCategory());
        when(gradeService.getByTitle(expectedSkillInDb.getGradeTitle())).thenReturn(expectedSkillDTO.getGrade());
        when(craftService.getByTitle(expectedSkillInDb.getCraftTitles().get(0))).thenReturn(expectedSkillDTO.getCrafts().get(0));

        List<SkillDTO> actualSkills = skillService.getWithFilter(skillFilter);

        assertEquals(expectedSkillDTOs, actualSkills);
    }

    @Test(expected=DataNotFoundException.class)
    public void getWithFilter_whenCategoryCannotBeFoundForCategoryId_throwDataNotFoundException() {

        SkillFilter skillFilter = SkillFilter.builder()
                .withCategoryTitle(CATEGORY_TITLE_VALUE_1)
                .withGradeTitle(GRADE_TITLE_VALUE_1)
                .build();

        List<Skill> expectedSkills = Collections.singletonList(getDefaultSkillBuilder().build());

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);
        when(categoryService.getByTitle(CATEGORY_TITLE_VALUE_1)).thenThrow(DataNotFoundException.class);

        skillService.getWithFilter(skillFilter);
    }

    @Test(expected=DataNotFoundException.class)
    public void getWithFilter_whenGradeCannotBeFoundForGradeId_throwDataNotFoundException() {

        SkillFilter skillFilter = SkillFilter.builder()
                .withCategoryTitle(CATEGORY_ID_VALUE_1)
                .withGradeTitle(GRADE_TITLE_VALUE_1)
                .build();

        List<Skill> expectedSkills = Collections.singletonList(getDefaultSkillBuilder().build());

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);
        when(categoryService.getByTitle(CATEGORY_TITLE_VALUE_1)).thenReturn(Category.builder().build());
        when(gradeService.getByTitle(GRADE_TITLE_VALUE_1)).thenThrow(DataNotFoundException.class);

        skillService.getWithFilter(skillFilter);
    }

}