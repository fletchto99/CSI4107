package wrappers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Query {

    private Map<String, Integer> terms = new HashMap<>();
    private int id;

    public Query(final int id, final String query) {
        this.id = id;
        String[] queryTerms = query.toLowerCase().split("\\s+");
        for (String term : queryTerms) {
            if (terms.containsKey(term)) {
                terms.merge(term, 1, Integer::sum);
            } else {
                terms.put(term, 1);
            }
        }

    }

    public int getId() {
        return this.id;
    }

    public Set<String> getTerms() {
        return terms.keySet();
    }

    public int getFrequency(String term) {
        return terms.get(term);
    }

}
