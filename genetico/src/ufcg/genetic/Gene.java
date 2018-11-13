package ufcg.genetic;

import java.io.Serializable;

public interface Gene<T> extends Serializable {
    boolean doMutation();

    public T getValue();

}
