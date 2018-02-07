package in.tech.blogger.query;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.List;

public class BlogQuery {

    String id;

    List<String> ids;

    String query;

    String categoryId;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Query build() {
        Query textQuery = new Query();
        if (query != null) {
            textQuery = TextQuery.queryText(TextCriteria.forDefaultLanguage().matching(query));
        }

        if (id != null) {
            textQuery.addCriteria(Criteria.where("id").is(id));
        }

        if (ids != null) {
            textQuery.addCriteria(Criteria.where("id").in(ids));
        }

        if (categoryId != null) {
            textQuery.addCriteria(Criteria.where("category.id").is(categoryId));
        }

        return textQuery;
    }
}
