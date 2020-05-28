package Corpus;

import Dictionary.Word;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SentenceTest {
    Sentence sentence;

    @Before
    public void setUp() throws Exception {
        sentence = new Sentence();
        sentence.addWord(new Word("ali"));
        sentence.addWord(new Word("topu"));
        sentence.addWord(new Word("at"));
        sentence.addWord(new Word("mehmet"));
        sentence.addWord(new Word("ayşeyle"));
        sentence.addWord(new Word("gitti"));
    }

    @Test
    public void testGetWord() {
        assertEquals(new Word("ali"), sentence.getWord(0));
        assertEquals(new Word("at"), sentence.getWord(2));
        assertEquals(new Word("gitti"), sentence.getWord(5));
    }

    @Test
    public void testGetIndex() {
        assertEquals(0, sentence.getIndex(new Word("ali")));
        assertEquals(2, sentence.getIndex(new Word("at")));
        assertEquals(5, sentence.getIndex(new Word("gitti")));
    }

    @Test
    public void testWordCount() {
        assertEquals(6, sentence.wordCount());
    }

    @Test
    public void testCharCount() {
        assertEquals(27, sentence.charCount());
    }
}