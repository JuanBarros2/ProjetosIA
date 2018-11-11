package ufcg.edu.test;

import org.junit.Before;
import org.junit.Test;
import ufcg.edu.genetic.Chromossome;
import ufcg.edu.genetic.Gene;
import ufcg.edu.genetic.GeneQuantitativeImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ChromossomeTest {

    private Chromossome chromossome;
    private List genes = Arrays.asList(
            new GeneQuantitativeImpl(100, 100),
            new GeneQuantitativeImpl(0, 304),
            new GeneQuantitativeImpl(101),
            new GeneQuantitativeImpl(21, 212),
            new GeneQuantitativeImpl(10, 100)
    );

    @Before
    public void setUp() throws Exception {
        chromossome = new Chromossome(genes);
    }

    @Test
    public void mutation() {
        List<Gene> last = genes;

        for(int i = 0; i != 1000; i++) {
            assertEquals(i+"", chromossome.mutation(), !chromossome.getGenes().equals(last));
            last = chromossome.getGenes();
        }
    }

    @Test
    public void getGenes() {
        assertEquals(chromossome.getGenes(), genes);
    }
}