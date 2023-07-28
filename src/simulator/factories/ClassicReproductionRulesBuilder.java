package simulator.factories;

import simulator.model.ClassicReproductionRules;
import simulator.model.ReproductionRules;

public class ClassicReproductionRulesBuilder extends Builder<ReproductionRules>{

	public ClassicReproductionRulesBuilder() {
		super("ClassicReproduction", "3 neighbours to reproduce");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ReproductionRules createinstance() {
		// TODO Auto-generated method stub
		return new ClassicReproductionRules();
	}

}
