package com.loofah.graph.api.repositories;

import com.loofah.graph.api.models.Skill;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SkillsRepositoryTest {

    private List<Skill> skills;

    private SkillsRepository skillsRepository;

    @Before
    public void setUp() {
        skills = new ArrayList<>();
        skills.add(new Skill("1", "testTitle1", "testDescription1"));
        skills.add(new Skill("2", "testTitle2", "testDescription2"));
        skills.add(new Skill("3", "testTitle3", "testDescription3"));
        skillsRepository = new SkillsRepository(skills);
    }

    @Test
    public void gets_all_skills() {
        List<Skill> actualListOfSkills = skillsRepository.getSkills();
        assertEquals(skills, actualListOfSkills);
    }

    @Test
    public void gets_correct_skill_when_id_is_valid() {
        Skill actualSkill = skillsRepository.getSkill("2");
        assertEquals("testTitle2", actualSkill.getTitle());
        assertEquals("testDescription2", actualSkill.getDescription());
    }

}