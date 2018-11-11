package ufcg.edu.genetic;

import java.util.Arrays;

public class GeneticAlgorithm {

    private Individual[] population;
    private Integer generationCount;
    private FitnessFunction fitnessFunction;

    public GeneticAlgorithm(FitnessFunction fitnessFunction){
        this.population = new Individual[2];
        this.population[0] = randomizeIndividual();
        this.population[1] = this.population[0].clone();
        this.generationCount = 0;
        this.fitnessFunction = fitnessFunction;
    }

    private Individual randomizeIndividual(){
        return new Individual(Arrays.asList(
                new Chromossome(Arrays.asList(
                        new GeneQuantitativeImpl(359),
                        new GeneQuantitativeImpl(100)
                )),
                new Chromossome(Arrays.asList(
                        new GeneQuantitativeImpl(359)
                ))
        ));
    }

    /**
     * Realiza a avaliação da população de acordo com a população
     * disponível. Para isso, ele roda a função fitness em todos os
     * indivíduos da população
     * @return indivíduo com melhor pontuação.
     */
    public Individual getBestIndividual(){
        for(Individual aux: population){
            aux.setScore(this.fitnessFunction.getScore(aux));
        }
        return this.getBest();
    }

    /**
     * Inicia o processo de aprendizagem com algoritmo genético.
     */
    public void runAlgorithm(Integer generationCountMax){
        while(generationCount < generationCountMax){
            population[0] = getBestIndividual();

            population[1] = population[0].clone();

            population[1].mutation();
            this.generationCount++;
        }
    }

    public void reset(){
        this.generationCount = 0;
    }

    /**
     * Retorna o melhor indivíduo nessa população. Caso não exista mais de um,
     * ele retornará o primeiro.
     * @return indivíduo com maior score
     */
    public Individual getBest(){
        Individual result = population[0];
        if(population[1] != null && population[0].compareTo(population[1]) < 0){
            result = population[1];
        }
        return result;
    }

    public Integer getGenerationCount() {
        return generationCount;
    }
}

