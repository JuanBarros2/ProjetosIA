package ufcg.edu.genetic;

import ufcg.edu.commons.Direction;
import ufcg.edu.commons.Params;

import java.util.ArrayList;
import java.util.List;

public class Individual implements Comparable<Individual> {
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

        ArrayList<Gene> genes = new ArrayList<>();
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

    public boolean isMutated() {
        return this.mutated;
    }

    public void setMutated() {
        this.mutated = true;
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

    public Individual clone(){
        return new Individual(this.chromossomes);
    }
}
