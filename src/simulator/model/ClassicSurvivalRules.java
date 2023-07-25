package simulator.model;

import java.util.List;
import java.util.Map;

import simulator.miscellany.Vector2D;

public class ClassicSurvivalRules implements SurvivalRules{

	public ClassicSurvivalRules() {
		
	}
	
	@Override
	public void apply(Map<Vector2D,Cell> cells, List<Action> actions) {
		
		for(Cell cell : cells.values()) {
			
			int numberofNeighbours = cell.getNumberOfNeighbours();
			if(numberofNeighbours < 2 || numberofNeighbours > 3) {
				actions.add(new Action(ActionType.KILL_CELL,new Vector2D(cell.getPos())));
			}
		}
	}

}
