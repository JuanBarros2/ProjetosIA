package ufcg.edu.robot;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import ufcg.edu.commons.Direction;
import ufcg.edu.commons.Params;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Mendel - a robot by a group for the project of artificial intelligence class
 * in 2018.2, at UFCG.
 */
public class Mendel extends Robot {

    Params params;

    /**
     * Default behavior
     */
    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        // setColors(Color.red,Color.blue,Color.green); // body,gun,radar

        // Robot main loop
        new Scan().start();
        int currentMov = 0;
        while (true) {
            Direction mov = params.getDefaultMovement().get(currentMov);
            turnRight(mov.getDegrees());
            ahead(mov.getStep());
            currentMov++;
            currentMov %= params.getDefaultMovement().size();
        }
    }

    class Scan extends Thread {
        public void run() {
            int currentScan = 0;
            List<Direction> scans = params.getDefaultScan();
            while (true) {
                Direction mov = scans.get(currentScan);
                turnRadarRight(mov.getDegrees());
                currentScan++;
                currentScan %= scans.size();
            }
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        fire(1);
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        ahead(10);
        turnGunRight(360);
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        back(20);
        turnLeft(90);
    }
}