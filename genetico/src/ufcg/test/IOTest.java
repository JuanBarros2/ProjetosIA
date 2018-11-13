package ufcg.test;

import org.junit.Assert;
import org.junit.Test;
import ufcg.commons.IO;
import ufcg.commons.Params;

import java.util.Arrays;
import java.util.List;

public class IOTest {
    private IO<Params> io;
    private Params persist;

    @org.junit.Before
    public void setUp() {
        io = new IO<>();
        persist = new Params();
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
        Params params = io.read();
        Assert.assertEquals(io.read(), persist);
    }

}