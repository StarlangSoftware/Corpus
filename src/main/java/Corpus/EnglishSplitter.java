package Corpus;

import Language.EnglishLanguage;

public class EnglishSplitter extends SentenceSplitter{

    @Override
    protected String upperCaseLetters() {
        return EnglishLanguage.UPPERCASE_LETTERS;
    }

    @Override
    protected String lowerCaseLetters() {
        return EnglishLanguage.LOWERCASE_LETTERS;
    }

    /**
     * Returns shortcut words in English language.
     * @return Shortcut words in English language.
     */
    @Override
    protected String[] shortCuts() {
        return new String[]{"dr", "prof", "org", "II", "III", "IV", "VI", "VII", "VIII", "IX",
                "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
                "XX", "min", "km", "jr", "mrs", "sir"};
    }
}
