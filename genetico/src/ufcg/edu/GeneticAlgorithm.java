package ufcg.edu;

import java.util.List;

public class GeneticAlgorithm {

    Individual[] population;
    Integer generationCount;

    public GeneticAlgorithm(){
        population = new Individual[2];
        generationCount = 0;
    }

    public Individual nextGeneration(){
        generationCount++;
        
        return getBest();
    }

    public Individual getBest(){
        Individual result = population[0];
        if(population[1] != null && population[0].compareTo(population[1]) < 0){
            result = population[1];
        }
        return result;
    }
}
