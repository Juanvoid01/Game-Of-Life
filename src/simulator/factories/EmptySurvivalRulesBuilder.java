package simulator.factories;

import simulator.model.EmptySurvivalRules;
import simulator.model.SurvivalRules;

public class EmptySurvivalRulesBuilder extends Builder<SurvivalRules>{
	
	public EmptySurvivalRulesBuilder() {
		super("EmptySurvival", "no cell dies");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected SurvivalRules createinstance() {
		// TODO Auto-generated method stub
		return new EmptySurvivalRules();
	}
}
