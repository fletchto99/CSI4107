import wrappers.DocFreqTuple;
import wrappers.IndexedTerm;

import java.util.HashMap;
import java.util.Map;

public class InvertedIndex {

    private Map<String, IndexedTerm> index = new HashMap<>();

    public void insertTerm(final String docId, final String term) {

        if (index.containsKey(term)) {
            index.get(term).addDocId(docId);
        } else {
            index.put(term, new IndexedTerm(docId));
        }
    }

    Map<String, Double> calcTfIdf(final String term) {
        if (!index.containsKey(term)) {
            return new HashMap<>();
        }
        IndexedTerm indexedTerm = index.get(term);
        double idf = Math.log(index.size() / indexedTerm.getDf());
        Map<String, Double> tfIdf = new HashMap<>();
        indexedTerm.getPostingsList().forEach(doc -> tfIdf.put(doc.getDocId(), idf * doc.getTf()));
        return tfIdf;
    }

    double idf(String term) {
        return Math.log(index.size() / index.get(term).getDf());
    }
}
