package ufcg.edu.genetic;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chromossome that = (Chromossome) o;

        return Objects.equals(genes, that.genes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genes);
    }

    public List<Gene> getGenes() {
        return genes;
    }
}
