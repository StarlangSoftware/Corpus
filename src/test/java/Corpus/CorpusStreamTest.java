package Corpus;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CorpusStreamTest {

    @org.junit.Test
    public void testNumberOfWords1() {
        int wordCount = 0;
        CorpusStream corpusStream = new CorpusStream("corpus.txt");
        Sentence sentence = corpusStream.getSentence();
        while (sentence != null){
            wordCount += sentence.wordCount();
            sentence = corpusStream.getSentence();
        }
        assertEquals(826680, wordCount);
        corpusStream.close();
    }

    @org.junit.Test
    public void testNumberOfWords2() {
        int wordCount = 0;
        CorpusStream corpusStream = new CorpusStream("corpus.txt");
        ArrayList<Sentence> sentences = corpusStream.getSentenceBatch(100);
        while (sentences.size() != 0){
            for (Sentence sentence : sentences){
                wordCount += sentence.wordCount();
            }
            sentences = corpusStream.getSentenceBatch(100);
        }
        assertEquals(826680, wordCount);
        corpusStream.close();
    }

}
