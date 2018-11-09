package ufcg.edu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneDirectionImplTest {
    private GeneDirectionImpl geneDirection;
    private final Integer INITIAL_FORCE = 100;

    @Before
    public void setUp(){
        geneDirection = new GeneDirectionImpl(INITIAL_FORCE);
    }

    @Test
    public void doMutation() {
        Integer aux = INITIAL_FORCE;
        for(int i = 0; i != 50; i++){
            assertEquals(geneDirection.doMutation(), !geneDirection.getForce().equals(aux));
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