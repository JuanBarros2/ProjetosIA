package ufcg.edu.genetic;

public interface Gene<T> {
    boolean doMutation();
    public T getForce();
    String toString();
}
