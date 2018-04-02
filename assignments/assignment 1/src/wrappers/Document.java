package wrappers;

import java.util.List;

public class Document {

    private String ID;
    private List<String> words;

    public Document(String ID, List<String> words) {
        this.ID = ID;
        this.words = words;
    }

    public String getID() {
        return ID;
    }

    public List<String> getWords() {
        return words;
    }

}
