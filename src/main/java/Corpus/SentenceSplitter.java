package Corpus;

import java.util.ArrayList;

/**
 * An interface for declaring separators, sentence finishers and punctuations.
 * Also calls split method for splitting a given line.
 * SEPARATORS : ()[]{}"'״＂՛
 */
public interface SentenceSplitter {
    String SEPARATORS = "()[]{}\"'\u05F4\uFF02\u055B";
    String SENTENCE_ENDERS = ".?!…";
    String PUNCTUATION_CHARACTERS = ",:;";

    ArrayList<Sentence> split(String line);

}
