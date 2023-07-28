package simulator.factories;

import simulator.model.ClassicSurvivalRules;
import simulator.model.SurvivalRules;

public class ClassicSurvivalRulesBuilder extends Builder<SurvivalRules>{

	public ClassicSurvivalRulesBuilder() {
		super("ClassicSurvival", "2 or 3 neighbours to survive");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected SurvivalRules createinstance() {
		// TODO Auto-generated method stub
		return new ClassicSurvivalRules();
	}

}
