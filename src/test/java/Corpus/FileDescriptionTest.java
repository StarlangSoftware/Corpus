package Corpus;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileDescriptionTest {

    @Test
    public void testGetIndex() {
        FileDescription fileDescription = new FileDescription("mypath", "1234.train");
        assertEquals(1234, fileDescription.getIndex());
        fileDescription = new FileDescription("mypath", "0000.test");
        assertEquals(0, fileDescription.getIndex());
        fileDescription = new FileDescription("mypath", "0003.dev");
        assertEquals(3, fileDescription.getIndex());
        fileDescription = new FileDescription("mypath", "0020.train");
        assertEquals(20, fileDescription.getIndex());
        fileDescription = new FileDescription("mypath", "0304.dev");
        assertEquals(304, fileDescription.getIndex());
    }

    @Test
    public void testGetExtension() {
        FileDescription fileDescription = new FileDescription("mypath", "1234.train");
        assertEquals("train", fileDescription.getExtension());
        fileDescription = new FileDescription("mypath", "0000.test");
        assertEquals("test", fileDescription.getExtension());
        fileDescription = new FileDescription("mypath", "0003.dev");
        assertEquals("dev", fileDescription.getExtension());
    }

    @Test
    public void testGetFileName() {
        FileDescription fileDescription = new FileDescription("mypath", "0003.train");
        assertEquals("mypath/0003.train", fileDescription.getFileName());
        assertEquals("newpath/0003.train", fileDescription.getFileName("newpath"));
        assertEquals("newpath/0000.train", fileDescription.getFileName("newpath", 0));
        assertEquals("newpath/0020.train", fileDescription.getFileName("newpath", 20));
        assertEquals("newpath/0103.train", fileDescription.getFileName("newpath", 103));
        assertEquals("newpath/0000.dev", fileDescription.getFileName("newpath", 0, "dev"));
        assertEquals("newpath/0020.dev", fileDescription.getFileName("newpath", 20, "dev"));
        assertEquals("newpath/0103.dev", fileDescription.getFileName("newpath", 103, "dev"));
    }
}