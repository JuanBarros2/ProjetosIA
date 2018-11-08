import java.io.Serializable;

public class Direction implements Serializable{
    private static final long serialVersionUID = 1L;

    /** Degrees to turn after steps */
    public int degrees;

    /** Probability of turning degress after steps */
    public double prob;
}

public class Params implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Movement when nothing happens */
    ArrayList<Direction> defaultMovement;

    /** Default steps to go ahead when nothing happens */
    int step;

    /** Default scan turn */
    ArrayList<Direction> defaultScan;
}