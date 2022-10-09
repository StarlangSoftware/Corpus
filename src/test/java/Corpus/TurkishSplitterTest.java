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

    @Test
    public void testSplit7() {
        assertEquals(1, splitter.split("1.87 cm boyunda ve 84 kg ağırlığındaydı").size());
        assertEquals(7, splitter.split("1.87 cm boyunda ve 84 kg ağırlığındaydı").get(0).wordCount());
    }

    @Test
    public void testSplit8() {
        assertEquals("AAA", splitter.split("AA piller, AAA pillerin yaklaşık üç kat kapasitesine sahiptir").get(0).getWord(3).getName());
        assertEquals("yakala", splitter.split("Topu atıp yakalaaaa diye bağırdı").get(0).getWord(2).getName());
    }

    @Test
    public void testSplit9() {
        assertEquals(1, splitter.split("Bunun yanı sıra erkek t-shirt modellerini klasik giyim tarzına uyarlayarak kullanmak da mümkündür").size());
        assertEquals(13, splitter.split("Bunun yanı sıra erkek t-shirt modellerini klasik giyim tarzına uyarlayarak kullanmak da mümkündür").get(0).wordCount());
        assertEquals(1, splitter.split("USB-C, USB-A’ya göre çok daha yüksek hızlarda aktarım sağlayabilir").size());
        assertEquals(10, splitter.split("USB-C, USB-A’ya göre çok daha yüksek hızlarda aktarım sağlayabilir").get(0).wordCount());
    }
}