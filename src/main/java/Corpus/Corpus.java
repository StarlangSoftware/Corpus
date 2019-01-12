package Corpus;

import DataStructure.CounterHashMap;
import Dictionary.*;

import java.io.*;
import java.util.*;

public class Corpus {

    protected ArrayList<Sentence> sentences;
    protected CounterHashMap<Word> wordList;
    protected String fileName;

    /**
     * A constructor of {@link Corpus} class which creates new {@link ArrayList} for sentences and a {@link CounterHashMap}
     * for wordList.
     */
    public Corpus() {
        sentences = new ArrayList<Sentence>();
        wordList = new CounterHashMap<>();
    }

    /**
     * The emptyCopy method returns new Corpus.
     *
     * @return new {@link Corpus}.
     */
    public Corpus emptyCopy() {
        return new Corpus();
    }

    /**
     * Another constructor of {@link Corpus} class which takes a file name as an input. Then reads the input file line by line
     * and calls addSentence method with each read line.
     *
     * @param fileName String file name input that will be read.
     */
    public Corpus(String fileName) {
        this();
        this.fileName = fileName;
        int i = 0;
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {
                addSentence(new Sentence(line));
                line = br.readLine();
                i++;
                if (i % 10000 == 0) {
                    System.out.println("Read " + i + " sentences");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Another constructor of {@link Corpus} class which takes {@link SentenceSplitter}  as an input besides the file name.
     * It reads input file line by line and calls the sentenceSplitter method with each line, then calls addSentence method
     * with each sentence.
     *
     * @param fileName         String file name input that will be read.
     * @param sentenceSplitter {@link SentenceSplitter} type input.
     */
    public Corpus(String fileName, SentenceSplitter sentenceSplitter) {
        this();
        ArrayList<Sentence> sentences;
        int count = 0;
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {
                sentences = sentenceSplitter.split(line);
                for (Sentence s : sentences) {
                    addSentence(s);
                }
                count++;
                if (count % 1000 == 0) {
                    System.out.println(count);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Another constructor of {@link Corpus} class which also takes languageChecker input besides file name input.
     * It reads input file line by line and add each sentence also by using the languageChecker input which simply checks
     * the validity of the sentence.
     *
     * @param fileName        String file name input that will be read.
     * @param languageChecker {@link LanguageChecker} type input.
     */
    public Corpus(String fileName, LanguageChecker languageChecker) {
        this();
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {
                addSentence(new Sentence(line, languageChecker));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The combine method takes a {@link Corpus} as an input and adds each sentence of sentences {@link ArrayList}.
     *
     * @param corpus {@link Corpus} type input.
     */
    public void combine(Corpus corpus) {
        for (Sentence sentence : corpus.sentences) {
            addSentence(sentence);
        }
    }

    /**
     * The addSentence method takes a Sentence as an input. It adds given input to sentences {@link ArrayList} and loops
     * through the each word in sentence and puts these words into wordList {@link CounterHashMap}.
     *
     * @param s Sentence type input that will be added to sentences {@link ArrayList} and its words will be added to wordList
     *          {@link CounterHashMap}.
     */
    public void addSentence(Sentence s) {
        Word w;
        sentences.add(s);
        for (int i = 0; i < s.wordCount(); i++) {
            w = s.getWord(i);
            wordList.put(w);
        }
    }

    /**
     * The numberOfWords method loops through the sentences {@link ArrayList} and accumulates the number of words in sentence.
     *
     * @return size which holds the total number of words.
     */
    public int numberOfWords() {
        int size = 0;
        for (Sentence s : sentences) {
            size += s.wordCount();
        }
        return size;
    }

    /**
     * The contains method takes a String word as an input and checks whether wordList {@link CounterHashMap} has the
     * given word and returns true if so, otherwise returns false.
     *
     * @param word String input to check.
     * @return true if wordList has the given word, false otherwise.
     */
    public boolean contains(String word) {
        return wordList.containsKey(new Word(word));
    }

    /**
     * The addParagraph method takes a {@link Paragraph} type input. It gets the sentences in the given paragraph and
     * add these to the sentences {@link ArrayList} and the words in the sentences to the wordList {@link CounterHashMap}.
     *
     * @param p {@link Paragraph} type input to add sentences and wordList.
     */
    public void addParagraph(Paragraph p) {
        for (int i = 0; i < p.sentenceCount(); i++)
            addSentence(p.getSentence(i));
    }

    /**
     * Getter for the file name.
     *
     * @return file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Getter for the wordList.
     *
     * @return the keySet of wordList.
     */
    public Set<Word> getWordList() {
        return wordList.keySet();
    }

    /**
     * The wordCount method returns the size of the wordList {@link CounterHashMap}.
     *
     * @return the size of the wordList {@link CounterHashMap}.
     */
    public int wordCount() {
        return wordList.size();
    }

    /**
     * The getCount method returns the count value of given word.
     *
     * @param word Word type input to check.
     * @return the count value of given word.
     */
    public int getCount(Word word) {
        return wordList.get(word);
    }

    /**
     * The sentenceCount method returns the size of the sentences {@link ArrayList}.
     *
     * @return the size of the sentences {@link ArrayList}.
     */
    public int sentenceCount() {
        return sentences.size();
    }

    /**
     * Getter for getting a sentence at given index.
     *
     * @param index to get sentence from.
     * @return the sentence at given index.
     */
    public Sentence getSentence(int index) {
        return sentences.get(index);
    }

    /**
     * The maxSentenceLength method finds the sentence with the maximum number of words and returns this number.
     *
     * @return maximum length.
     */
    public int maxSentenceLength() {
        int maxLength = 0;
        for (Sentence s : sentences) {
            if (s.wordCount() + 1 > maxLength)
                maxLength = s.wordCount() + 1;
        }
        return maxLength;
    }

    /**
     * The getAllWordsAsArrayList method creates new {@link ArrayList} of ArrayLists and adds each word in each sentence of sentences
     * {@link ArrayList} into new {@link ArrayList}.
     *
     * @return newly created and populated {@link ArrayList}.
     */
    public ArrayList<ArrayList<Word>> getAllWordsAsArrayList() {
        ArrayList<ArrayList<Word>> allWords = new ArrayList<>();
        for (int i = 0; i < sentenceCount(); i++) {
            allWords.add(getSentence(i).getWords());
        }
        return allWords;
    }

    /**
     * The getAllWordsAsArray method creates new array of {@link ArrayList} and adds each word in each sentence of sentences
     * into this array.
     *
     * @return newly created and populated array.
     */
    public ArrayList<Word>[] getAllWordsAsArray() {
        ArrayList<Word>[] allWords = new ArrayList[sentenceCount()];
        for (int i = 0; i < allWords.length; i++) {
            allWords[i] = getSentence(i).getWords();
        }
        return allWords;
    }

    /**
     * The shuffleSentences method randomly shuffles sentences {@link ArrayList} with given seed value.
     *
     * @param seed value to randomize shuffling.
     */
    public void shuffleSentences(int seed) {
        Collections.shuffle(sentences, new Random(seed));
    }

    /**
     * The getTrainCorpus method takes two integer inputs foldNo and foldCount for determining train data size and count of fold respectively.
     * Initially creates a new empty Corpus, then finds the sentenceCount as N. Then, starting from the index 0 it loops through
     * the index (foldNo * N) / foldCount and add each sentence of sentences {@link ArrayList} to new Corpus. Later on,
     * starting from the index ((foldNo + 1) * N) / foldCount, it loops through the index N and add each sentence of
     * sentences {@link ArrayList} to new Corpus.
     *
     * @param foldNo    Integer input for train set size.
     * @param foldCount Integer input for counting fold.
     * @return the newly created and populated Corpus.
     */
    public Corpus getTrainCorpus(int foldNo, int foldCount) {
        Corpus trainCorpus = emptyCopy();
        int N = sentenceCount();
        for (int i = 0; i < (foldNo * N) / foldCount; i++) {
            trainCorpus.addSentence(sentences.get(i));
        }
        for (int i = ((foldNo + 1) * N) / foldCount; i < N; i++) {
            trainCorpus.addSentence(sentences.get(i));
        }
        return trainCorpus;
    }

    /**
     * The getTestCorpus method takes two integer inputs foldNo and foldCount for determining test data size and count of
     * fold respectively.. Initially creates a new empty Corpus, then finds the sentenceCount as N.
     * Then, starting from the index (foldNo * N) / foldCount it loops through the index ((foldNo + 1) * N) / foldCount and
     * add each sentence of sentences {@link ArrayList} to new Corpus.
     *
     * @param foldNo    Integer input for test size.
     * @param foldCount Integer input counting fold.
     * @return the newly created and populated Corpus.
     */
    public Corpus getTestCorpus(int foldNo, int foldCount) {
        Corpus testCorpus = emptyCopy();
        int N = sentenceCount();
        for (int i = (foldNo * N) / foldCount; i < ((foldNo + 1) * N) / foldCount; i++) {
            testCorpus.addSentence(sentences.get(i));
        }
        return testCorpus;
    }

    /**
     * The writeToFile method takes a String file name input and writes sentence of sentences {@link ArrayList} into this file.
     *
     * @param fileName file to write the sentences.
     */
    public void writeToFile(String fileName) {
        FileWriter fw;
        try {
            fw = new FileWriter(new File(fileName));
            for (Sentence sentence : sentences) {
                fw.write(sentence.toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The allSubStrings method takes a Word and an integer as inputs. If the length of the word's name is less than given input k,
     * it concatenates the each word's name with {@literal </s>} and adds to result which starts with {@literal <s>}. Else,  it finds out the
     * substring, concatenates with {@literal </s>} and adds to the String result.
     *
     * @param word Word type input to find substrings.
     * @param k    Integer for substring length.
     * @return String result that has all substrings.
     */
    private String allSubStrings(Word word, int k) {
        String result = "<s> ";
        if (word.getName().length() < k) {
            result += word.getName() + " </s>\n";
        } else {
            result += word.getName().substring(0, k);
            for (int j = 1; j < word.charCount() - k + 1; j++) {
                result += " " + word.getName().substring(j, j + k);
            }
            result += " </s>\n";
        }
        return result;
    }

    /**
     * An overloaded writeToFile method takes a String file name and {@link WordFormat} type format as inputs.
     * It writes sentence of sentences {@link ArrayList} into this file according to given format. There are 4 formats;
     * SURFACE, it directly writes words to file
     * LETTER_2, it writes words to file as 2-Grams.
     * LETTER_3, it writes words to file as 3-Grams.
     * LETTER_4, it writes words to file as 4-Grams.
     *
     * @param fileName file to write the sentences.
     * @param format {@link WordFormat} type input indicates N-Gram output.
     */
    public void writeToFile(String fileName, WordFormat format) {
        int k = 0;
        FileWriter fw;
        String result = "";
        try {
            fw = new FileWriter(new File(fileName));
            for (Sentence sentence : sentences) {
                switch (format) {
                    case SURFACE:
                        result = "<s> " + sentence.toString() + " </s>\n";
                        break;
                    case LETTER_2:
                        result = "";
                        for (int i = 0; i < sentence.wordCount(); i++) {
                            result += allSubStrings(sentence.getWord(i), 2);
                        }
                        break;
                    case LETTER_3:
                        result = "";
                        for (int i = 0; i < sentence.wordCount(); i++) {
                            result += allSubStrings(sentence.getWord(i), 3);
                        }
                        break;
                    case LETTER_4:
                        result = "";
                        for (int i = 0; i < sentence.wordCount(); i++) {
                            result += allSubStrings(sentence.getWord(i), 4);
                        }
                        break;
                }
                fw.write(result);
                k++;
                if (k % 10000 == 0) {
                    System.out.println("Written " + k + " sentences");
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
