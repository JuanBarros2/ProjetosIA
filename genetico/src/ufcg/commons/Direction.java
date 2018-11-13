package ufcg.commons;

import ufcg.genetic.GeneQuantitativeImpl;

import java.io.Serializable;

public class Direction implements Serializable {
    private static final long serialVersionUID = 1L;

    public GeneQuantitativeImpl degress, step;


    public Direction() {
        this.degress = new GeneQuantitativeImpl(359,0,15,70);
        this.step = new GeneQuantitativeImpl(100,0,5,40);
    }

    public Direction(GeneQuantitativeImpl degress, GeneQuantitativeImpl prob) {
        this.degress = degress;
        this.step = prob;
    }

    public Integer getDegrees() {
        return degress.getValue();
    }

    public Integer getStep() {
        return step.getValue();
    }

    public boolean doMutation() {
        boolean result = false;
        result |= this.degress.doMutation();
        result |= this.step.doMutation();
        return result;

    }
}
