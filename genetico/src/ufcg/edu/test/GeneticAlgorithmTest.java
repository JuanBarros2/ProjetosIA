package ufcg.edu.test;

import org.junit.Assert;
import ufcg.edu.commons.Params;
import ufcg.edu.genetic.FitnessFunction;
import ufcg.edu.genetic.GeneticAlgorithm;
import ufcg.edu.genetic.OnFitnessComplete;

public class GeneticAlgorithmTest {
    private GeneticAlgorithm geneticAlgorithm;
    private FitnessFunction fitnessFunction = new FitnessFunction() {

        int count = 0;
        @Override
        public void getScore(Params individual, OnFitnessComplete listener) {
            listener.onComplete(0);
        }

        @Override
        public void writeGeneration(Integer score, Integer generation, String opponent) {
            
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