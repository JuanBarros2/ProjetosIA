package swordseeker.robots;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import robocode.*;
import swordseeker.mapanalyser.*;
import swordseeker.movement.*;
import swordseeker.guns.*;
import swordseeker.utils.*;

public class Bismuth extends SmartRobot {
	boolean inverted;
	int R, G, B;
	
	public void run() {
		R = G = B = 0;		
		inverted = false;
		movimento = new AntiGravityMovement(this);	
		linearAnalyser = new LinearPredictive(this);
		patternAnalyser = new PatternMatcherPredictive(this);
		arma = new CheetosGun(this);
		bullets = new ArrayList<EnemyBullets>();
		
		Utils.setRobot(this);
		
		setColors(new Color(G, B, R), Color.white, Color.white, new Color(G, B, R), Color.magenta);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);
		
		//Coordinates to = movimento.nextMove();
		
		System.out.println("V0.8.1");
		
		while (true) {
			changeColor();
			doRadar();
			
			//if (getDistanceRemaining() < 50) {
				Utils.goTo(movimento.nextMove());
			//}
			
			if (enemy != null) {
				if (getEnergy() > 30) {
					arma.atirar(enemy, (int) (Math.max(arma.firePower(enemy), 90.0/enemy.energy)));
				} else {
					arma.atirar(enemy, (int) (Math.min(arma.firePower(enemy), 90.0/enemy.energy)));
				}
				
			}
			
			if (enemy != null && enemy.visto > 10 ) {
				enemy = null;
			}
			
			
			
			
			execute();
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		Enemy en;
		String name = e.getName();
		if (enemies.containsKey(name)){
			en = enemies.get(name);
		} else {
			en = new Enemy(name, this);
			enemies.put(name, en);
		}
		
		en.onScanned(e);
		
		if (enemy == null || e.getDistance() + 30 < enemy.getDistance()) {
			enemy = enemies.get(e.getName());
		}
	}
	
	private void doRadar() {
		if (enemy != null && getOthers() <= 1) {
			linearAnalyser.see(enemy);
		} else {
			setTurnRadarLeftRadians(2*Utils.PI);
		}
		
	}
	
	private void changeColor() {
		if (R < 255 && G == 0 && B == 0) {				//E
			R++;										//S
		} else if (R == 255 && G < 255 && B == 0) {		//T
			G++;										//E
		} else if (R == 255 && G == 255 && B < 255) {	//T
			B++;										//I
		} else if (R > 0 && G == 255 && B == 255) {		//C
			R--;										//I
		} else if (R == 0 && G > 0 && B == 255) {		//S
			G--;										//M
		} else if (R == 0 && G == 0 && B > 0) {			//O
			B--;										//P
		}												//U
														//R
		if (!inverted) {
			setBodyColor(new Color(G, B, R));			//O
		} else {
			setBodyColor(new Color(255-G, 255-B, 255-R));
		}
	}
	
	public void onRobotDeath(RobotDeathEvent e){
		if (enemy == null) return;
		if (e.getName().equals(enemy.name)) {
			enemy = null;
		}
		
		String name = e.getName();
		if (enemies.containsKey(name)){
			enemies.get(e.getName()).alive = false;
			System.out.printf("%s morreu\n", name);
		}
	}
	
	public void onBulletHit(BulletHitEvent e) {
		inverted = !inverted;
		
		if (inverted) {
			setBulletColor(new Color(255-G, 255-B, 255-R));
		} else {
			setBulletColor(new Color(G, B, R));
		}
		
		String name = e.getName();
		if (enemies.containsKey(name)) {
			
			enemies.get(name).hitted++;
		}
		//System.out.println(name+" foi acertado\n");
	}
	
	public void onPaint(Graphics2D g) {	//debugging
		g.setColor(Color.red);
		for (EnemyBullets b : bullets) {
			g.fillOval((int) b.point.x()-5, (int) b.point.y()-5, 10, 10);
		}
	}
	
	public Coordinates getPosition() {
		return new Coordinates(getX(), getY());
	}
}
