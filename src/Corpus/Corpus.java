package Corpus;

import DataStructure.CounterHashMap;
import Dictionary.*;

import java.io.*;
import java.util.*;

public class Corpus {

    protected ArrayList<Sentence> sentences;
    protected CounterHashMap<Word> wordList;
    protected String fileName;

    public Corpus(){
        sentences = new ArrayList<Sentence>();
        wordList = new CounterHashMap<>();
    }

    public Corpus emptyCopy(){
        return new Corpus();
    }

    public Corpus(String fileName){
        this();
        this.fileName = fileName;
        int i = 0;
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null){
                addSentence(new Sentence(line));
                line = br.readLine();
                i++;
                if (i % 10000 == 0){
                    System.out.println("Read " + i + " sentences");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Corpus(String fileName, SentenceSplitter sentenceSplitter){
        this();
        ArrayList<Sentence> sentences;
        int count = 0;
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null){
                sentences = sentenceSplitter.split(line);
                for (Sentence s : sentences){
                    addSentence(s);
                }
                count++;
                if (count % 1000 == 0){
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

    public Corpus(String fileName, LanguageChecker languageChecker){
        this();
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null){
                addSentence(new Sentence(line, languageChecker));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void combine(Corpus corpus){
        for (Sentence sentence : corpus.sentences){
            addSentence(sentence);
        }
    }

    public void addSentence(Sentence s){
        Word w;
        sentences.add(s);
        for (int i = 0; i < s.wordCount(); i++){
            w = s.getWord(i);
            wordList.put(w);
        }
    }

    public int numberOfWords(){
        int size = 0;
        for (Sentence s: sentences){
            size += s.wordCount();
        }
        return size;
    }

    public boolean contains(String word){
        return wordList.containsKey(new Word(word));
    }

    public void addParagraph(Paragraph p){
        for (int i = 0; i < p.sentenceCount(); i++)
            addSentence(p.getSentence(i));
    }

    public String getFileName(){
        return fileName;
    }

    public Set<Word> getWordList(){
        return wordList.keySet();
    }

    public int wordCount(){
        return wordList.size();
    }

    public int getCount(Word word){
        return wordList.get(word);
    }

    public int sentenceCount(){
        return sentences.size();
    }

    public Sentence getSentence(int index){
        return sentences.get(index);
    }

    public int maxSentenceLength(){
        int maxLength = 0;
        for (Sentence s : sentences) {
            if (s.wordCount() + 1 > maxLength)
                maxLength = s.wordCount() + 1;
        }
        return maxLength;
    }

    public ArrayList<ArrayList<Word>> getAllWordsAsArrayList(){
        ArrayList<ArrayList<Word>> allWords = new ArrayList<>();
        for (int i = 0; i < sentenceCount(); i++){
            allWords.add(getSentence(i).getWords());
        }
        return allWords;
    }

    public ArrayList<Word>[] getAllWordsAsArray(){
        ArrayList<Word>[] allWords = new ArrayList[sentenceCount()];
        for (int i = 0; i < allWords.length; i++){
            allWords[i] = getSentence(i).getWords();
        }
        return allWords;
    }

    public void shuffleSentences(int seed){
        Collections.shuffle(sentences, new Random(seed));
    }

    public Corpus getTrainCorpus(int foldNo, int foldCount){
        Corpus trainCorpus = emptyCopy();
        int N = sentenceCount();
        for (int i = 0; i < (foldNo * N) / foldCount; i++){
            trainCorpus.addSentence(sentences.get(i));
        }
        for (int i = ((foldNo + 1) * N) / foldCount; i < N; i++){
            trainCorpus.addSentence(sentences.get(i));
        }
        return trainCorpus;
    }

    public Corpus getTestCorpus(int foldNo, int foldCount){
        Corpus testCorpus = emptyCopy();
        int N = sentenceCount();
        for (int i = (foldNo * N) / foldCount; i < ((foldNo + 1) * N) / foldCount; i++){
            testCorpus.addSentence(sentences.get(i));
        }
        return testCorpus;
    }

    public void writeToFile(String fileName) {
        FileWriter fw;
        try {
            fw = new FileWriter(new File(fileName));
            for (Sentence sentence : sentences){
                fw.write(sentence.toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String allSubStrings(Word word, int k){
        String result = "<s> ";
        if (word.getName().length() < k){
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

    public void writeToFile(String fileName, WordFormat format){
        int k = 0;
        FileWriter fw;
        String result = "";
        try {
            fw = new FileWriter(new File(fileName));
            for (Sentence sentence : sentences){
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
                if (k % 10000 == 0){
                    System.out.println("Written " + k + " sentences");
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
