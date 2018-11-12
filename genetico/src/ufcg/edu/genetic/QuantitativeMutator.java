package ufcg.edu.genetic;

import ufcg.edu.commons.Utils;

import java.util.Random;

public class QuantitativeMutator implements Gene<Integer> {
    public Integer MAX_VALUE;
    public Integer MIN_VALUE;
    private Integer RATE_OF_RANDOM_MUTATION = Utils.LOW_MUTATION_RATE;
    private Integer RATE_OF_MEDIUM_MUTATION = Utils.MED_MUTATION_RATE;
    private Integer RATE_OF_SMALL_MUTATION = Utils.HIGH_MUTATION_RATE;
    private Integer SMALL_MUTATION_VARIATION;
    private Integer MED_MUTATION_VARIATION;
    private boolean mutated;
    private Integer value;
    

    public QuantitativeMutator(Integer maxValue, Integer minValue, Integer smallRange, Integer medRange){
        this.MAX_VALUE = maxValue;
        this.MIN_VALUE = minValue;
        this.SMALL_MUTATION_VARIATION = smallRange;
        this.MED_MUTATION_VARIATION = medRange;
        this.mutated = false;
    }

    @Override
    public Integer doMutation() {
        this.mutated = false;
        randomMutation();
        mutationWithInterval(MED_MUTATION_VARIATION);
        mutationWithInterval(SMALL_MUTATION_VARIATION);
        if (!value.equals(this.value)) {
            this.mutated = true;
        }
        return this.value;
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

    public void setValue(Integer value) {
        value = Math.max(value, MIN_VALUE);
        value = Math.min(value, MAX_VALUE);
        this.value = value;
    }

    public boolean mutated() {
        return this.mutated;
    }
}
