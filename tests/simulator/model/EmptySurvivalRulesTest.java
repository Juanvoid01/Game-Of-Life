package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EmptySurvivalRulesTest {
	@Test
	void basic_behaviour() {
		Grid grid = new Grid(20,20);
		List<Action> actions = new ArrayList<Action>();
		
		grid.born(9,9);
		grid.setNumberOfNeighbours(9,9, 2);
		grid.born(9,10);
		grid.setNumberOfNeighbours(9,10, 4);
		grid.born(9,11);
		grid.setNumberOfNeighbours(9,11, 2);
		grid.born(10,9);
		grid.setNumberOfNeighbours(10,9, 4);
		grid.born(10,11);
		grid.setNumberOfNeighbours(10,11, 4);
		grid.born(11,9);
		grid.setNumberOfNeighbours(11,9, 2);
		grid.born(11,10);
		grid.setNumberOfNeighbours(11,10, 4);
		grid.born(11,11);
		grid.setNumberOfNeighbours(11,11, 2);
		
		SurvivalRules survivalRules = new EmptySurvivalRules();
		survivalRules.apply(grid, actions);
		

		assertEquals(0, actions.size(), 
				"survivalRules.apply returned wrong number of actions");

	}
}
