package ufcg.commons;

import robocode.BattleResults;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import ufcg.genetic.FitnessFunction;
import ufcg.genetic.GeneticAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Script implements FitnessFunction {
	
    private File robocodeHome;
    private RobocodeEngine engine;
    private IO<Params> io;
    private String filePath;
    private Stack<String> enemies;
    private static final int NUM_ROUNDS = 10;
    private static final int NUM_GER = 30;
    private BattleObserver battleObserver;

    public Script() {
    	this.filePath = "BattleParams.txt";
    	io = new IO<Params>(filePath);
        robocodeHome = new File("/home/ignacio/robocode"); // JUAN: "/home/juan/robocode"
        enemies = new Stack<>();
        enemies.push("sample.Walls");
        enemies.push("sample.RamFire");
        enemies.push("sample.Crazy");
        battleObserver = new BattleObserver();
    }

    private void battle() {
        while(!enemies.empty()){
            System.out.println("Rodando para o robo inimigo: "+ enemies.peek());
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(this);
            geneticAlgorithm.runAlgorithm(NUM_GER);
            enemies.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        Script bc = new Script();
        bc.battle();
    }

    @Override
    public int getScore(Params individual) {
        this.engine = new RobocodeEngine(robocodeHome);
        IO<Params> file = new IO<Params>();
        boolean write = file.write(individual);
        String selected_robots = enemies.peek() + ",ufcg.robot.Mendel";
        RobotSpecification[] robots = engine.getLocalRepository(selected_robots);
//X
        BattlefieldSpecification battlefield = new BattlefieldSpecification();
        BattleSpecification specs = new BattleSpecification(NUM_ROUNDS, battlefield, robots);
        engine.addBattleListener(battleObserver);
        engine.setVisible(false);
        System.out.println("Iniciando batalha");
        engine.runBattle(specs, true);
        BattleResults[] results = battleObserver.getResults();
        int ret = 0;
        for (BattleResults result : results) {
            System.out.println("Nome: " + result.getTeamLeaderName());
            if (result.getTeamLeaderName().equals("ufcg.robot.Mendel*")) {
                ret = result.getScore();
            }

        }
        return ret;
    }

    
    @Override
    public void writeGeneration(Integer score, Integer generation) {
        System.out.println("Registrando geração " + (generation-1) + " SCORE: " + score);
        try {
			writeCsv(score, generation-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
       
    }
    
    public void writeCsv(Integer score, Integer generation) throws IOException {
    	  FileWriter writer = new FileWriter("Gen.csv", true);
    	  writer.append(generation.toString() + "," +score.toString()+","+enemies.peek()+"\n");
          writer.close();
    }
}


class BattleObserver extends BattleAdaptor {

    robocode.BattleResults[] results;

    public void onBattleCompleted(BattleCompletedEvent e) {
        System.out.println("Acabou a batalha");
        results = e.getIndexedResults();
        }

    public void onBattleError(BattleErrorEvent e){
        System.out.println("Error running battle: " + e.getError());
    }

    public BattleResults[] getResults(){
        return results;
    }

}