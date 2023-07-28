package simulator.model;

import java.util.List;

public interface ReproductionRules {
	public void apply(Grid grid, List<Action> actions);
}
