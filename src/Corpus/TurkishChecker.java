package Corpus;

import Language.TurkishLanguage;

public class TurkishChecker implements LanguageChecker {

    /**
     * The isValidWord method takes an input String as a word than define all valid characters as a validCharacters String which has
     * letters (abcçdefgğhıijklmnoöprsştuüvyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ),
     * extended language characters (âàáäãèéêëíîòóôûúqwxÂÈÉÊËÌÒÛQWX),
     * digits (0123456789),
     * separators ({@literal ()[]{}"'״＂՛}),
     * sentence enders (.?!…),
     * arithmetic chars (+-/=\*),
     * punctuation chars (,:;),
     * special-meaning chars
     * <p>
     * Then, loops through input word's each char and if a char in word does not in the validCharacters string it returns
     * false, true otherwise.
     *
     * @param word String to check validity.
     * @return true if each char in word is valid, false otherwise.
     */
    public boolean isValidWord(String word) {
        String specialMeaningCharacters = "$\\_|@%#£§&><";
        String validCharacters = TurkishLanguage.LETTERS + TurkishLanguage.EXTENDED_LANGUAGE_CHARACTERS + TurkishLanguage.DIGITS + SentenceSplitter.SEPARATORS + SentenceSplitter.SENTENCE_ENDERS + TurkishLanguage.ARITHMETIC_CHARACTERS + SentenceSplitter.PUNCTUATION_CHARACTERS + specialMeaningCharacters;
        for (int i = 0; i < word.length(); i++) {
            if (!validCharacters.contains("" + word.charAt(i))) {
                System.out.println(word.charAt(i));
                return false;
            }
        }
        return true;
    }
}
