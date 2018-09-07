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

    private static void testParagraph(){
        Corpus corpus = new Corpus();
        String text = "Bu dönemde, üniversitelerin yanısıra, bilimin gelişimini büyük ölçüde etkilemiş olan iki manastır\n" +
                "düzeninin, yani tarikatın da ortaya çıktığı gözlenmektedir. 1209’da Fransisken Tarikatı (Gri\n" +
                "Kardeşler), 1215’de ise Dominiken Tarikatı (Siyah Kardeşler) kurulmuştur. Başlangıçta her iki\n" +
                "tarikat da dinsel amaçlara sahiptir; ancak giderek birincisi bilime, ikincisi ise felsefeye yönelmiştir.";
        Paragraph paragraph = new Paragraph(text, new TurkishSplitter());
        corpus.addParagraph(paragraph);
        for (Sentence s:corpus.sentences) {
            System.out.println(s);
        }

    }

    public static void main(String[] args){
        //semanticCorpus();
    }
}
