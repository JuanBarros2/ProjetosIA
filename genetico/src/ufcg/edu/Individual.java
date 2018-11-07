package ufcg.edu;

import java.util.List;

public class Individual {

    private List<Gene> genes;
    private boolean mutated;

    public Individual(List<Gene> genes){
        this.genes = genes;
        this.mutated = false;
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
}
