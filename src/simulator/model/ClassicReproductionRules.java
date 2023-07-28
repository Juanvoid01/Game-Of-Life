package simulator.model;

import java.util.List;

public class ClassicReproductionRules implements ReproductionRules{

	public ClassicReproductionRules() {
		
	}
	
	@Override
	public void apply(Grid grid, List<Action> actions) {
		
		int rows = grid.getRows();
		int cols = grid.getCols();
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				
				if(!grid.isAlive(i, j) && grid.getNumberOfNeighbours(i,j) == 3) {
					actions.add(new Action(ActionType.ADD_CELL,i,j));
				}
				
			}
		}
		
	}

}
