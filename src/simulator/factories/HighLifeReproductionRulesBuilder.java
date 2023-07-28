package simulator.factories;

import simulator.model.HighLifeReproductionRules;
import simulator.model.ReproductionRules;

public class HighLifeReproductionRulesBuilder extends Builder<ReproductionRules>{

	public HighLifeReproductionRulesBuilder() {
		super("HighLifeReproduction", "3 or 6 neighbours to reproduce");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ReproductionRules createinstance() {
		// TODO Auto-generated method stub
		return new HighLifeReproductionRules();
	}

}
