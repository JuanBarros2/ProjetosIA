package ufcg.edu.commons;

import java.io.Serializable;
import java.util.ArrayList;

public class Params implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Movement when nothing happens */
    ArrayList<Direction> defaultMovement;

    /** Default steps to go ahead when nothing happens */
    int step;

    /** Default scan turn */
    ArrayList<Direction> defaultScan;

    public void setDefaultMovement(ArrayList<Direction> defaultMovement) {
        this.defaultMovement = defaultMovement;
    }

    public ArrayList<Direction> getDefaultMovement() {
        return defaultMovement;
    }

    public ArrayList<Direction> getDefaultScan() {
        return defaultScan;
    }

    public void setDefaultScan(ArrayList<Direction> defaultScan) {
        this.defaultScan = defaultScan;
    }

    private List<Chromossome> chromossomes;
    private Integer score;
    private boolean mutated;

    public Individual(List<Chromossome> chromossomes){
        this.chromossomes = chromossomes;
        this.score = 0;
        mutated = false;
    }

    public Individual(Params params){
        this.chromossomes = new ArrayList<>();

        ArrayList<Gene<Integer>> genes = new ArrayList<>();
        for (Direction direction: params.getDefaultMovement()){
            genes.add(direction.degress);
            genes.add(direction.prob);
        }
        this.chromossomes.add(new Chromossome(genes));

        genes.clear();
        for (Direction direction: params.getDefaultScan()){
            genes.add(direction.degress);
        }

        this.chromossomes.add(new Chromossome(genes));
    }

    public void mutation(){
        for(Chromossome chromossome: chromossomes){
            mutated = mutated | chromossome.mutation();
        }
    }

    public List<Chromossome> getChromossomes() {
        return chromossomes;
    }

    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int compareTo(Individual o) {
        return score.compareTo(o.getScore());
    }

    public Params clone(){
        return new Params(this.chromossomes);
    }
}