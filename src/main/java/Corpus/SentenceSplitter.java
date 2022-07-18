package Corpus;

import Dictionary.Word;
import Language.Language;
import Language.TurkishLanguage;

import java.util.ArrayList;

/**
 * An interface for declaring separators, sentence finishers and punctuations.
 * Also calls split method for splitting a given line.
 * SEPARATORS : ()[]{}"'״＂՛
 */
public abstract class SentenceSplitter {
    public static final String SEPARATORS = "\n()[]{}\"'\u05F4\uFF02\u055B’”‘“–\u00AD\u200B\t&\u2009\u202F\uFEFF";
    public static final String SENTENCE_ENDERS = ".?!…";
    public static final String PUNCTUATION_CHARACTERS = ",:;‚";

    protected abstract String upperCaseLetters();
    protected abstract String lowerCaseLetters();
    protected abstract String[] shortCuts();

    /**
     * The listContains method has a String array shortcuts which holds the possible abbreviations that might end with a '.' but not a
     * sentence finisher word. It also takes a String as an input and loops through the shortcuts array and returns
     * true if given String has any matching item in the shortcuts array.
     *
     * @param currentWord String input to check.
     * @return true if contains any abbreviations, false otherwise.
     */
    private boolean listContains(String currentWord) {
        for (String shortcut : shortCuts()) {
            if (currentWord.equalsIgnoreCase(shortcut)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The isNextCharUpperCaseOrDigit method takes a String line and an int i as inputs. First it compares each char in
     * the input line with " " and SEPARATORS ({@literal ()[]{}"'״＂՛}) and increment i by one until a mismatch or end of line.
     * <p>
     * When i equals to line length or contains one of the uppercase letters or digits it returns true, false otherwise.
     *
     * @param line String to check.
     * @param i    int defining starting index.
     * @return true if next char is uppercase or digit, false otherwise.
     */
    private boolean isNextCharUpperCaseOrDigit(String line, int i) {
        while (i < line.length() && (line.charAt(i) == ' ' || contains(SEPARATORS, line.charAt(i)))) {
            i++;
        }
        if (i == line.length() || contains(upperCaseLetters() + Language.DIGITS + "-", line.charAt(i))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The isPreviousWordUpperCase method takes a String line and an int i as inputs. First it compares each char in
     * the input line with " " and checks each char whether they are lowercase letters or one of the qxw. And decrement
     * input i by one till this condition is false.
     * <p>
     * When i equals to -1 or contains one of the uppercase letters or one of the QXW it returns true, false otherwise.
     *
     * @param line String to check.
     * @param i    int defining ending index.
     * @return true if previous char is uppercase or one of the QXW, false otherwise.
     */
    private boolean isPreviousWordUpperCase(String line, int i) {
        while (i >= 0 && (line.charAt(i) == ' ' || contains(lowerCaseLetters() + "qxw", line.charAt(i)))) {
            i--;
        }
        if (i == -1 || contains(upperCaseLetters() + "QWX", line.charAt(i))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The isNextCharUpperCase method takes a String line and an int i as inputs. First it compares each char in
     * the input line with " " and increment i by one until a mismatch or end of line.
     * <p>
     * When i equals to line length or contains one of the uppercase letters it returns true, false otherwise.
     *
     * @param line String to check.
     * @param i    int defining starting index.
     * @return true if next char is uppercase, false otherwise.
     */
    private boolean isNextCharUpperCase(String line, int i) {
        while (i < line.length() && (line.charAt(i) == ' ')) {
            i++;
        }
        if (i == line.length() || contains(upperCaseLetters() + "\"\'", line.charAt(i))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The isNameShortcut method takes a String word as an input. First, if the word length is 1, and currentWord
     * contains UPPERCASE_LETTERS letters than it returns true.
     * <p>
     * Secondly, if the length of the word is 3 (i.e it is a shortcut) and it has a '.' at its 1st index and
     * currentWord's 2nd  index is an uppercase letter it also returns true. (Ex : m.A)
     *
     * @param currentWord String input to check whether it is a shortcut.
     * @return true if given input is a shortcut, false otherwise.
     */
    private boolean isNameShortcut(String currentWord) {
        if (currentWord.length() == 1 && upperCaseLetters().contains(currentWord)) {
            return true;
        }
        if (currentWord.length() == 3 && currentWord.charAt(1) == '.' && contains(upperCaseLetters(), currentWord.charAt(2))) {
            return true;
        }
        return false;
    }

    /**
     * The repeatControl method takes a String word as an input, and a boolean exceptionMode and compress the repetitive chars. With
     * the presence of exceptionMode it directly returns the given word. Then it declares a counter i and loops till the end of the
     * given word. It compares the char at index i with the char at index (i+2) if they are equal then it compares the char at index i
     * with the char at index (i+1) and increments i by one and returns concatenated  result String with char at index i.
     *
     * @param word          String input.
     * @param exceptionMode boolean input for exceptional cases.
     * @return String result.
     */
    private String repeatControl(String word, boolean exceptionMode) {
        if (exceptionMode) {
            return word;
        }
        int i = 0;
        String result = "";
        while (i < word.length()) {
            if (i < word.length() - 2 && word.charAt(i) == word.charAt(i + 1) && word.charAt(i) == word.charAt(i + 2)) {
                while (i < word.length() - 1 && word.charAt(i) == word.charAt(i + 1)) {
                    i++;
                }
            }
            result = result + word.charAt(i);
            i++;
        }
        return result;
    }

    /**
     * The isApostrophe method takes a String line and an integer i as inputs. Initially declares a String apostropheLetters
     * which consists of abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ, âàáäãèéêëíîòóôûúqwxÂÈÉÊËÌÒÛQWX and 0123456789.
     * Then, it returns true if the result of contains method which checks the existence of previous char and next char
     * at apostropheLetters returns true, returns false otherwise.
     *
     * @param line String input to check.
     * @param i    index.
     * @return true if apostropheLetters contains previous char and next char, false otherwise.
     */
    private boolean isApostrophe(String line, int i) {
        String apostropheLetters = upperCaseLetters() + lowerCaseLetters() + Language.EXTENDED_LANGUAGE_CHARACTERS + Language.DIGITS;
        if (i > 0 && i + 1 < line.length()) {
            char previousChar = line.charAt(i - 1);
            char nextChar = line.charAt(i + 1);
            return contains(apostropheLetters, previousChar) && contains(apostropheLetters, nextChar);
        } else {
            return false;
        }
    }

    /**
     * The numberExistsBeforeAndAfter method takes a String line and an integer i as inputs. Then, it returns true if
     * the result of contains method, which compares the previous char and next char with 0123456789, returns true and
     * false otherwise.
     *
     * @param line String input to check.
     * @param i    index.
     * @return true if previous char and next char is a digit, false otherwise.
     */
    private boolean numberExistsBeforeAndAfter(String line, int i) {
        if (i > 0 && i + 1 < line.length()) {
            char previousChar = line.charAt(i - 1);
            char nextChar = line.charAt(i + 1);
            return contains(Language.DIGITS, previousChar) && contains(Language.DIGITS, nextChar);
        } else {
            return false;
        }
    }

    /**
     * The isTime method takes a String line and an integer i as inputs. Then, it returns true if
     * the result of the contains method, which compares the previous char, next char and two next chars with 0123456789,
     * returns true and false otherwise.
     *
     * @param line String input to check.
     * @param i    index.
     * @return true if previous char, next char and two next chars are digit, false otherwise.
     */
    private boolean isTime(String line, int i) {
        if (i > 0 && i + 2 < line.length()) {
            char previousChar = line.charAt(i - 1);
            char nextChar = line.charAt(i + 1);
            char twoNextChar = line.charAt(i + 2);
            return contains(Language.DIGITS, previousChar) && contains(Language.DIGITS, nextChar) && contains(Language.DIGITS, twoNextChar);
        } else {
            return false;
        }
    }

    /**
     * The contains method takes a String and a char input then check the given String contains the given char.
     *
     * @param s         String input to search for the char.
     * @param character Char input to look for in String.
     * @return true if char is found, false otherwise.
     */
    protected boolean contains(String s, char character) {
        return s.contains("" + character);
    }

    /**
     * The split method takes a String line as an input. Firstly it creates a new sentence as currentSentence a new {@link ArrayList}
     * as sentences. Then loops till the end of the line and checks some conditions;
     * If the char at ith index is a separator;
     * <p>
     * ' : assigns currentWord as currentWord'
     * { : increment the curlyBracketCount
     * } : decrement the curlyBracketCount
     * " : increment the specialQuotaCount
     * " : decrement the specialQuotaCount
     * ( : increment roundParenthesisCount
     * ) : decrement roundParenthesisCount
     * [ : increment bracketCount
     * ] : decrement bracketCount
     * " : assign quotaCount as 1- quotaCount
     * ' : assign apostropheCount as 1- apostropheCount
     * <p>
     * If the currentWord is not empty, it adds the currentWord after repeatControl to currentSentence.
     * <p>
     * If the char at index i is " and  bracketCount, specialQuotaCount, curlyBracketCount, roundParenthesisCount, and
     * quotaCount equal to 0 and also the next char is uppercase or digit, it adds currentSentence to sentences.
     * <p>
     * If the char at ith index is a sentence ender;
     * <p>
     * . and currentWord is www : assigns webMode as true. Ex: www.google.com
     * . and currentWord is a digit or in web or e-mail modes : assigns currentWord as currentWord+char(i) Ex: 1.
     * . and currentWord is a shortcut or an abbreviation : assigns currentWord as currentWord+char(i) and adds currentWord to currentSentence. Ex : bkz.
     * ' and next char is uppercase or digit: add word to currentSentence as ' and add currentSentence to sentences.
     *
     * <p>
     * If the char at index i is ' ', i.e space, add word to currentSentence and assign "" to currentSentence.
     * If the char at index i is -,  add word to currentSentence and add sentences when the wordCount of currentSentence greater than 0.
     * <p>
     * If the char at ith index is a punctuation;
     * : and if currentWord is "https" : assign webMode as true.
     * , and there exists a number before and after : assign currentWord as currentWord+char(i) Ex: 1,2
     * : and if line is a time : assign currentWord as currentWord+char(i) Ex: 12:14:24
     * - and there exists a number before and after : assign currentWord as currentWord+char(i) Ex: 12-1
     * {@literal @} : assign emailMode as true.
     *
     * @param line String input to split.
     * @return sentences {@link ArrayList} which holds split line.
     */
    public ArrayList<Sentence> split(String line) {
        boolean emailMode = false, webMode = false;
        int i = 0, specialQuotaCount = 0, roundParenthesisCount = 0, bracketCount = 0, curlyBracketCount = 0, quotaCount = 0, apostropheCount = 0;
        Sentence currentSentence = new Sentence();
        String currentWord = "";
        ArrayList<Sentence> sentences = new ArrayList<>();
        while (i < line.length()) {
            if (contains(SEPARATORS, line.charAt(i))) {
                if (line.charAt(i) == '\'' && !currentWord.isEmpty() && isApostrophe(line, i)) {
                    currentWord = currentWord + line.charAt(i);
                } else {
                    if (!currentWord.isEmpty()) {
                        currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
                    }
                    if (line.charAt(i) != '\n'){
                        currentSentence.addWord(new Word("" + line.charAt(i)));
                    }
                    currentWord = "";
                    switch (line.charAt(i)) {
                        case '{':
                            curlyBracketCount++;
                            break;
                        case '}':
                            curlyBracketCount--;
                            break;
                        case '\uFF02':
                            specialQuotaCount++;
                            break;
                        case '\u05F4':
                            specialQuotaCount--;
                            break;
                        case '“':
                            specialQuotaCount++;
                            break;
                        case '”':
                            specialQuotaCount--;
                            break;
                        case '‘':
                            specialQuotaCount++;
                            break;
                        case '’':
                            specialQuotaCount--;
                            break;
                        case '(':
                            roundParenthesisCount++;
                            break;
                        case ')':
                            roundParenthesisCount--;
                            break;
                        case '[':
                            bracketCount++;
                            break;
                        case ']':
                            bracketCount--;
                            break;
                        case '"':
                            quotaCount = 1 - quotaCount;
                            break;
                        case '\'':
                            apostropheCount = 1 - apostropheCount;
                            break;
                    }
                    if (line.charAt(i) == '"' && bracketCount == 0 && specialQuotaCount == 0 && curlyBracketCount == 0 &&
                            roundParenthesisCount == 0 && quotaCount == 0 && isNextCharUpperCaseOrDigit(line, i + 1)) {
                        sentences.add(currentSentence);
                        currentSentence = new Sentence();
                    }
                }
            } else {
                if (contains(SENTENCE_ENDERS, line.charAt(i))) {
                    if (line.charAt(i) == '.' && currentWord.equalsIgnoreCase("www")) {
                        webMode = true;
                    }
                    if (line.charAt(i) == '.' && !currentWord.isEmpty() && (webMode || emailMode || (contains(Language.DIGITS, line.charAt(i - 1))) && !isNextCharUpperCaseOrDigit(line, i + 1))) {
                        currentWord = currentWord + line.charAt(i);
                    }
                    if(line.charAt(i) == '.' && !currentWord.isEmpty() && contains(Language.DIGITS, line.charAt(i - 1)) && i+1 < line.length() && contains(lowerCaseLetters(),line.charAt(i + 1))) {
                        currentSentence.addWord(new Word(currentWord));
                        currentWord = "";
                    }
                    else {
                        if (line.charAt(i) == '.' && (listContains(currentWord) || isNameShortcut(currentWord))) {
                            currentWord = currentWord + line.charAt(i);
                            currentSentence.addWord(new Word(currentWord));
                            currentWord = "";
                        } else {
                            if (!currentWord.isEmpty()) {
                                currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
                            }
                            currentWord = "" + line.charAt(i);
                            do {
                                i++;
                            } while (i < line.length() && contains(SENTENCE_ENDERS, line.charAt(i)));
                            i--;
                            currentSentence.addWord(new Word(currentWord));
                            if (roundParenthesisCount == 0 && bracketCount == 0 && curlyBracketCount == 0 && quotaCount == 0) {
                                if (i + 1 < line.length() && line.charAt(i + 1) == '\'' && apostropheCount == 1 && isNextCharUpperCaseOrDigit(line, i + 2)) {
                                    currentSentence.addWord(new Word("'"));
                                    i++;
                                    sentences.add(currentSentence);
                                    currentSentence = new Sentence();
                                } else {
                                    if (i + 2 < line.length() && line.charAt(i + 1) == ' ' && line.charAt(i + 2) == '\'' && apostropheCount == 1 && isNextCharUpperCaseOrDigit(line, i + 3)) {
                                        currentSentence.addWord(new Word("'"));
                                        i += 2;
                                        sentences.add(currentSentence);
                                        currentSentence = new Sentence();
                                    } else {
                                        if (isNextCharUpperCaseOrDigit(line, i + 1)) {
                                            sentences.add(currentSentence);
                                            currentSentence = new Sentence();
                                        }
                                    }
                                }
                            }
                            currentWord = "";
                        }
                    }
                } else {
                    if (line.charAt(i) == ' ') {
                        emailMode = false;
                        webMode = false;
                        if (!currentWord.isEmpty()) {
                            currentSentence.addWord(new Word(repeatControl(currentWord, false)));
                            currentWord = "";
                        }
                    } else {
                        if (line.charAt(i) == '-' && !webMode && roundParenthesisCount == 0 && isNextCharUpperCase(line, i + 1) && !isPreviousWordUpperCase(line, i - 1)) {
                            if (!TurkishLanguage.DIGITS.contains(currentWord)) {
                                currentSentence.addWord(new Word(repeatControl(currentWord, emailMode)));
                            }
                            if (currentSentence.wordCount() > 0) {
                                sentences.add(currentSentence);
                            }
                            currentSentence = new Sentence();
                            roundParenthesisCount = bracketCount = curlyBracketCount = quotaCount = specialQuotaCount = 0;
                            if (!currentWord.isEmpty() && currentWord.matches("\\d+")) {
                                currentSentence.addWord(new Word(currentWord + " -"));
                            } else {
                                currentSentence.addWord(new Word("-"));
                            }
                            currentWord = "";
                        } else {
                            if (contains(PUNCTUATION_CHARACTERS, line.charAt(i)) || contains(Language.ARITHMETIC_CHARACTERS, line.charAt(i))) {
                                if (line.charAt(i) == ':' && (currentWord.equalsIgnoreCase("http") || currentWord.equalsIgnoreCase("https"))) {
                                    webMode = true;
                                }
                                if (webMode) {
                                    //Constructing web address. Web address can contain both punctuation and arithmetic characters
                                    currentWord = currentWord + line.charAt(i);
                                } else {
                                    if (line.charAt(i) == ',' && numberExistsBeforeAndAfter(line, i)) {
                                        currentWord = currentWord + line.charAt(i);
                                    } else {
                                        if (line.charAt(i) == ':' && isTime(line, i)) {
                                            currentWord = currentWord + line.charAt(i);
                                        } else {
                                            if (line.charAt(i) == '-' && numberExistsBeforeAndAfter(line, i)) {
                                                currentWord = currentWord + line.charAt(i);
                                            } else {
                                                if (!currentWord.isEmpty()) {
                                                    currentSentence.addWord(new Word(repeatControl(currentWord, emailMode)));
                                                }
                                                currentSentence.addWord(new Word("" + line.charAt(i)));
                                                currentWord = "";
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (line.charAt(i) == '@') {
                                    //Constructing e-mail address
                                    currentWord = currentWord + line.charAt(i);
                                    emailMode = true;
                                } else {
                                    currentWord = currentWord + line.charAt(i);
                                }
                            }
                        }
                    }
                }
            }
            i++;
        }
        if (!currentWord.isEmpty()) {
            currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
        }
        if (currentSentence.wordCount() > 0) {
            sentences.add(currentSentence);
        }
        return sentences;
    }

}
