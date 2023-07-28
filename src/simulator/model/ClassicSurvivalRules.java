package simulator.model;

import java.util.List;

public class ClassicSurvivalRules implements SurvivalRules{

	public ClassicSurvivalRules() {
		
	}
	
	@Override
	public void apply(Grid grid, List<Action> actions) {
		
		
		int rows = grid.getRows();
		int cols = grid.getCols();
		int neighbours = 0;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(grid.isAlive(i, j)) {
					neighbours = grid.getNumberOfNeighbours(i, j);
					
					if(neighbours < 2 || neighbours > 3) {
						actions.add(new Action(ActionType.KILL_CELL,i,j));
					}
				}
			}
		}
		
	}

}
