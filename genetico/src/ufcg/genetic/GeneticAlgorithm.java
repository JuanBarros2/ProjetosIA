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
        this.fitnessFunction = fitnessFunction;
        getScore(population[0]);
        this.population[1] = this.population[0].clone();
        this.generationCount = 0;

    }


    private void getScore(Params p) {
        p.setScore(this.fitnessFunction.getScore(p));
    }

    /**
     * Inicia o processo de aprendizagem com algoritmo genético.
     */
    public void runAlgorithm(Integer generationCountMax){
        System.out.println("Rodando algoritmo genético para "+generationCountMax +" gerações");
        while(generationCount < generationCountMax){

            System.out.println("Iniciando geração " + generationCount);
            population[1] = population[0].clone();

            while (!population[1].mutation()) {
                continue;
            }
            getScore(population[1]);
            population[0] = getBest();
            this.fitnessFunction.writeGeneration(population[0].getScore(), generationCount);
            this.generationCount++;
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

