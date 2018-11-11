package ufcg.edu.commons;
import java.util.Random;

public final class Utils {

    public static int HIGH_MUTATION_RATE = 1500;
    public static int MED_MUTATION_RATE = 1100;
    public static int LOW_MUTATION_RATE = 700;

    public static boolean willMutate(int mutationRate) {
        Random random = new Random();
        if (random.nextInt(mutationRate) == 0)
            return true;
        return false;
    }

    public static int generateRandominInterval(int interval) { //Preciso de um nome melhor
        Random random = new Random();
        int n = random.nextInt(2*interval + 1) - interval;
        return n;
    }

}
