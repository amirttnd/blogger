package in.tech.blogger.query;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.Arrays;
import java.util.List;

public class BlogQuery {

    String id;

    List<String> ids;

    String query;

    String categoryQ;

    String categoryId;

    Boolean onlyPublished;

    List<String> fieldsToExclude = Arrays.asList("content", "relatedBlog");

    Integer max = 10;

    Integer page = 1;

    public String getQuery() {
        return query != null ? query.trim() : "";
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
        if (id != null && !id.trim().equals("")) {
            return id;
        }
        return null;
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

    public List<String> getFieldsToExclude() {
        return fieldsToExclude;
    }

    public void setFieldsToExclude(List<String> fieldsToExclude) {
        this.fieldsToExclude = fieldsToExclude;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getOnlyPublished() {
        if (onlyPublished == null) {
            return false;
        }
        return onlyPublished;
    }

    public void setOnlyPublished(Boolean onlyPublished) {
        this.onlyPublished = onlyPublished;
    }

    public void addToFieldToExclude(String field) {
        if (field != null) {
            fieldsToExclude.add(field);
        }
    }

    public String getCategoryQ() {
        return categoryQ;
    }

    public void setCategoryQ(String categoryQ) {
        this.categoryQ = categoryQ;
    }

    public Integer getPage() {
        if (page != null && page < 1) {
            return 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page != null ? page : 1;
    }

    public Query build() {
        Query textQuery = new Query();

        Pageable pageRequest = new PageRequest(getPage() - 1, this.max, Sort.Direction.DESC, "dateCreated");

        Criteria criteria = new Criteria();

        if (onlyPublished != null) {
            textQuery.addCriteria(criteria.where("isPublished").is(getOnlyPublished()));
        }


        if (getQuery().length() > 0) {
            textQuery = TextQuery.queryText(TextCriteria.forDefaultLanguage().matching(query)).sortByScore();
        }

        if (getId() != null) {
            textQuery.addCriteria(criteria.where("id").is(id));
        }

        if (ids != null) {
            textQuery.addCriteria(criteria.where("id").in(ids));
        }

        if (categoryId != null) {
            textQuery.addCriteria(criteria.where("category.id").is(categoryId));
        }

        if (categoryQ != null) {
            textQuery.addCriteria(criteria.where("inCategories").in(categoryQ));
        }

        if (fieldsToExclude != null) {
            for (String field : fieldsToExclude) {
                textQuery.fields().exclude(field);
            }
        }

        textQuery.with(pageRequest);

        return textQuery;
    }


    @Override
    public String toString() {
        return "BlogQuery{" +
                "id='" + id + '\'' +
                ", ids=" + ids +
                ", query='" + query + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", onlyPublished=" + onlyPublished +
                ", fieldsToExclude=" + fieldsToExclude +
                ", max=" + max +
                '}';
    }
}
