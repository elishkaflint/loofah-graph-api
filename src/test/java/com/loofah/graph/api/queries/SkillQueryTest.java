package com.loofah.graph.api.queries;

import com.loofah.graph.api.models.dto.SkillDTO;
import com.loofah.graph.api.services.SkillService;
import graphql.schema.DataFetchingEnvironment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.loofah.graph.api.helpers.TestHelpers.SKILL_ID_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.getDefaultSkillDTO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillQueryTest {

    @Mock
    private SkillService skillService;

    @Mock
    private DataFetchingEnvironment dataFetchingEnvironment;

    @InjectMocks
    private SkillQuery skillQuery;

    @Test
    public void get_findsSkillFromRepositoryWithGivenId() {
        final SkillDTO expectedSkillDTO = getDefaultSkillDTO();

        when(dataFetchingEnvironment.getArgument("id")).thenReturn(SKILL_ID_VALUE_1);
        when(skillService.getById(SKILL_ID_VALUE_1)).thenReturn(expectedSkillDTO);

        final SkillDTO actualSkill = skillQuery.get(dataFetchingEnvironment);
        assertEquals(expectedSkillDTO, actualSkill);
    }
}
