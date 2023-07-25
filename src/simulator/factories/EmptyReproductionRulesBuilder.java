package simulator.factories;

import simulator.model.EmptyReproductionRules;
import simulator.model.ReproductionRules;

public class EmptyReproductionRulesBuilder extends Builder<ReproductionRules>{

	public EmptyReproductionRulesBuilder() {
		super("EmptyReproduction");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ReproductionRules createinstance() {
		// TODO Auto-generated method stub
		return new EmptyReproductionRules();
	}
}
