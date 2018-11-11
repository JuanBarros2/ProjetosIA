package ufcg.edu.commons;

import ufcg.edu.genetic.GeneQuantitativeImpl;

import java.io.Serializable;

public class Direction implements Serializable {
    private static final long serialVersionUID = 1L;

    public GeneQuantitativeImpl degress, prob;

    public Direction(){
        this.degress = new GeneQuantitativeImpl(359);
        this.prob = new GeneQuantitativeImpl(100);
    }

    public Direction(GeneQuantitativeImpl degress, GeneQuantitativeImpl prob){
        this.degress = degress;
        this.prob = prob;
    }

    public Integer getDegrees() {
        return degress.getValue();
    }

    public Integer getProb() {
        return prob.getValue();
    }
}
