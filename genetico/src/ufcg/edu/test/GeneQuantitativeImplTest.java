package ufcg.edu.test;

import org.junit.Before;
import org.junit.Test;
import ufcg.edu.genetic.GeneQuantitativeImpl;

import static org.junit.Assert.*;

public class GeneQuantitativeImplTest {
    private GeneQuantitativeImpl geneDirection;
    private final Integer INITIAL_VALUE = 100;

    @Before
    public void setUp() {
        geneDirection = new GeneQuantitativeImpl(INITIAL_VALUE, 400);
    }

    @Test
    public void doMutation() {
        Integer aux = INITIAL_VALUE;
        for (int i = 0; i != 1000; i++) {
            assertEquals(!geneDirection.getValue().equals(aux) + "", geneDirection.doMutation(), !geneDirection.getValue().equals(aux));
            aux = geneDirection.getValue();
        }
    }

    @Test
    public void getValue() {
        assertEquals(geneDirection.getValue(), INITIAL_VALUE);
    }

    @Test
    public void setValue() {
        geneDirection.setValue(100);
        assertEquals(geneDirection.getValue(), new Integer(100));
        geneDirection.setValue(101);
        assertEquals(geneDirection.getValue(), new Integer(101));
        geneDirection.setValue(-100);
        assertEquals(geneDirection.getValue(), new Integer(0));
        geneDirection.setValue(geneDirection.MAX_VALUE + 10);
        assertEquals(geneDirection.getValue(), geneDirection.MAX_VALUE);
        geneDirection.setValue(100);
        assertEquals(geneDirection.getValue(), new Integer(100));

    }

}