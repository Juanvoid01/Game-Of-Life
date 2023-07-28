package simulator.model;

import java.util.List;

public interface SurvivalRules {
	public void apply(Grid grid, List<Action> actions);
}
