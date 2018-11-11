package ufcg.edu.genetic;

import java.util.ArrayList;

public class GeneticAlgorithm {

    private Individual[] population;
    private Integer generationCount;
    private FitnessFunction fitnessFunction;

    public GeneticAlgorithm(FitnessFunction fitnessFunction){
        this.population = new Individual[2];
        this.population[0] = new Individual(new ArrayList<>());
        this.population[1] = this.population[0].clone();
        this.generationCount = 0;
        this.fitnessFunction = fitnessFunction;
    }

    /**
     * Realiza a avaliação da população de acordo com a população
     * disponível. Para isso, ele roda a função fitness em todos os
     * indivíduos da população
     * @return indivíduo com melhor pontuação.
     */
    public Individual evaluatePopulation(){
        for(Individual aux: population){
            aux.setScore(this.fitnessFunction.getScore(aux));
        }
        return this.getBest();
    }

    /**
     * Inicia o processo de aprendizagem com algoritmo genético.
     */
    public void runAlgorithm(){
        while(true){
            population[1].mutation();

            population[0] = evaluatePopulation();
            population[1] = population[0].clone();
            this.generationCount++;
        }
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

/**
 * Representa uma função que será chamada para avaliar os indivíduos.
 */
interface FitnessFunction{
    /**
     * Retorna um score em inteiro que representa a pontuação de um
     * dado indivíduo ao ser avaliado pela função fitness.
     * @param individual que será avaliado pela função
     * @return valor inteiro correspondente ao score.
     */
    Integer getScore(Individual individual);
}
