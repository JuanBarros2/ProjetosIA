package ufcg.edu.genetic;

import java.io.Serializable;

public interface Gene<T> extends Serializable {
    boolean doMutation();
    public T getForce();
    String toString();
}
