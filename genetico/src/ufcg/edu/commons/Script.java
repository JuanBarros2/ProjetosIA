package ufcg.edu.commons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import ufcg.edu.genetic.FitnessFunction;
import ufcg.edu.genetic.GeneticAlgorithm;
import ufcg.edu.genetic.OnFitnessComplete;

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
        robocodeHome = new File("D:\\robocode");
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
        RobotSpecification[] robots = engine.getLocalRepository("supersample.SuperRamFire,sample.Crazy,sample.Walls");
        BattlefieldSpecification battlefield = new BattlefieldSpecification();
        BattleSpecification specs = new BattleSpecification(NUM_ROUNDS, battlefield, robots);

        engine.addBattleListener(new BattleAdaptor() {
            @Override
            public void onBattleCompleted(BattleCompletedEvent event) {
                System.out.println("Batalha finalizada");
                super.onBattleCompleted(event);
                listener.onComplete(11);
            }
        });
        engine.setVisible(false);
        System.out.println("Iniciando batalha");
        engine.runBattle(specs, true);
    }

    @Override
    public void writeGeneration(Integer score, Integer generation) {
        System.out.println("Registrando geraÃ§Ã£o " + generation + " SCORE: " + score);
        try {
			writeFileScoreGen(score, generation);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void writeFileScoreGen(Integer score, Integer generation) throws IOException {
    	FileWriter  file = new FileWriter("BattleParams.txt", true);
        BufferedWriter output = new BufferedWriter(file);
        output.write("Geração: "+ generation + ",SCORE: " + score);
        output.close();
    }
    
    public static void readFileScoreGen() throws IOException {
    	BufferedReader br;
        br = new BufferedReader(new FileReader("Gen.txt"));
        String line;
        while((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
    
    public void writeFileParams(Params parametros) throws IOException {
    	io.write(parametros);
    	
   }
   
   public void readFileParams() throws IOException {
   	   io.read();
   }
}