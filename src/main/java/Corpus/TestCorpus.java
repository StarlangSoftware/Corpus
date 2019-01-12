package Corpus;

public class TestCorpus {

    private static void testSentenceSplit(){
        Corpus corpus = new Corpus("Data/SentenceSplit/koseyazilari.txt", new TurkishSplitter());
        corpus.writeToFile("koseyazilari.txt");
    }

    private static void semanticCorpus(){
    }

    private static void testWordFormats(){
        Corpus corpus = new Corpus("Data/Corpus/gazete.txt");
        corpus.writeToFile("koseyazilari-surface.txt", WordFormat.SURFACE);
        corpus.writeToFile("koseyazilari-letter2.txt", WordFormat.LETTER_2);
        corpus.writeToFile("koseyazilari-letter3.txt", WordFormat.LETTER_3);
        corpus.writeToFile("koseyazilari-letter4.txt", WordFormat.LETTER_4);
    }

    public static void main(String[] args){
        //semanticCorpus();
    }
}
