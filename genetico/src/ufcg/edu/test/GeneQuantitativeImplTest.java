package ufcg.edu.test;

import org.junit.Before;
import org.junit.Test;
import ufcg.edu.genetic.GeneQuantitativeImpl;

import static org.junit.Assert.*;

public class GeneQuantitativeImplTest {
    private GeneQuantitativeImpl geneDirection;
    private final Integer INITIAL_FORCE = 100;

    @Before
    public void setUp(){
        geneDirection = new GeneQuantitativeImpl(INITIAL_FORCE, 400);
    }

    @Test
    public void doMutation() {
        Integer aux = INITIAL_FORCE;
        for(int i = 0; i != 1000; i++){
            assertEquals(!geneDirection.getForce().equals(aux)+"", geneDirection.doMutation(), !geneDirection.getForce().equals(aux));
            aux = geneDirection.getForce();
        }
    }

    @Test
    public void getForce() {
        assertEquals(geneDirection.getForce(), INITIAL_FORCE);
    }

    @Test
    public void setForce() {
        geneDirection.setForce(100);
        assertEquals(geneDirection.getForce(), new Integer(100));
        geneDirection.setForce(101);
        assertEquals(geneDirection.getForce(), new Integer(101));
        geneDirection.setForce(-100);
        assertEquals(geneDirection.getForce(), new Integer(0));
        geneDirection.setForce(geneDirection.MAX_FORCE + 10);
        assertEquals(geneDirection.getForce(), geneDirection.MAX_FORCE);
        geneDirection.setForce(100);
        assertEquals(geneDirection.getForce(), new Integer(100));

    }

}