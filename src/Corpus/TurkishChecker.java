package Corpus;

import Language.TurkishLanguage;

public class TurkishChecker implements LanguageChecker{

    public boolean isValidWord(String word) {
        String specialMeaningCharacters = "$\\_|@%#£§&><";
        String validCharacters = TurkishLanguage.LETTERS + TurkishLanguage.EXTENDED_LANGUAGE_CHARACTERS + TurkishLanguage.DIGITS + SentenceSplitter.SEPARATORS + SentenceSplitter.SENTENCE_ENDERS + TurkishLanguage.ARITHMETIC_CHARACTERS + SentenceSplitter.PUNCTUATION_CHARACTERS + specialMeaningCharacters;
        for (int i = 0; i < word.length(); i++){
            if (!validCharacters.contains("" + word.charAt(i))){
                System.out.println(word.charAt(i));
                return false;
            }
        }
        return true;
    }
}
