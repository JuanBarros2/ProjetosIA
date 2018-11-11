package ufcg.edu.robot;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import ufcg.edu.commons.Direction;
import ufcg.edu.commons.IO;
import ufcg.edu.commons.Params;

import java.util.List;

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
        IO<Params> io = new IO("./params.ser");
        params = io.read();

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