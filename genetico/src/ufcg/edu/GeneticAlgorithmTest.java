package ufcg.edu;

public class GeneticAlgorithmTest {
    private GeneticAlgorithm geneticAlgorithm;

    @org.junit.Before
    public void setUp() throws Exception {
        this.geneticAlgorithm = new GeneticAlgorithm(new FitnessFunction() {
            int count = 0;
            @Override
            public Integer getScore(Individual individual) {
                return ++count;
            }
        });
    }


    @org.junit.Test
    public void runAlgorithm() {
    }

    @org.junit.Test
    public void getBest() {
    }
}