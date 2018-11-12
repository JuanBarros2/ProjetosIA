package ufcg.edu.commons;

import robocode.BattleResults;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import ufcg.edu.genetic.FitnessFunction;
import ufcg.edu.genetic.GeneticAlgorithm;
import ufcg.edu.genetic.OnFitnessComplete;

import java.io.*;

public class Script implements FitnessFunction {

    private File robocodeHome;
    private RobocodeEngine engine;
    private IO<Params> io;
    private String filePath;
    private static final int NUM_ROUNDS = 1;
    private static final int NUM_GER = 100;

    public Script() {
    	this.filePath = "BattleParams.txt";
    	io = new IO<Params>(filePath);
        robocodeHome = new File("/home/juan/robocode"); // JUAN: "/home/juan/robocode"
        engine = new RobocodeEngine(robocodeHome);
    }

    private void battle() {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(this);
        geneticAlgorithm.runAlgorithm(NUM_GER);
    }

    public static void main(String[] args) throws IOException {
        Script bc = new Script();
        bc.battle();
    }

    @Override
    public void getScore(Params individual, OnFitnessComplete listener) {
        IO<Params> file = new IO<Params>("Robo.txt");
        boolean write = file.write(individual);

        RobotSpecification[] robots = engine.getLocalRepository("sample.RamFire,sample.Crazy,sample.Walls,sample.Mendel");
        BattlefieldSpecification battlefield = new BattlefieldSpecification();
        BattleSpecification specs = new BattleSpecification(NUM_ROUNDS, battlefield, robots);

        engine.addBattleListener(new BattleAdaptor() {
            @Override
            public void onBattleCompleted(BattleCompletedEvent event) {
                super.onBattleCompleted(event);
                System.out.println("Batalha finalizada");
                for (BattleResults result : event.getSortedResults()) {
                    System.out.println(result.getScore() + " - " + result.getTeamLeaderName());
                }
                System.out.println("done");
                listener.onComplete(11);
                System.out.println("SHOW2");
            }
        });
        engine.setVisible(false);
        System.out.println("Iniciando batalha");
        engine.runBattle(specs, true);
    }

    
    @Override
    public void writeGeneration(Integer score, Integer generation) {
        System.out.println("Registrando geração aaaa " + generation + " SCORE: " + score);
       
		//writeCsv(score, generation);
		
    }
    
    public void writeCsv(Integer score, Integer generation) throws IOException {
    
    	  FileWriter writer = new FileWriter("Gen.csv");
    	  writer.append(generation.toString());
    	  writer.append(";");
    	  writer.append(score.toString());
    	  writer.append(";");
    	  writer.flush();
          writer.close();
    }

    public void writeFileParams(Params parametros) throws IOException {
    	io.write(parametros);
   }
   
   public void readFileParams() throws IOException {
   	   io.read();
   }
}