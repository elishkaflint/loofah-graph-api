package com.loofah.graph.api.repositories;

import com.loofah.graph.api.testConfig.GraphAPITestConfiguration;
import com.loofah.graph.api.models.Skill;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GraphAPITestConfiguration.class)
public class SkillsRepositoryTest {

    @Autowired
    private List<Skill> setUpTestSkills;

    private SkillsRepository skillsRepository;

    @Before
    public void setUp() {
        skillsRepository = new SkillsRepository(setUpTestSkills);
    }

    @Test
    public void gets_all_skills() {
        List<Skill> actualListOfSkills = skillsRepository.getSkills();
        assertEquals(setUpTestSkills, actualListOfSkills);
    }

    @Test
    public void gets_correct_skill_when_id_is_valid() {
        Skill actualSkill = skillsRepository.getSkill("2");
        assertEquals("testTitle2", actualSkill.getTitle());
        assertEquals("testDescription2", actualSkill.getDescription());
    }

}