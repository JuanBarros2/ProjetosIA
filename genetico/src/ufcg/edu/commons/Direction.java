package ufcg.edu.commons;

import ufcg.edu.genetic.GeneQuantitativeImpl;

import java.io.Serializable;

public class Direction implements Serializable {
    private static final long serialVersionUID = 1L;

    public GeneQuantitativeImpl degress, prob;

    public Direction(Integer degress, Integer prob){
        this.degress = new GeneQuantitativeImpl(degress,360);
        this.prob = new GeneQuantitativeImpl(prob, 100);
    }

    public Direction(GeneQuantitativeImpl degress, GeneQuantitativeImpl prob){
        this.degress = degress;
        this.prob = prob;
    }

    public Integer getDegrees() {
        return degress.getForce();
    }

    public Integer getProb() {
        return prob.getForce();
    }
}
