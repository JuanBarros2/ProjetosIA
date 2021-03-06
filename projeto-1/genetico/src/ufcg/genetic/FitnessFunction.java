package ufcg.genetic;

import ufcg.commons.Params;

/**
 * Representa uma função que será chamada para avaliar os indivíduos.
 */
public interface FitnessFunction {
    /**
     * Retorna um score em inteiro que representa a pontuação de um
     * dado indivíduo ao ser avaliado pela função fitness.
     *
     * @param individual que será avaliado pela função
     * @return valor inteiro correspondente ao score.
     */
    Integer getScore(Params individual, OnFitnessComplete listener);

    void writeGeneration(Integer score, Integer generation);
}
