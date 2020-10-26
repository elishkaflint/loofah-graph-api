package com.loofah.graph.api.providers;

import com.loofah.graph.api.models.filters.SkillFilter;
import org.bson.Document;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;

import static com.loofah.graph.api.helpers.TestHelpers.CATEGORY_ID;
import static com.loofah.graph.api.helpers.TestHelpers.CRAFT_ID;
import static com.loofah.graph.api.helpers.TestHelpers.GRADE_ID;
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
                .withCategoryId(CATEGORY_ID)
                .withGradeId(GRADE_ID)
                .withCraftIds(Collections.singletonList(CRAFT_ID))
                .build();

        Query query = mongoQueryProvider.buildMongoQuery(skillFilter);

        assertTrue(query.getQueryObject().containsKey("categoryId"));
        assertTrue(query.getQueryObject().containsValue(CATEGORY_ID));

        assertTrue(query.getQueryObject().containsKey("gradeId"));
        assertTrue(query.getQueryObject().containsValue(GRADE_ID));

        assertTrue(query.getQueryObject().containsKey("craftIds"));
        Document doc = (Document) query.getQueryObject().get("craftIds");
        assertTrue(doc.containsKey("$in"));
        assertTrue(doc.containsValue(Collections.singletonList(CRAFT_ID)));
    }


}