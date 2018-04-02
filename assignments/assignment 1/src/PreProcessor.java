import wrappers.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PreProcessor {

    private ArrayList<Document> documents = new ArrayList<>();

    private List<String> readFile(final String fileLocation) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(fileLocation));
        return stream.collect(Collectors.toList());
    }

    InvertedIndex loadIndex(final String stopsFile, final String documentsFile) throws IOException {

        //Attempt to read the index, throw an exception if unable to read
        List<String> stopWords = readFile(stopsFile);

        //Reads the documents file line by line
        for (String singleDocWithKey : readFile(documentsFile)) {

            //Convert our words to only english characters
            List<String> words = new ArrayList<>(Arrays.asList(singleDocWithKey.toLowerCase().replaceAll("[^a-z ]", " ").trim().split("\\s+")));

            //Remove any stop words
            words.removeAll(stopWords);

            //Store as a document
            documents.add(new Document(singleDocWithKey.substring(0, 17), words));
        }

        InvertedIndex index = new InvertedIndex();

        System.out.println(documents.size());

        //Insert the documents into the index
        for(Document document : documents) {
            for (String word: document.getWords())
                index.insertTerm(document.getID(), word);
        }

        return index;
    }

    public int getLoadedDocuentsCount() {
        return documents.size();
    }

}