package Corpus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CorpusStream {

    private BufferedReader br;

    public CorpusStream(String fileName) {
        try{
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sentence getSentence(){
        try{
            String line = br.readLine();
            if (line != null){
                return new Sentence(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sentence> getSentenceBatch(int lineCount){
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        try{
            for (int i = 0; i < lineCount; i++){
                String line = br.readLine();
                if (line != null){
                    sentences.add(new Sentence(line));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sentences;
    }

}
