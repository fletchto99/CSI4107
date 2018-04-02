import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import wrappers.Query;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CSI4017_A1 {
    public static void main(String bull[]) throws ParserConfigurationException, SAXException, IOException {
        InvertedIndex invertedIndex = new InvertedIndex();

        PreProcessor preProcessor = new PreProcessor();
        System.out.println("*** LOADING INDEX ***");
        long indexStartTime = System.nanoTime();
        try {
            invertedIndex = preProcessor.loadIndex("StopWords.txt", "Trec_microblog11.txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("** ERROR READING INDEX FILE **");
            System.exit(1);
        }

        System.out.println("*** INDEX BUILT IN " + (System.nanoTime() - indexStartTime) / 1000000000 + " seconds ***");

        //Hardcoded query for testing
        List<Query> queries = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        System.out.println("Building queries");

        saxParser.parse("topics_MB1-49.txt", new DefaultHandler() {

            boolean numMatch = false;
            boolean topicMatch = false;

            String topic = "";
            String num = "";

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equalsIgnoreCase("num")) {
                    numMatch = true;
                } else if (qName.equalsIgnoreCase("title")) {
                    topicMatch = true;
                }
            }

            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
                if (numMatch) {
                    num = new String(ch, start, length);
                    numMatch = false;
                }
                if (topicMatch) {
                    topic = new String(ch, start, length);
                    topicMatch = false;
                }

            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                //Reached last element, add the query
                if (qName.equalsIgnoreCase("querytweettime")) {
                    if (!topic.equals("") && !num.equals("")) {
                        queries.add(new Query(Integer.parseInt(num.split(" ")[2].replaceAll("[^0-9]", "")), topic.trim()));
                    }
                }
            }
        });


        final Map<String, Double> scores = new HashMap<>();

        Path path = Paths.get("Results.txt");
        BufferedWriter writer = Files.newBufferedWriter(path);

        for (Query query : queries) {
            System.out.println("Running query: " + query.getId());
            for (String term : query.getTerms()) {

                Map<String, Double> tfidfs = invertedIndex.calcTfIdf(term);

                InvertedIndex localIdx = invertedIndex;
                tfidfs.forEach((docid, idfscore) -> {
                    double score = (0.5 + 0.5 * query.getFrequency(term)) * localIdx.idf(term) * idfscore;
                    if (scores.containsKey(term)) {
                        scores.merge(term, score, Double::sum);

                    } else {
                        scores.put(docid, score);
                    }
                });

            }

            Map<String, Double> sortedScores = scores.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .limit(1000)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));

            int[] idx = { 1 };
            sortedScores.forEach((id, score) -> {
                try {
                    writer.write(query.getId() + "\tQ0\t" + id + "\t" + idx[0] + "\t" + new DecimalFormat("#.00").format(score) + "\tmyRun\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                idx[0]++;
            });
        }

        writer.close();

    }
}
