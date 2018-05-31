package Corpus;

import Dictionary.Word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sentence {

    protected ArrayList<Word> words;

    public Sentence(){
        words = new ArrayList<Word>();
    }

    public Sentence clone(){
        Sentence s = new Sentence();
        for (Word w:words)
            s.addWord(w);
        return s;
    }

    public Sentence(File file){
        words = new ArrayList<Word>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()){
                words.add(new Word(sc.next()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Sentence s){
        if (words.size() != s.words.size())
            return false;
        for (int i = 0; i < words.size(); i++)
            if (words.get(i).getName().compareTo(s.words.get(i).getName()) != 0)
                return false;
        return true;
    }

    public Sentence(String sentence){
        String[] wordArray;
        words = new ArrayList<Word>();
        wordArray = sentence.split(" ");
        for (String word : wordArray){
            if (!word.isEmpty()){
                words.add(new Word(word));
            }
        }
    }

    public Sentence(String sentence, LanguageChecker languageChecker){
        String[] wordArray;
        words = new ArrayList<Word>();
        wordArray = sentence.split(" ");
        for (String word : wordArray){
            if (!word.isEmpty() && languageChecker.isValidWord(word)){
                words.add(new Word(word));
            }
        }
    }

    public Word getWord(int index){
        return words.get(index);
    }

    public ArrayList<Word> getWords(){
        return words;
    }

    public ArrayList<String> getStrings(){
        ArrayList<String> result = new ArrayList<>();
        for (Word word : words){
            result.add(word.getName());
        }
        return result;
    }

    public int getIndex(Word word){
        int i = 0;
        for (Word w: words){
            if (w.equals(word))
                return i;
            i++;
        }
        return -1;
    }

    public int wordCount(){
        return words.size();
    }

    public void addWord(Word word){
        words.add(word);
    }

    public int charCount(){
        int sum = 0;
        for (Word word: words)
            sum += word.charCount();
        return sum;
    }

    public void replaceWord(int i, Word newWord){
        words.remove(i);
        words.add(i, newWord);
    }

    public boolean safeIndex(int index){
        return index >= 0 && index < words.size();
    }

    public String toString(){
        if (words.size() > 0){
            String result = words.get(0).toString();
            for (int i = 1; i < words.size(); i++){
                result = result + " " + words.get(i);
            }
            return result;
        } else {
            return "";
        }
    }

    public String toWords(){
        if (words.size() > 0){
            String result = words.get(0).getName();
            for (int i = 1; i < words.size(); i++){
                result = result + " " + words.get(i).getName();
            }
            return result;
        } else {
            return "";
        }
    }

    public void writeToFile(File file){
        PrintWriter writer;
        try {
            writer = new PrintWriter(file, "UTF8");
            writer.println(toString());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
