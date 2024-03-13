package Corpus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CorpusStream extends AbstractCorpus{

    private BufferedReader br;

    public CorpusStream(String fileName) {
        this.fileName = fileName;
    }

    public void open(){
        try{
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr);
        } catch (IOException ignored) {
        }
    }

    public void close(){
        try{
            br.close();
        } catch (IOException ignored) {
        }
    }

    public Sentence getSentence(){
        try{
            String line = br.readLine();
            if (line != null){
                return new Sentence(line);
            }
        } catch (IOException ignored) {
        }
        return null;
    }

    public ArrayList<Sentence> getSentenceBatch(int lineCount){
        ArrayList<Sentence> sentences = new ArrayList<>();
        try{
            for (int i = 0; i < lineCount; i++){
                String line = br.readLine();
                if (line != null){
                    sentences.add(new Sentence(line));
                } else {
                    break;
                }
            }
        } catch (IOException ignored) {
        }
        return sentences;
    }

}
