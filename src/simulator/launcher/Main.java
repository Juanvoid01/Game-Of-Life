package simulator.launcher;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.factories.*;
import simulator.model.GenerationSimulator;
import simulator.model.ReproductionRules;
import simulator.model.SurvivalRules;
import simulator.view.MainWindow;

public class Main {

	private static Factory<ReproductionRules> reproductionRulesFactory;
	private static Factory<SurvivalRules> survivalRulesFactory;
	
	private static void initFactories() {
		ArrayList<Builder<ReproductionRules>> reproductionRulesBuilders = new ArrayList<>();
		reproductionRulesBuilders.add(new ClassicReproductionRulesBuilder());
		reproductionRulesBuilders.add(new HighLifeReproductionRulesBuilder());
		reproductionRulesBuilders.add(new EmptyReproductionRulesBuilder());
		
		reproductionRulesFactory = new BuilderBasedFactory<ReproductionRules>(reproductionRulesBuilders);
		
		ArrayList<Builder<SurvivalRules>> survivalRulesBuilders = new ArrayList<>();
		survivalRulesBuilders.add(new ClassicSurvivalRulesBuilder());
		survivalRulesBuilders.add(new HighLifeSurvivalRulesBuilder());
		survivalRulesBuilders.add(new EmptySurvivalRulesBuilder());
		
		survivalRulesFactory = new BuilderBasedFactory<SurvivalRules>(survivalRulesBuilders);
		
		
	}
	private static void start() throws Exception{
		
		ReproductionRules Rr = reproductionRulesFactory.createInstance("ClassicReproduction");
		SurvivalRules Sr = survivalRulesFactory.createInstance("ClassicSurvival");
		
		GenerationSimulator Gs = new GenerationSimulator(300,Rr,Sr);
		Controller c = new Controller(Gs,reproductionRulesFactory,survivalRulesFactory);
		

		
		
		//c.run(1);
		SwingUtilities.invokeAndWait(() -> new MainWindow(c));
	}
	
	public static void main(String[] args) {
		try {
			initFactories();
			start();
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
