package Corpus;

import java.util.ArrayList;

public class Paragraph {

    private ArrayList<Sentence> sentences;

    /**
     * A constructor of {@link Paragraph} class which creates an {@link ArrayList} sentences.
     */
    public Paragraph() {
        sentences = new ArrayList<Sentence>();
    }

    /**
     * The addSentence method adds given sentence to sentences {@link ArrayList}.
     *
     * @param s Sentence type input to add sentences.
     */
    public void addSentence(Sentence s) {
        sentences.add(s);
    }

    /**
     * The sentenceCount method finds the size of the {@link ArrayList} sentences.
     *
     * @return the size of the {@link ArrayList} sentences.
     */
    public int sentenceCount() {
        return sentences.size();
    }

    /**
     * The getSentence method finds the sentence from sentences {@link ArrayList} at given index.
     *
     * @param index used to get a sentence.
     * @return sentence at given index.
     */
    public Sentence getSentence(int index) {
        return sentences.get(index);
    }

}
