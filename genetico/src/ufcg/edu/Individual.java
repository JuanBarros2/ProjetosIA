package ufcg.edu;

import java.util.List;
import java.util.Random;

public class Individual {

    private List<Gene> genes;

    public Individual(List<Gene> genes){
        this.genes = genes;
    }

    public void mutation(){
        Random random = new Random();
        for(Gene gene: getGenes()){
            gene.doMutation(0);
        }
    }

    public List<Gene> getGenes() {
        return genes;
    }
}
