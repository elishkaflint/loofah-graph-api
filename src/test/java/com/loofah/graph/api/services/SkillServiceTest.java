package com.loofah.graph.api.services;

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

import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @Mock
    private DataRetriever skillRetriever;

    @InjectMocks
    private SkillService skillService;

    @Test
    public void getById() {

        Skill expectedSkill = Skill.builder().build();
        when(skillRetriever.getSkillById("id")).thenReturn(expectedSkill);

        Skill actualSkill = skillService.getById("id");

        assertEquals(expectedSkill, actualSkill);
    }

    @Test
    public void getWithFilter() {

        SkillFilter skillFilter = SkillFilter.builder().withGradeId(GRADE_ID).build();
        List<Skill> expectedSkills = Collections.singletonList(Skill.builder().build());
        when(skillRetriever.getSkillWithFilter(skillFilter)).thenReturn(expectedSkills);

        List<Skill> actualSkills = skillService.getWithFilter(skillFilter);

        assertEquals(expectedSkills, actualSkills);
    }

}