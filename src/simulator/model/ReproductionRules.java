package simulator.model;

import java.util.List;
import java.util.Map;

import simulator.miscellany.Vector2D;

public interface ReproductionRules {
	public void apply(Map<Vector2D,Cell> futureCells, List<Action> actions);
}
