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
    public void testSplit() {
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

}