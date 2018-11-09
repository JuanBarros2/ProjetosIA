package ufcg.edu;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class IndividualTest {
    Individual individual;
    List<Gene> genes;

    @Before
    public void setUp() throws Exception {
        individual = new Individual(
                Arrays.asList(new GeneDirectionImpl(100), new GeneDirectionImpl(100))
        );
    }

    @Test
    public void mutation() {
    }

    @Test
    public void getGenes() {
    }

    @Test
    public void isMutated() {
    }

    @Test
    public void setMutated() {
    }

    @Test
    public void getScore() {
    }

    @Test
    public void setScore() {
    }

    @Test
    public void compareTo() {
    }

}