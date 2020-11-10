package com.loofah.graph.api.services;

import com.loofah.graph.api.exceptions.DataNotFoundException;
import com.loofah.graph.api.models.DTO.SkillDTO;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @Mock
    private DataRetriever dataRetriever;

    @InjectMocks
    private SkillService skillService;

    @Test
    public void getById_whenSkillExists_thenReturnSkill() {

        Skill expectedSkillInDb = getDefaultSkillBuilder().build();
        SkillDTO expectedSkillDTO = getDefaultSkillDTO();
        when(dataRetriever.getSkillById("id")).thenReturn(Optional.of(expectedSkillInDb));
        when(dataRetriever.getCategoryById(expectedSkillInDb.getCategoryId())).thenReturn(Optional.of(expectedSkillDTO.getCategory()));

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

        SkillFilter skillFilter = SkillFilter.builder().withGradeId(GRADE_ID_VALUE_1).build();
        Skill expectedSkillInDb = getDefaultSkillBuilder().build();
        SkillDTO expectedSkillDTO = getDefaultSkillDTO();

        List<Skill> expectedSkills = Collections.singletonList(expectedSkillInDb);
        List<SkillDTO> expectedSkillDTOs = Collections.singletonList(expectedSkillDTO);

        when(dataRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);
        when(dataRetriever.getCategoryById(expectedSkillInDb.getCategoryId())).thenReturn(Optional.of(expectedSkillDTO.getCategory()));


        List<SkillDTO> actualSkills = skillService.getWithFilter(skillFilter);

        assertEquals(expectedSkillDTOs, actualSkills);
    }

}