package Corpus;

import java.util.ArrayList;

public interface SentenceSplitter {
    String SEPARATORS = "()[]{}\"'”“’";
    String SENTENCE_ENDERS = ".?!…";
    String PUNCTUATION_CHARACTERS = ",:;";

    ArrayList<Sentence> split(String line);

}
