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

    /**
     * An empty constructor of {@link Sentence} class. Creates an {@link ArrayList} of words.
     */
    public Sentence() {
        words = new ArrayList<Word>();
    }

    /**
     * The overridden clone method which creates a new sentence and clone words to this sentence.
     *
     * @return Sentence type sentence.
     */
    public Sentence clone() {
        Sentence s = new Sentence();
        for (Word w : words)
            s.addWord(w);
        return s;
    }

    /**
     * Another constructor of {@link Sentence} class which takes a file as an input. It reads each word in the file
     * and adds to words {@link ArrayList}.
     *
     * @param file input file to read words from.
     */
    public Sentence(File file) {
        words = new ArrayList<Word>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                words.add(new Word(sc.next()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The equals method takes a Sentence as an input. First compares the sizes of both {@link ArrayList} words and words
     * of the Sentence input. If they are not equal then it returns false. Than it compares each word in the {@link ArrayList}.
     * If they are equal, it returns true.
     *
     * @param s Sentence to compare.
     * @return true if words of two sentences are equal.
     */
    public boolean equals(Sentence s) {
        if (words.size() != s.words.size())
            return false;
        for (int i = 0; i < words.size(); i++)
            if (words.get(i).getName().compareTo(s.words.get(i).getName()) != 0)
                return false;
        return true;
    }

    /**
     * Another constructor of {@link Sentence} class which takes a sentence String as an input. It parses the sentence by
     * " " and adds each word to the newly created {@link ArrayList} words.
     *
     * @param sentence String input to parse.
     */
    public Sentence(String sentence) {
        String[] wordArray;
        words = new ArrayList<Word>();
        wordArray = sentence.split(" ");
        for (String word : wordArray) {
            if (!word.isEmpty()) {
                words.add(new Word(word));
            }
        }
    }

    /**
     * Another constructor of {@link Sentence} class with two inputs; a String sentence and a {@link LanguageChecker}
     * languageChecker. It parses a sentence by " " and then check the language considerations. If it is a valid word,
     * it adds this word to the newly created {@link ArrayList} words.
     *
     * @param sentence        String input.
     * @param languageChecker {@link LanguageChecker} type input.
     */
    public Sentence(String sentence, LanguageChecker languageChecker) {
        String[] wordArray;
        words = new ArrayList<Word>();
        wordArray = sentence.split(" ");
        for (String word : wordArray) {
            if (!word.isEmpty() && languageChecker.isValidWord(word)) {
                words.add(new Word(word));
            }
        }
    }

    /**
     * The getWord method takes an index input and gets the word at that index.
     *
     * @param index is used to get the word.
     * @return the word in given index.
     */
    public Word getWord(int index) {
        return words.get(index);
    }

    /**
     * The getWords method returns the {@link ArrayList} words.
     *
     * @return words ArrayList.
     */
    public ArrayList<Word> getWords() {
        return words;
    }

    /**
     * The getStrings method loops through the words {@link ArrayList} and adds each words' names to the newly created
     * {@link ArrayList} result.
     *
     * @return result ArrayList which holds names of the words.
     */
    public ArrayList<String> getStrings() {
        ArrayList<String> result = new ArrayList<>();
        for (Word word : words) {
            result.add(word.getName());
        }
        return result;
    }

    /**
     * The getIndex method takes a word as an input and finds the index of that word in the words {@link ArrayList} if it exists.
     *
     * @param word Word type input to search for.
     * @return index of the found input, -1 if not found.
     */
    public int getIndex(Word word) {
        int i = 0;
        for (Word w : words) {
            if (w.equals(word))
                return i;
            i++;
        }
        return -1;
    }

    /**
     * The wordCount method finds the size of the words {@link ArrayList}.
     *
     * @return the size of the words {@link ArrayList}.
     */
    public int wordCount() {
        return words.size();
    }

    /**
     * The addWord method takes a word as an input and adds this word to the words {@link ArrayList}.
     *
     * @param word Word to add words {@link ArrayList}.
     */
    public void addWord(Word word) {
        words.add(word);
    }

    /**
     * The charCount method finds the total number of chars in each word of words {@link ArrayList}.
     *
     * @return number of the chars in the whole sentence.
     */
    public int charCount() {
        int sum = 0;
        for (Word word : words)
            sum += word.charCount();
        return sum;
    }

    /**
     * The replaceWord method takes an index and a word as inputs. It removes the word at given index from words
     * {@link ArrayList} and then adds the given word to given index of words.
     *
     * @param i       index.
     * @param newWord to add the words {@link ArrayList}.
     */
    public void replaceWord(int i, Word newWord) {
        words.remove(i);
        words.add(i, newWord);
    }

    /**
     * The safeIndex method takes an index as an input and checks whether this index is between 0 and the size of the words.
     *
     * @param index is used to check the safety.
     * @return true if an index is safe, false otherwise.
     */
    public boolean safeIndex(int index) {
        return index >= 0 && index < words.size();
    }

    /**
     * The overridden toString method returns an accumulated string of each word in words {@link ArrayList}.
     *
     * @return String result which has all the word in words {@link ArrayList}.
     */
    public String toString() {
        if (words.size() > 0) {
            String result = words.get(0).toString();
            for (int i = 1; i < words.size(); i++) {
                result = result + " " + words.get(i);
            }
            return result;
        } else {
            return "";
        }
    }

    /**
     * The toWords method returns an accumulated string of each word's names in words {@link ArrayList}.
     *
     * @return String result which has all the names of each item in words {@link ArrayList}.
     */
    public String toWords() {
        if (words.size() > 0) {
            String result = words.get(0).getName();
            for (int i = 1; i < words.size(); i++) {
                result = result + " " + words.get(i).getName();
            }
            return result;
        } else {
            return "";
        }
    }

    /**
     * The writeToFile method writes the given file by using toString method.
     *
     * @param file to write in.
     */
    public void writeToFile(File file) {
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
