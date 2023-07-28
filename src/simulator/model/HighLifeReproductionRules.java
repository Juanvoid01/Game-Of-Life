package simulator.model;

import java.util.List;

public class HighLifeReproductionRules implements ReproductionRules{

	@Override
	public void apply(Grid grid, List<Action> actions) {
		// TODO Auto-generated method stub
		int rows = grid.getRows();
		int cols = grid.getCols();
		
		int neighbours = 0;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(!grid.isAlive(i, j)){
					neighbours = grid.getNumberOfNeighbours(i,j);
					if(neighbours == 3 || neighbours == 6) {
						actions.add(new Action(ActionType.ADD_CELL,i,j));
					}
				}

			}
		}
	}

}
