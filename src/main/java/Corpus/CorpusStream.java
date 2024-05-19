package Corpus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CorpusStream extends AbstractCorpus{

    private BufferedReader br;

    /**
     * Constructor for CorpusStream. CorpusStream is used for reading very large corpora that does not fit in memory as
     * a whole. For that reason, sentences are read one by one.
     * @param fileName File name of the corpus stream.
     */
    public CorpusStream(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Implements open method in AbstractCorpus. Initializes file reader.
     */
    public void open(){
        try{
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr);
        } catch (IOException ignored) {
        }
    }

    /**
     * Implements close method in AbstractCorpus. Closes the file reader.
     */
    public void close(){
        try{
            br.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Implements getSentence method in AbstractCorpus. Reads from the file buffer next sentence and returns it. If
     * there are no sentences to be read, returns null.
     * @return Next read sentence from file buffer or null.
     */
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

    /**
     * Reads more than one line (lineCount lines) from the buffer, stores them in an array list and returns that
     * array list. If there are no lineCount lines to be read, the method reads only available lines and returns them.
     * @param lineCount Maximum number of lines to read.
     * @return An array list of read lines.
     */
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
