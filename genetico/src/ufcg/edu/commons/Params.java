package ufcg.edu.commons;
import java.io.Serializable;
import java.util.ArrayList;

public class Params implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Movement when nothing happens */
    ArrayList<Direction> defaultMovement;

    /** Default steps to go ahead when nothing happens */
    int step;

    /** Default scan turn */
    ArrayList<Direction> defaultScan;

    public void setDefaultMovement(ArrayList<Direction> defaultMovement) {
        this.defaultMovement = defaultMovement;
    }

    public ArrayList<Direction> getDefaultMovement() {
        return defaultMovement;
    }

    public ArrayList<Direction> getDefaultScan() {
        return defaultScan;
    }

    public void setDefaultScan(ArrayList<Direction> defaultScan) {
        this.defaultScan = defaultScan;
    }
}