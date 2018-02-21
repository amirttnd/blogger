package in.tech.blogger.query;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

public class UserQuery {

    String id;

    String email;

    String query;

    int max = 10;

    int offset = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPage() {
        if (offset == 0 || offset < max) {
            return 0;
        }
        return (int) Math.floor(offset / max);
    }

    public Query build() {
        Query textQuery = new Query();

        Criteria criteria = new Criteria();

        PageRequest pageRequest = new PageRequest(getPage(), max, new Sort(Sort.Direction.ASC, "dateCreated"));

        if (id != null) {
            textQuery.addCriteria(criteria.where("id").is(id));
        }

        if (email != null) {
            textQuery.addCriteria(criteria.where("email").is(email));
        }

        if (query != null) {
            textQuery = TextQuery.queryText(TextCriteria.forDefaultLanguage().matching(query)).sortByScore();
        }
        textQuery.with(pageRequest);
        return textQuery;
    }
}
