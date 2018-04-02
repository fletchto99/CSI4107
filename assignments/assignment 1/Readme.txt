----- Question 1 -----


Willem Kowal - 7741241
Matt Langlois - 7731813

The project was a joint effort which made use of pair programming. We both contributed an equal amount of work.

----- Question 2 -----

Notes on functionality of program:

1. The main function loads the required files
2. The main function calls to the pre-processor
3. The pre-processor normalizes the data and removes all stopwords
4. The words are all then added to a dictionary
5. The dictionary is used to build the Inverted Index which is passed back to the main function
6. Next the queries are loaded into the program (parsed as XML)
7. Each query is given an ID and a query topic
8. Next the queries are tested against the InvertedIndex to determine how relevant the documents are compared to the word in the query
9. The results for each of the queries is stored in a sorted linkedhashmap
10. Once all results have been gathered for a query they are saved to a file called Results.txt

----- Question 3 -----

The program is written in Java and makes the assumption that you have Java 8 or greater installed on your computer. The program will use the txt files located in the root directory when executing.
To run the program through command line, one must first compile all the java files. This is accomplished with the “javac” command. After this, run “java” against CSI4107_A1.

A better way the execute the program is to open it in an IDE, such as Eclipse or Intellij. From here simply execute the main method in CSI4107_A1.java.

----- Question 4 -----


The program makes use of many data structures to ensure efficiency. For example the inverted index was built using
a hash map to ensure efficient access. We made full use of core java functionality such as streams, collections, sorting algorithms and lambda functions.

A total of 45899 words were loaded into the inverted index, some of which were seen in multiple documents. Here's a sample of 100 words from our vocabulary:

service
savage
petitionbuzz
petitions
savews
people
question
rethink
positive
outlook
technology
staffing
specialist
rethink
expects
revenues
hfjtmy
zombie
manager
phoenix
appoints
phoenix
closed
business
latest
releases
globalclassified
releases
presents
wonderland
catonsville
dinner
posted
presents
wonderland
gmicayt
territory
manager
location
calgary
alberta
canada
category
school
funding
transparency
newsid
manchester
council
details
saving
fypypc
depressing
deprived
hardest
interested
professional
global
translation
services
fitness
service
evfleb
mostest
beautiful
beware
meanies
thebluemeanies
dentes
warcraft
alisson
exciting
bunchesuk
happening
gearing
valentines
bouquets
flying
appreciate
people
broadcasting
asking
people
samanthaprabu
cricket
watching
matches
coming
rendell
philly
morning
affiliates
meeting
fundamental
broadcasting
network
baptist
church
newport
manchester
united
patrice
france
newzfor

First ten answers to question 1:

1	Q0	29208850963898368	1	105.49	myRun
1	Q0	29386330014220288	2	105.49	myRun
1	Q0	34703780100448257	3	105.49	myRun
1	Q0	32835196143276032	4	84.91	myRun
1	Q0	33509236184973313	5	84.91	myRun
1	Q0	32834698367475712	6	84.91	myRun
1	Q0	30423507347181568	7	84.91	myRun
1	Q0	34889484864585728	8	70.33	myRun
1	Q0	34142019685187584	9	70.33	myRun
1	Q0	33272578709794816	10	70.33	myRun


First ten answers to question 25:

25	Q0	29540081047961602	1	177.79	myRun
25	Q0	34718841359568896	2	165.68	myRun
25	Q0	29262243438792704	3	165.68	myRun
25	Q0	33096241340489729	4	154.29	myRun
25	Q0	32714775704113152	5	153.57	myRun
25	Q0	29509351093833728	6	149.63	myRun
25	Q0	32365411118878720	7	149.27	myRun
25	Q0	32481505913602048	8	137.09	myRun
25	Q0	29222359915302912	9	135.37	myRun
25	Q0	30809302881017857	10	133.34	myRun


In each of these cases the confidence was much higher for the first few choices and then quickly dropped off (by almost 40 points).
This is accurate as there are often a few results which really stand out for each query. For example some of the best results have words
repeated multiple times in the query (see result number 1 for query 1). Mathematically this query best matches the first result.

