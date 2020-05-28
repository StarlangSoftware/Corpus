package Corpus;

import Dictionary.Word;

import static org.junit.Assert.*;

public class CorpusTest {
    Corpus simpleCorpus, corpus;

    @org.junit.Before
    public void setUp() throws Exception {
        corpus = new Corpus("corpus.txt");
        simpleCorpus = new Corpus("simplecorpus.txt");
    }

    @org.junit.Test
    public void testNumberOfWords() {
        assertEquals(826680, corpus.numberOfWords());
        assertEquals(24, simpleCorpus.numberOfWords());
    }

    @org.junit.Test
    public void testContains() {
        assertTrue(corpus.contains("atatürk"));
        for (Word word : corpus.getWordList()){
            assertTrue(corpus.contains(word.getName()));
        }
        assertTrue(simpleCorpus.contains("mehmet"));
        for (Word word : simpleCorpus.getWordList()){
            assertTrue(simpleCorpus.contains(word.getName()));
        }
    }

    @org.junit.Test
    public void testWordCount() {
        assertEquals(98199, corpus.wordCount());
        assertEquals(12, simpleCorpus.wordCount());
    }

    @org.junit.Test
    public void testGetCount() {
        assertEquals(309, corpus.getCount(new Word("mustafa")));
        assertEquals(109, corpus.getCount(new Word("kemal")));
        assertEquals(122, corpus.getCount(new Word("atatürk")));
        assertEquals(4, simpleCorpus.getCount(new Word("ali")));
        assertEquals(3, simpleCorpus.getCount(new Word("gitti")));
        assertEquals(4, simpleCorpus.getCount(new Word("at")));
    }

    @org.junit.Test
    public void testSentenceCount() {
        assertEquals(50000, corpus.sentenceCount());
        assertEquals(5, simpleCorpus.sentenceCount());
    }

    @org.junit.Test
    public void testMaxSentenceLength() {
        assertEquals(1092, corpus.maxSentenceLength());
        assertEquals(6, simpleCorpus.maxSentenceLength());
    }
}