package ufcg.commons;

import robocode.BattleResults;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import ufcg.genetic.FitnessFunction;
import ufcg.genetic.GeneticAlgorithm;
import ufcg.genetic.OnFitnessComplete;

import java.io.*;
import java.util.Stack;

public class Script implements FitnessFunction {
	
    private File robocodeHome;
    private RobocodeEngine engine;
    private Stack<String> enemies;
    private static final int NUM_ROUNDS = 1;
    private static final int NUM_GER = 30;

    public Script() {
        robocodeHome = new File("/home/juan/robocode"); // JUAN: "/home/juan/robocode"
        enemies = new Stack<>();
        enemies.push("sample.Walls");
        enemies.push("sample.RamFire");
        enemies.push("sample.Crazy");
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
    public Integer getScore(Params individual, OnFitnessComplete listener) {
        IO<Params> file = new IO<Params>();
        boolean write = file.write(individual);
        final Integer[] score = {0};

        engine = new RobocodeEngine(robocodeHome);
        RobotSpecification[] robots = engine.getLocalRepository(enemies.peek()+",ufcg.robot.Mendel");
        BattlefieldSpecification battlefield = new BattlefieldSpecification();
        BattleSpecification specs = new BattleSpecification(NUM_ROUNDS, battlefield, robots);

        engine.addBattleListener(new BattleAdaptor() {
            @Override
            public void onBattleCompleted(BattleCompletedEvent event) {
                super.onBattleCompleted(event);
                System.out.println("Batalha finalizada");
                for (BattleResults result : event.getSortedResults()) {
                    if (result.getTeamLeaderName().equals("ufcg.robot.Mendel*")) {
                        System.out.println("Score: " + result.getScore());
                        score[0] = result.getScore();
                    }
                }
            }
        });
        engine.setVisible(false);
        System.out.println("Iniciando batalha");
        engine.runBattle(specs, true);

        return score[0];
    }

    
    @Override
    public void writeGeneration(Integer score, Integer generation) {
        System.out.println("Registrando geração " + generation + " SCORE: " + score);
        try {
			writeCsv(score, generation);
		} catch (IOException e) {
			e.printStackTrace();
		}
       
    }
    
    public void writeCsv(Integer score, Integer generation) throws IOException {
    	  FileWriter writer = new FileWriter("Gen.csv", true);
    	  writer.append(generation.toString());
    	  writer.append(";");
    	  writer.append(score.toString());
    	  writer.append(";");
          writer.append(enemies.peek());
          writer.append(";");
          writer.append("\n");
          writer.close();
    }
}