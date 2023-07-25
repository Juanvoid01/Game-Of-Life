package simulator.factories;

import simulator.model.ClassicSurvivalRules;
import simulator.model.SurvivalRules;

public class ClassicSurvivalRulesBuilder extends Builder<SurvivalRules>{

	public ClassicSurvivalRulesBuilder() {
		super("ClassicSurvival");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected SurvivalRules createinstance() {
		// TODO Auto-generated method stub
		return new ClassicSurvivalRules();
	}

}
