package ufcg.edu.commons;

import ufcg.edu.genetic.Gene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Params implements Serializable, Comparable<Params> {
    private static final long serialVersionUID = 1L;

    /** Movement when nothing happens */
    List<Direction> defaultMovement;
    Integer score;
    List<Gene> defaultScan;

    public void setDefaultMovement(List<Direction> defaultMovement) {
        this.defaultMovement = defaultMovement;
    }

    public List<Direction> getDefaultMovement() {
        return defaultMovement;
    }

    public List<Gene> getDefaultScan() {
        return defaultScan;
    }

    public void setDefaultScan(List<Gene> defaultScan) {
        this.defaultScan = defaultScan;
    }

    public void mutation(){
        for (Gene gene: defaultScan){
            gene.doMutation();
        }
    }

    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int compareTo(Params o) {
        return score.compareTo(o.getScore());
    }

    public Params clone(){
        return this;
    }
}