package ufcg.edu;

import java.util.Random;

public class GeneDirectionImpl implements Gene {
    //A maioria desses valores foi escolhido de maneira aleatória
    private Integer force;
    private Integer MAX_FORCE = 400;
    private Integer RATE_OF_RANDOM_MUTATION = Utils.LOW_MUTATION_RATE;
    private Integer RATE_OF_MEDIUM_MUTATION = Utils.MED_MUTATION_RATE;
    private Integer RATE_OF_SMALL_MUTATION = Utils.HIGH_MUTATION_RATE;
    private Integer SMALL_MUTATION_VARIATION = 30;
    private Integer MED_MUTATION_VARIATION = 70;

    @Override
    public boolean doMutation() {
        return randomMutation() |
                mutationWithInterval(MED_MUTATION_VARIATION) |
                mutationWithInterval(SMALL_MUTATION_VARIATION) ;
    }

    private boolean randomMutation() {
        if (Utils.willMutate(RATE_OF_RANDOM_MUTATION)) {
            Random random = new Random();
            this.force = random.nextInt(MAX_FORCE + 1);
            return true;
        }
        return false;
    }

    private boolean mutationWithInterval(Integer rate) {
        if (Utils.willMutate(RATE_OF_MEDIUM_MUTATION)) {
            Integer change = Utils.generateRandominInterval(rate);
            setForce(this.force + change);
            return true;
        }
        return false;
    }

    private void setForce(Integer newForce) {
        newForce = Math.max(newForce, 0);
        newForce = Math.min(newForce, MAX_FORCE);
        this.force = newForce;
    }

    public String toString() {
        return "FORCA " + this.force.toString();
    }




}
