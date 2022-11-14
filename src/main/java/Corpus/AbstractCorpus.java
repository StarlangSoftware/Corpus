package Corpus;

public abstract class AbstractCorpus {

    protected String fileName;

    public abstract void open();
    public abstract void close();
    public abstract Sentence getSentence();
}
