package ufcg.edu.test;

import org.junit.Assert;
import ufcg.edu.commons.Params;
import ufcg.edu.genetic.FitnessFunction;
import ufcg.edu.genetic.GeneticAlgorithm;
import ufcg.edu.genetic.OnFitnessComplete;

import java.util.Random;

public class GeneticAlgorithmTest {

    private GeneticAlgorithm geneticAlgorithm;
    private FitnessFunction fitnessFunction = new FitnessFunction() {
        Random random = new Random();
        int count = 0;

        @Override
        public void getScore(Params individual, OnFitnessComplete listener) {
            new Thread(new Runnable() {
                @Override
                synchronized public void run() {
                    System.out.println("Função fitness rodada com sucesso");
                    listener.onComplete(random.nextInt(500));
                }
            }).start();
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