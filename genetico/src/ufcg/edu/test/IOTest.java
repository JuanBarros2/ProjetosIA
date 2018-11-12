package ufcg.edu.test;

import org.junit.Assert;
import org.junit.Test;
import ufcg.edu.commons.IO;

import java.util.Arrays;
import java.util.List;

public class IOTest {
    private IO<List> io;
    private List<String> persist;

    @org.junit.Before
    public void setUp() {
        String FILEPATH = "resposta.txt";
        io = new IO<>(FILEPATH);
        persist = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");
    }

    @org.junit.After
    public void tearDown() {
    }

    @Test
    public void testWrite() {
        Assert.assertTrue(io.write(persist));
    }

    @Test
    public void testRead() {
        Assert.assertEquals(io.read(), persist);
    }

}