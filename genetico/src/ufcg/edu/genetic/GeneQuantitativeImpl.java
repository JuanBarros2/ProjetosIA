package ufcg.edu.genetic;

import ufcg.edu.commons.Utils;

import java.util.Random;

public class GeneQuantitativeImpl implements Gene<Integer> {
    private Integer force;
    public Integer MAX_FORCE;
    private Integer RATE_OF_RANDOM_MUTATION = Utils.LOW_MUTATION_RATE;
    private Integer RATE_OF_MEDIUM_MUTATION = Utils.MED_MUTATION_RATE;
    private Integer RATE_OF_SMALL_MUTATION = Utils.HIGH_MUTATION_RATE;
    private Integer SMALL_MUTATION_VARIATION = 30;
    private Integer MED_MUTATION_VARIATION = 70;

    public GeneQuantitativeImpl(Integer force, Integer maxForce){
        this.force = force;
        this.MAX_FORCE = maxForce;
    }

    public GeneQuantitativeImpl(Integer maxForce){
        this.MAX_FORCE = maxForce;
        this.force = 0;
        randomMutation();
    }

    @Override
    public boolean doMutation() {
        Integer force = this.force;
        randomMutation();
        mutationWithInterval(MED_MUTATION_VARIATION);
        mutationWithInterval(SMALL_MUTATION_VARIATION);
        return !force.equals(this.force);
    }

    private void randomMutation() {
        if (Utils.willMutate(RATE_OF_RANDOM_MUTATION)) {
            Random random = new Random();
            this.force = random.nextInt(MAX_FORCE + 1);
        }
    }

    private void mutationWithInterval(Integer rate) {
        if (Utils.willMutate(RATE_OF_MEDIUM_MUTATION)) {
            Integer change = Utils.generateRandominInterval(rate);
            setForce(this.force + change);
        }
    }

    @Override
    public Integer getForce() {
        return force;
    }

    public void setForce(Integer newForce) {
        newForce = Math.max(newForce, 0);
        newForce = Math.min(newForce, MAX_FORCE);
        this.force = newForce;
    }

    public String toString() {
        return "FORCA " + this.force.toString();
    }




}
