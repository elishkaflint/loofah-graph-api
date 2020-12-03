package com.loofah.graph.api.providers;

import com.loofah.graph.api.models.filters.SkillFilter;
import org.bson.Document;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;

import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_ID_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_TITLE_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.CRAFT_ID_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.CRAFT_TITLE_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID_VALUE_1;
import static com.loofah.graph.api.helpers.TestHelpers.GRADE_TITLE_VALUE_1;
import static org.junit.Assert.*;

public class MongoQueryProviderTest {

    private MongoQueryProvider mongoQueryProvider = new MongoQueryProvider();

    @Test
    public void buildMongoQuery_addsNothingToQueryIfFilterIsEmpty() {

        SkillFilter skillFilter = SkillFilter.builder().build();

        Query query = mongoQueryProvider.buildMongoQuery(skillFilter);

        assertTrue(query.getQueryObject().isEmpty());
    }

    @Test
    public void buildMongoQuery_addsAllFieldsInFilterToQuery() {

        SkillFilter skillFilter = SkillFilter.builder()
                .withCategoryTitle(CATEGORY_TITLE_VALUE_1)
                .withGradeTitle(GRADE_TITLE_VALUE_1)
                .withCraftTitles(Collections.singletonList(CRAFT_TITLE_VALUE_1))
                .build();

        Query query = mongoQueryProvider.buildMongoQuery(skillFilter);

        assertTrue(query.getQueryObject().containsKey("categoryTitle"));
        assertTrue(query.getQueryObject().containsValue(CATEGORY_TITLE_VALUE_1));

        assertTrue(query.getQueryObject().containsKey("gradeTitle"));
        assertTrue(query.getQueryObject().containsValue(GRADE_TITLE_VALUE_1));

        assertTrue(query.getQueryObject().containsKey("craftTitles"));
        Document doc = (Document) query.getQueryObject().get("craftTitles");
        assertTrue(doc.containsKey("$in"));
        assertTrue(doc.containsValue(Collections.singletonList(CRAFT_TITLE_VALUE_1)));
    }


}