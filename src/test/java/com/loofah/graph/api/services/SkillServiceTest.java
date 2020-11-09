package com.loofah.graph.api.services;

import com.loofah.graph.api.models.database.Skill;
import com.loofah.graph.api.models.filters.SkillFilter;
import com.loofah.graph.api.providers.MongoQueryProvider;
import com.loofah.graph.api.repositories.SkillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @Mock
    private MongoQueryProvider mongoQueryProvider;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Test
    public void getById() {

        Skill expectedSkill = Skill.builder().build();
        when(skillRepository.findById("id")).thenReturn(Optional.of(expectedSkill));

        Skill actualSkill = skillService.getById("id");

        assertEquals(expectedSkill, actualSkill);
    }

    @Test
    public void getWithFilter() {

        SkillFilter skillFilter = SkillFilter.builder().withGradeId(GRADE_ID).build();
        Query query = new Query();
        when(mongoQueryProvider.buildMongoQuery(skillFilter)).thenReturn(query);

        List<Skill> expectedSkills = Collections.singletonList(Skill.builder().build());
        when(skillRepository.findWithQuery(query, Skill.class)).thenReturn(expectedSkills);

        List<Skill> actualSkills = skillService.getWithFilter(skillFilter);

        assertEquals(expectedSkills, actualSkills);
    }

}