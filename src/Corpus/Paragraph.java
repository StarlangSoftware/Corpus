package Corpus;

import java.util.ArrayList;

public class Paragraph {

    private ArrayList<Sentence> sentences;

    public Paragraph(){
        sentences = new ArrayList<Sentence>();
    }

    public void addSentence(Sentence s){
        sentences.add(s);
    }

    public int sentenceCount(){
        return sentences.size();
    }

    public Sentence getSentence(int index){
        return sentences.get(index);
    }

}
