package ufcg.edu;

import java.util.List;

public class Individual implements Comparable<Individual> {

    private List<Gene> genes;
    private boolean mutated;
    private Integer score;

    public Individual(List<Gene> genes){
        this.genes = genes;
        this.mutated = false;
        this.score = 0;
    }

    public void mutation(){

        for(Gene gene: getGenes()){
            mutated = mutated | gene.doMutation();
        }
    }

    public List<Gene> getGenes() {
        return genes;
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
}
