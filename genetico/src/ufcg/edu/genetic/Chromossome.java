package ufcg.edu.genetic;

import java.util.List;

public class Chromossome {
    private List<Gene> genes;

    public Chromossome(List<Gene> genes){
        this.genes = genes;
    }

    public boolean mutation(){
        boolean mutated = false;
        for(Gene gene: getGenes()){
            mutated = mutated | gene.doMutation();
        }
        return mutated;
    }

    public List<Gene> getGenes() {
        return genes;
    }
}
