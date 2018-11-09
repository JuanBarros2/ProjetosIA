package ufcg.edu.commons;

import java.io.Serializable;

public class Direction implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Degrees to turn after steps */
    public int degrees;

    /** Probability of turning degress after steps */
    public double prob;
}
