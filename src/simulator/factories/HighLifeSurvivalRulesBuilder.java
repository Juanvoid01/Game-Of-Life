package simulator.factories;


import simulator.model.HighLifeSurvivalRules;

import simulator.model.SurvivalRules;

public class HighLifeSurvivalRulesBuilder extends Builder<SurvivalRules>{
	public HighLifeSurvivalRulesBuilder() {
		super("HighLifeSurvival", "2 or 3 neighbours to survive");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected SurvivalRules createinstance() {
		// TODO Auto-generated method stub
		return new HighLifeSurvivalRules();
	}
}
