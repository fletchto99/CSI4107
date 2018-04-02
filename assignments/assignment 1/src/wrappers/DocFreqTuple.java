package wrappers;

public class DocFreqTuple {
    private String docId;
    private int tf;

    public DocFreqTuple(String docId) {
        this.docId = docId;
        this.tf = 1;
    }

    public String getDocId() {
        return this.docId;
    }

    public void incrementFrequency() {
        tf++;
    }

    public double getTf() {
        return tf;
    }
}
