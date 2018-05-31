package Corpus;

import Dictionary.Word;
import Language.TurkishLanguage;

import java.util.ArrayList;

public class TurkishSplitter implements SentenceSplitter{

    private boolean contains(String s, char character){
        return s.contains("" + character);
    }

    private boolean listContains(String currentWord){
        String[] shortcuts = {"alb", "bnb", "bkz", "bşk", "co", "dr", "dç", "der", "em", "gn",
                "hz", "kd", "kur", "kuv", "ltd", "md", "mr", "mö", "muh", "müh",
                "no", "öğr", "op", "opr", "org", "sf", "tuğ", "uzm", "vb", "vd",
                "yön", "yrb", "yrd", "üniv", "fak", "prof", "dz", "yd", "krm", "gen",
                "pte", "p", "av", "II", "III", "IV", "VI", "VII", "VIII", "IX",
                "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
                "XX", "tuğa", "plt", "tğm", "tic", "srv", "bl", "dipl", "not", "min",
                "cul", "san", "rzv", "or", "kor", "tüm", "st", "sn", "fr", "pl",
                "ka", "tk", "ko", "vs", "yard", "bknz", "doç", "gör", "müz", "oyn",
                "m", "s", "kr", "ms", "hv", "uz", "re", "ph", "mc", "ed",
                "km", "yb", "bk", "jr", "bn", "os", "mrs", "bld", "sen", "alm",
                "sir", "ord", "dir", "yay", "man", "brm", "edt", "dec", "mah", "cad",
                "vol", "kom", "sok", "apt", "elk", "mad", "ort", "cap", "ste", "exc",
                "ef"};
        for (String shortcut : shortcuts){
            if (currentWord.equalsIgnoreCase(shortcut)){
                return true;
            }
        }
        return false;
    }

    private boolean isNextCharUpperCaseOrDigit(String line, int i){
        while (i < line.length() && (line.charAt(i) == ' ' || contains(SEPARATORS, line.charAt(i)))){
            i++;
        }
        if (i == line.length() || contains(TurkishLanguage.UPPERCASE_LETTERS + TurkishLanguage.DIGITS + "-", line.charAt(i))){
            return true;
        } else {
            return false;
        }
    }

    private boolean isPreviousWordUpperCase(String line, int i){
        while (i >= 0 && (line.charAt(i) == ' ' || contains(TurkishLanguage.LOWERCASE_LETTERS + "qxw", line.charAt(i)))){
            i--;
        }
        if (i == -1 || contains(TurkishLanguage.UPPERCASE_LETTERS + "QWX", line.charAt(i))){
            return true;
        } else {
            return false;
        }
    }

    private boolean isNextCharUpperCase(String line, int i){
        while (i < line.length() && (line.charAt(i) == ' ')){
            i++;
        }
        if (i == line.length() || contains(TurkishLanguage.UPPERCASE_LETTERS + "\"\'", line.charAt(i))){
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameShortcut(String currentWord){
        if (currentWord.length() == 1 && TurkishLanguage.UPPERCASE_LETTERS.contains(currentWord)){
            return true;
        }
        if (currentWord.length() == 3 && currentWord.charAt(1) == '.' && contains(TurkishLanguage.UPPERCASE_LETTERS, currentWord.charAt(2))){
            return true;
        }
        return false;
    }

    private String repeatControl(String word, boolean exceptionMode){
        if (exceptionMode){
            return word;
        }
        int i = 0;
        String result = "";
        while (i < word.length()){
            if (i < word.length() - 2 && word.charAt(i) == word.charAt(i + 1) && word.charAt(i) == word.charAt(i + 2)){
                while (i < word.length() - 1 && word.charAt(i) == word.charAt(i + 1)){
                    i++;
                }
            }
            result = result + word.charAt(i);
            i++;
        }
        return result;
    }

    private boolean isApostrophe(String line, int i){
        String apostropheLetters = TurkishLanguage.LETTERS + TurkishLanguage.EXTENDED_LANGUAGE_CHARACTERS + TurkishLanguage.DIGITS;
        if (i + 1 < line.length()){
            char previousChar = line.charAt(i - 1);
            char nextChar = line.charAt(i + 1);
            return contains(apostropheLetters, previousChar) && contains(apostropheLetters, nextChar);
        } else {
            return false;
        }
    }

    private boolean numberExistsBeforeAndAfter(String line, int i){
        if (i + 1 < line.length() && i > 0){
            char previousChar = line.charAt(i - 1);
            char nextChar = line.charAt(i + 1);
            return contains(TurkishLanguage.DIGITS, previousChar) && contains(TurkishLanguage.DIGITS, nextChar);
        } else {
            return false;
        }
    }

    private boolean isTime(String line, int i){
        if (i + 2 < line.length()){
            char previousChar = line.charAt(i - 1);
            char nextChar = line.charAt(i + 1);
            char twoNextChar = line.charAt(i + 2);
            return contains(TurkishLanguage.DIGITS, previousChar) && contains(TurkishLanguage.DIGITS, nextChar) && contains(TurkishLanguage.DIGITS, twoNextChar);
        } else {
            return false;
        }
    }

    public ArrayList<Sentence> split(String line) {
        boolean emailMode = false, webMode = false;
        int i = 0, specialQuotaCount = 0, roundParenthesisCount = 0, bracketCount = 0, curlyBracketCount = 0, quotaCount = 0, apostropheCount = 0;
        Sentence currentSentence = new Sentence();
        String currentWord = "";
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        while (i < line.length()){
            if (contains(SEPARATORS, line.charAt(i))){
                if (line.charAt(i) == '\'' && !currentWord.isEmpty() && isApostrophe(line, i)){
                    currentWord = currentWord + line.charAt(i);
                } else {
                    if (!currentWord.isEmpty()) {
                        currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
                    }
                    currentSentence.addWord(new Word("" + line.charAt(i)));
                    currentWord = "";
                    switch (line.charAt(i)){
                        case '{':
                            curlyBracketCount++;
                            break;
                        case '}':
                            curlyBracketCount--;
                            break;
                        case '“':
                            specialQuotaCount++;
                            break;
                        case '”':
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
                    if (line.charAt(i) == '"' && bracketCount == 0 && specialQuotaCount == 0 && curlyBracketCount == 0 && roundParenthesisCount == 0 && quotaCount == 0 && isNextCharUpperCaseOrDigit(line, i + 1)){
                        sentences.add(currentSentence);
                        currentSentence = new Sentence();
                    }
                }
            } else {
                if (contains(SENTENCE_ENDERS, line.charAt(i))) {
                    if (line.charAt(i) == '.' && currentWord.equalsIgnoreCase("www")){
                        webMode = true;
                    }
                    if (line.charAt(i) == '.' && !currentWord.isEmpty() && (webMode || emailMode || contains(TurkishLanguage.DIGITS, line.charAt(i - 1)))){
                        currentWord = currentWord + line.charAt(i);
                    } else {
                        if (line.charAt(i) == '.' && (listContains(currentWord) || isNameShortcut(currentWord))){
                            currentWord = currentWord + line.charAt(i);
                            currentSentence.addWord(new Word(currentWord));
                            currentWord = "";
                        } else {
                            if (!currentWord.isEmpty()){
                                currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
                            }
                            currentWord = "" + line.charAt(i);
                            do{
                                i++;
                            }while (i < line.length() && contains(SENTENCE_ENDERS, line.charAt(i)));
                            i--;
                            currentSentence.addWord(new Word(currentWord));
                            if (roundParenthesisCount == 0 && bracketCount == 0 && curlyBracketCount == 0 && quotaCount == 0){
                                if (i + 1 < line.length() && line.charAt(i + 1) == '\'' && apostropheCount == 1 && isNextCharUpperCaseOrDigit(line, i + 2)){
                                    currentSentence.addWord(new Word("'"));
                                    i++;
                                    sentences.add(currentSentence);
                                    currentSentence = new Sentence();
                                } else {
                                    if (i + 2 < line.length() && line.charAt(i + 1) == ' ' && line.charAt(i + 2) == '\'' && apostropheCount == 1 && isNextCharUpperCaseOrDigit(line, i + 3)){
                                        currentSentence.addWord(new Word("'"));
                                        i += 2;
                                        sentences.add(currentSentence);
                                        currentSentence = new Sentence();
                                    } else {
                                        if (isNextCharUpperCaseOrDigit(line, i + 1)){
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
                    if (line.charAt(i) == ' '){
                        emailMode = false;
                        webMode = false;
                        if (!currentWord.isEmpty()) {
                            currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
                            currentWord = "";
                        }
                    } else {
                        if (line.charAt(i) == '-' && !webMode && roundParenthesisCount == 0 && isNextCharUpperCase(line, i + 1) && !isPreviousWordUpperCase(line, i - 1)) {
                            if (!currentWord.isEmpty() && !TurkishLanguage.DIGITS.contains(currentWord)) {
                                currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
                            }
                            if (currentSentence.wordCount() > 0){
                                sentences.add(currentSentence);
                            }
                            currentSentence = new Sentence();
                            roundParenthesisCount = bracketCount = curlyBracketCount = quotaCount = specialQuotaCount = 0;
                            if (!currentWord.isEmpty() && currentWord.matches("\\d+")){
                                currentSentence.addWord(new Word(currentWord + " -"));
                            } else {
                                currentSentence.addWord(new Word("-"));
                            }
                            currentWord = "";
                        } else {
                            if (contains(PUNCTUATION_CHARACTERS, line.charAt(i)) || contains(TurkishLanguage.ARITHMETIC_CHARACTERS, line.charAt(i))){
                                if (line.charAt(i) == ':' && (currentWord.equalsIgnoreCase("http") || currentWord.equalsIgnoreCase("https"))){
                                    webMode = true;
                                }
                                if (webMode){
                                    //Constructing web address. Web address can contain both punctuation and arithmetic characters
                                    currentWord = currentWord + line.charAt(i);
                                } else {
                                    if (line.charAt(i) == ',' && numberExistsBeforeAndAfter(line, i)){
                                        currentWord = currentWord + line.charAt(i);
                                    } else {
                                        if (line.charAt(i) == ':' && isTime(line, i)){
                                            currentWord = currentWord + line.charAt(i);
                                        } else {
                                            if (line.charAt(i) == '-' && numberExistsBeforeAndAfter(line, i)) {
                                                currentWord = currentWord + line.charAt(i);
                                            } else{
                                                if (!currentWord.isEmpty()) {
                                                    currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
                                                }
                                                currentSentence.addWord(new Word("" + line.charAt(i)));
                                                currentWord = "";
                                            }
                                        }
                                    }
                                }
                            }  else {
                                if (line.charAt(i) == '@'){
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
        if (!currentWord.isEmpty()){
            currentSentence.addWord(new Word(repeatControl(currentWord, webMode || emailMode)));
        }
        if (currentSentence.wordCount() > 0){
            sentences.add(currentSentence);
        }
        return sentences;
    }
}
