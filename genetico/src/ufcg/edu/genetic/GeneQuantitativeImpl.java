package ufcg.edu.genetic;

import ufcg.edu.commons.Utils;

import java.util.Random;

public class GeneQuantitativeImpl implements Gene<Integer> {
    private Integer value;
    public Integer MAX_VALUE;
    private Integer RATE_OF_RANDOM_MUTATION = Utils.LOW_MUTATION_RATE;
    private Integer RATE_OF_MEDIUM_MUTATION = Utils.MED_MUTATION_RATE;
    private Integer RATE_OF_SMALL_MUTATION = Utils.HIGH_MUTATION_RATE;
    private Integer SMALL_MUTATION_VARIATION = 30;
    private Integer MED_MUTATION_VARIATION = 70;

    public GeneQuantitativeImpl(Integer value, Integer maxValue){
        this.value = value;
        this.MAX_VALUE = maxValue;
    }

    public GeneQuantitativeImpl(Integer maxValue){
        this.MAX_VALUE = maxValue;
        this.value = 0;
        randomMutation();
    }

    @Override
    public boolean doMutation() {
        Integer value = this.value;
        randomMutation();
        mutationWithInterval(MED_MUTATION_VARIATION);
        mutationWithInterval(SMALL_MUTATION_VARIATION);
        return !value.equals(this.value);
    }

    private void randomMutation() {
        if (Utils.willMutate(RATE_OF_RANDOM_MUTATION)) {
            Random random = new Random();
            this.value = random.nextInt(MAX_VALUE + 1);
        }
    }

    private void mutationWithInterval(Integer rate) {
        if (Utils.willMutate(RATE_OF_MEDIUM_MUTATION)) {
            Integer change = Utils.generateRandominInterval(rate);
            setValue(this.value + change);
        }
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer newValue) {
        newValue = Math.max(newValue, 0);
        newValue = Math.min(newValue, MAX_VALUE);
        this.value = newValue;
    }

    public String toString() {
        return "FORCA " + this.value.toString();
    }

}
