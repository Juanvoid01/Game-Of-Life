package simulator.model;

import java.util.List;
import java.util.Map;

import simulator.miscellany.Vector2D;

public class ClassicReproductionRules implements ReproductionRules{

	public ClassicReproductionRules() {
		
	}
	
	@Override
	public void apply(Map<Vector2D,Cell> futureCells, List<Action> actions) {
		
		for(Cell futureCell : futureCells.values()) {
			
			if(futureCell.getNumberOfNeighbours() == 3) {
				actions.add(new Action(ActionType.ADD_CELL,new Vector2D(futureCell.getPos())));
			}
		}
	}

}
