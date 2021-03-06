package ufcg.genetic;

import ufcg.commons.Params;

public class GeneticAlgorithm {


    private Params[] population;
    private Integer generationCount;
    private FitnessFunction fitnessFunction;
    private boolean isRunning = false;

    public GeneticAlgorithm(FitnessFunction fitnessFunction) {
        this.population = new Params[2];
        this.population[0] = new Params();
        this.population[1] = this.population[0].clone();
        this.generationCount = 0;
        this.fitnessFunction = fitnessFunction;
    }

    /**
     * Realiza a avaliação da população de acordo com a população
     * disponível. Para isso, ele roda a função fitness em todos os
     * indivíduos da população
     *
     * @return indivíduo com melhor pontuação.
     */
    private Params getBestIndividual(){
        isRunning = true;
        population[1].setScore(this.fitnessFunction.getScore(population[1], null));
        return this.getBest();
    }

    /**
     * Inicia o processo de aprendizagem com algoritmo genético.
     */
    public void runAlgorithm(Integer generationCountMax){
        System.out.println("Rodando algoritmo genético para "+generationCountMax +" gerações");
        while(generationCount < generationCountMax){
            System.out.println("Iniciando geração " + generationCount);
            population[0] = getBestIndividual();

            population[1] = population[0].clone();

            while (!population[1].mutation()) {
                continue;
            }

            this.generationCount++;
            this.fitnessFunction.writeGeneration(population[0].getScore(), generationCount);
        }

        System.out.println("Algoritmo finalizado com os parametros finais salvos em arquivo");
    }

    public void reset() {
        this.generationCount = 0;
    }

    /**
     * Retorna o melhor indivíduo nessa população. Caso não exista mais de um,
     * ele retornará o primeiro.
     *
     * @return indivíduo com maior score
     */
    public Params getBest() {
        Params result = population[0];
        if(population[0].compareTo(population[1]) < 0){
            result = population[1];
        }
        System.out.println("O melhor indivíduo foi: " + result);
        return result;
    }

    public Integer getGenerationCount() {
        return generationCount;
    }
}

