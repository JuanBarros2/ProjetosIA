package ufcg.test;

import org.junit.Assert;
import org.junit.Test;
import ufcg.commons.Params;
import ufcg.genetic.FitnessFunction;
import ufcg.genetic.GeneticAlgorithm;
import ufcg.genetic.OnFitnessComplete;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneticAlgorithmTest {

    private GeneticAlgorithm geneticAlgorithm;
    private FitnessFunction fitnessFunction = new FitnessFunction() {
        Random random = new Random();
        int count = 0;

        @Override
        public Integer getScore(Params individual, OnFitnessComplete listener) {
            count++;
            return random.nextInt(8000);
        }

        @Override
        public void writeGeneration(Integer score, Integer generation) {

            FileWriter writer = null;
            try {
                writer = new FileWriter("Gen.csv", true);
                writer.append(generation.toString());
                writer.append(",");
                writer.append(score.toString());
                writer.append(",");
                writer.append("\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };

    @org.junit.Before
    public void setUp() {
        this.geneticAlgorithm = new GeneticAlgorithm(fitnessFunction);
    }

    @Test
    public void geraDados(){
        geneticAlgorithm.runAlgorithm(100);
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