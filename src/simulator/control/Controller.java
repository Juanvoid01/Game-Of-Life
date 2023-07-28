package simulator.control;

import java.util.List;

import simulator.factories.Factory;
import simulator.model.GenerationSimulator;
import simulator.model.ReproductionRules;
import simulator.model.SimulatorObserver;
import simulator.model.SurvivalRules;

public class Controller {
	
	private Factory<ReproductionRules> reproductionRulesFactory;
	private Factory<SurvivalRules> survivalRulesFactory;
	private GenerationSimulator generationSimulator;
	
	public Controller(GenerationSimulator gs,Factory<ReproductionRules> FRr, Factory<SurvivalRules> FSr) {
		this.generationSimulator = gs;
		this.reproductionRulesFactory = FRr;
		this.survivalRulesFactory = FSr;
	}
	
	public void run(int numberOfGenerations) {
		for(int i = 0; i < numberOfGenerations;i++) {
			generationSimulator.advance();
		}
	}
	
	public void reset() {
		generationSimulator.reset();
	}
	
	public void addObserver(SimulatorObserver o) {
		generationSimulator.addObserver(o);
	}
	
	public void removeObserver(SimulatorObserver o) {
		generationSimulator.removeObserver(o);
	}
	
	public void setReproductionRules(String type) {
		
		generationSimulator.setReproductionRules(reproductionRulesFactory.createInstance(type));
	}
	public void setSurvivalRules(String type) {
		
		generationSimulator.setSurvivalRules(survivalRulesFactory.createInstance(type));
	}
	
	public ReproductionRules getReproductionRules() {
		return generationSimulator.getReproductionRules();
	}


	public SurvivalRules getSurvivalRules() {
		return generationSimulator.getSurvivalRules();
	}
	
	public void addCell(int row, int col) {
		generationSimulator.addCell(row,col);
	}
	
	public void killCell(int row, int col) {
		generationSimulator.killCell(row,col);
	}
	
	public List<String> getSurvivalRulesInfo(){
		return this.survivalRulesFactory.getInfo();
	}
	
	public List<String> getReproductionRulesInfo(){
		return this.reproductionRulesFactory.getInfo();
	}
	
}
