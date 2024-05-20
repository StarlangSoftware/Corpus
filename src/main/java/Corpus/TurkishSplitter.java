package Corpus;

import Language.TurkishLanguage;

public class TurkishSplitter extends SentenceSplitter {

    /**
     * Returns Turkish UPPERCASE letters.
     * @return Turkish UPPERCASE letters.
     */
    @Override
    protected String upperCaseLetters() {
        return TurkishLanguage.UPPERCASE_LETTERS;
    }

    /**
     * Returns Turkish lowercase letters.
     * @return Turkish lowercase letters.
     */
    @Override
    protected String lowerCaseLetters() {
        return TurkishLanguage.LOWERCASE_LETTERS;
    }

    /**
     * Returns shortcut words in Turkish language.
     * @return Shortcut words in Turkish language.
     */
    @Override
    protected String[] shortCuts() {
        return new String[]{"alb", "bnb", "bkz", "bşk", "co", "dr", "dç", "der", "em", "gn",
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
    }
}
