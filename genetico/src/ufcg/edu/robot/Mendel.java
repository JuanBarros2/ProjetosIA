package ufcg.edu.robot;

import robocode.*;
import ufcg.edu.commons.Direction;
import ufcg.edu.commons.Params;
import java.util.Collections;
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
		Scan = new Scan().start();
		while (true) {
			int nextDegrees = nextDegrees(params.getDefaultMovement());
			if (nextDegrees < 180) {
				turnRight(nextDegrees);
			} else {
				turnLeft(360 - nextDegrees);
			}
			ahead(params.getSteps());
		}
	}

	class Scan extends Thread {
		public void run() {
			while (true) {
				int nextDegrees = nextDegrees(params.getDefaultScan());
				if (nextDegrees < 180) {
					turnRadarRight(nextDegrees);
				} else {
					turnRadarLeft(360 - nextDegrees);
				}
			}
		}
	}

	private int nextDegrees(List movements) {
		List<Direction> allMovements = new ArrayList<>();
		for (Direction dir : movements) {
			allMovements.addAll(Collections.nCopies(dir.getProb(), dir));
		}
		return allMovements.get(randInt(0, allMovements.size())).getDegrees();
	}

	static Random rand = new Random();

	private randInt(int min, int max){
		return  rand.nextInt((max - min)) + min;
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