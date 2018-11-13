package ufcg.test;

import org.junit.Assert;
import ufcg.commons.Params;
import ufcg.genetic.FitnessFunction;
import ufcg.genetic.GeneticAlgorithm;

import java.util.Random;

public class GeneticAlgorithmTest {

    private GeneticAlgorithm geneticAlgorithm;
    private FitnessFunction fitnessFunction = new FitnessFunction() {
        Random random = new Random();
        int count = 0;

        @Override
        public int getScore(Params individual) {
            return random.nextInt(500);
        }

        @Override
        public void writeGeneration(Integer score, Integer generation) {
        }

    };

    @org.junit.Before
    public void setUp() throws Exception {
        this.geneticAlgorithm = new GeneticAlgorithm(fitnessFunction);
    }


    @org.junit.Test
    public void getGenerationCount() {
        geneticAlgorithm.runAlgorithm(100);
        Assert.assertEquals(geneticAlgorithm.getGenerationCount().intValue(), 100);
        geneticAlgorithm.reset();

        geneticAlgorithm.runAlgorithm(10);
        Assert.assertEquals(geneticAlgorithm.getGenerationCount().intValue(), 10);
        geneticAlgorithm.reset();

        geneticAlgorithm.runAlgorithm(0);
        Assert.assertEquals(geneticAlgorithm.getGenerationCount().intValue(), 0);
        geneticAlgorithm.reset();

        geneticAlgorithm.runAlgorithm(-100);
        Assert.assertEquals(geneticAlgorithm.getGenerationCount().intValue(), 0);
    }

}