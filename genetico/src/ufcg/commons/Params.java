package ufcg.commons;

import ufcg.genetic.GeneQuantitativeImpl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Params implements Serializable, Comparable<Params> {
    private static final long serialVersionUID = 1L;

    /**
     * Movement when nothing happens
     */
    List<Direction> defaultMovement;
    Integer score = 0;
    GeneQuantitativeImpl firePower;
    List<Direction> defaultScan;
    Direction onHitWall;
    Direction hitByBullet;

    public Params() {
        this.defaultMovement = Arrays.asList(new Direction(), new Direction(), new Direction());
        this.defaultScan = Arrays.asList(new Direction(), new Direction(), new Direction());
        this.firePower = new GeneQuantitativeImpl(30,1,5,10);
        this.onHitWall = new Direction();
        this.hitByBullet = new Direction();
    }


    public Direction getOnHitWall() {
        return onHitWall;
    }

    public void setOnHitWall(Direction onHitWall) {
        this.onHitWall = onHitWall;
    }

    public Direction getHitByBullet() {
        return hitByBullet;
    }

    public void setHitByBullet(Direction hitByBullet) {
        this.hitByBullet = hitByBullet;
    }


    public GeneQuantitativeImpl getFirePower() {
        return firePower;
    }

    public void setFirePower(GeneQuantitativeImpl firePower) {
        this.firePower = firePower;
    }



    public void setDefaultMovement(List<Direction> defaultMovement) {
        this.defaultMovement = defaultMovement;
    }

    public List<Direction> getDefaultMovement() {
        return defaultMovement;
    }

    public List<Direction> getDefaultScan() {
        return defaultScan;
    }

    public void setDefaultScan(List<Direction> defaultScan) {
        this.defaultScan = defaultScan;
    }

    public boolean mutation() {
        boolean mutated = false;
        for (Direction gene : defaultMovement) {
            mutated |= gene.doMutation();
        }
        for (Direction gene : defaultScan) {
            mutated |= gene.doMutation();
        }
        mutated |= firePower.doMutation();
        mutated |= hitByBullet.doMutation();
        mutated |= onHitWall.doMutation();
        return mutated;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int compareTo(Params o) {
        return score.compareTo(o.getScore());
    }

    public Params clone(){
        Params params = new Params();
        params.setScore(this.score);
        params.setDefaultMovement(this.defaultMovement);
        params.setDefaultScan(this.defaultScan);
        return params;
    }

    @Override
    public String toString() {
        return score.toString();
    }
}