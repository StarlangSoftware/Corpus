package Corpus;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurkishSplitterTest {
    TurkishSplitter splitter;

    @Before
    public void setUp() {
        splitter = new TurkishSplitter();
    }

    @Test
    public void testSplit1() {
        assertEquals(14, splitter.split("Cin Ali, bak! " +
                "At. " +
                "Bak, Cin Ali, bak. " +
                "Bu at. " +
                "Baba, o atı bana al. " +
                "Cin Ali, bu at. " +
                "O da ot. " +
                "Baba, bu ata ot al. " +
                "Cin Ali, bu ot, o da at. " +
                "Otu al, ata ver. " +
                "Bak, Suna! " +
                "Cin Ali, ata ot verdi. " +
                "Su verdi. " +
                "Cin Ali, ata bir kova da su verdi.").size());
    }

    @Test
    public void testSplit2() {
        assertEquals(1, splitter.split("WWW.GOOGLE.COM").size());
    }

    @Test
    public void testSplit3() {
        assertEquals(1, splitter.split("www.google.com").size());
    }

    @Test
    public void testSplit4() {
        assertEquals(1, splitter.split("1.adımda ve 2.adımda ne yaptın").size());
        assertEquals(7, splitter.split("1.adımda ve 2.adımda ne yaptın").get(0).wordCount());
    }

    @Test
    public void testSplit5() {
        assertEquals(1, splitter.split("1. adımda ve 2. adımda ne yaptın").size());
        assertEquals(7, splitter.split("1. adımda ve 2. adımda ne yaptın").get(0).wordCount());
    }

    @Test
    public void testSplit6() {
        assertEquals(1, splitter.split("Burada II. Murat ve I. Ahmet oyun oynadı").size());
        assertEquals(8, splitter.split("Burada II. Murat ve I. Ahmet oyun oynadı").get(0).wordCount());
    }

}