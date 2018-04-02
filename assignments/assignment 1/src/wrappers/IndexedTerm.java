package wrappers;

import java.util.ArrayList;
import java.util.List;

public class IndexedTerm {

    private int df;
    private List<DocFreqTuple> postingsList = new ArrayList<>();

    public IndexedTerm(String docId) {
        this.df = 1;
        postingsList.add(new DocFreqTuple(docId));
    }

    public void addDocId(String docId) {
        for (DocFreqTuple docFreqTuple : postingsList) {
            if (docFreqTuple.getDocId().equalsIgnoreCase(docId)) {
                docFreqTuple.incrementFrequency();
                return;
            }
        }
        this.df++;
        postingsList.add(new DocFreqTuple(docId));
    }

    public List<DocFreqTuple> getPostingsList() {
        return postingsList;
    }

    public double getDf() {
        return df;
    }

}
