package ufcg.edu.commons;

import ufcg.edu.genetic.Gene;

import java.io.Serializable;
import java.util.List;

public class Params implements Serializable, Comparable<Params> {
    private static final long serialVersionUID = 1L;

    /** Movement when nothing happens */
    List<Direction> defaultMovement;
    Integer score;
    List<Direction> defaultScan;

    public void setDefaultMovement(List<Direction> defaultMovement) {
        this.defaultMovement = defaultMovement;
    }

    public List<Direction> getDefaultMovement() {
        return defaultMovement;
    }

    public List<Direction> getDefaultScan() {
        return defaultScan;
    }

    public void setDefaultScan(List<Direction> defaultScan) {
        this.defaultScan = defaultScan;
    }

    public void mutation(){
        for (Direction gene: defaultScan){
            gene.doMutation();
        }
        for (Direction gene: defaultScan){
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

    @Override
    public String toString() {
        return score.toString();
    }
}