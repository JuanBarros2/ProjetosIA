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
        fire(params.getFirePower().getValue()/10.0);
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        Direction hitByBullet = params.getHitByBullet();
        ahead(hitByBullet.getStep());
        turnGunRight(hitByBullet.getDegrees());
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        Direction onHit = params.getOnHitWall();
        back(onHit.getStep());
        turnLeft(onHit.getDegrees());
    }
}