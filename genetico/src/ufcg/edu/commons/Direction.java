package ufcg.edu.commons;

import ufcg.edu.genetic.Gene;
import ufcg.edu.genetic.GeneQuantitativeImpl;

import java.io.Serializable;

public class Direction implements Serializable, Gene {
    private static final long serialVersionUID = 1L;

    public GeneQuantitativeImpl degress, step;

    public Direction(){
        this.degress = new GeneQuantitativeImpl(359);
        this.step = new GeneQuantitativeImpl(100);
    }

    public Direction(GeneQuantitativeImpl degress, GeneQuantitativeImpl prob){
        this.degress = degress;
        this.step = prob;
    }

    public Integer getDegrees() {
        return degress.getValue();
    }

    public Integer getStep() {
        return step.getValue();
    }

    @Override
    public boolean doMutation() {
        return false;
    }

    @Override
    public Object getValue() {
        return null;
    }
}
