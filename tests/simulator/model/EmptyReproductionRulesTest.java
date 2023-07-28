package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EmptyReproductionRulesTest {
	@Test
	void basic_behaviour() {
		Grid grid = new Grid(20,20);
		List<Action> actions = new ArrayList<Action>();
		
		grid.born(10, 10);
		grid.setNumberOfNeighbours(10, 10, 8);
		grid.born(8, 8);
		grid.setNumberOfNeighbours(8, 8, 1);
		grid.born(9, 8);
		grid.setNumberOfNeighbours(9, 8, 2);
		grid.born(10, 8);
		grid.setNumberOfNeighbours(10, 8, 3);
		grid.born(11, 8);
		grid.setNumberOfNeighbours(11, 8, 2);
		grid.born(12, 8);
		grid.setNumberOfNeighbours(12, 8, 1);
		grid.born(12, 9);
		grid.setNumberOfNeighbours(12, 9, 2);
		grid.born(12, 10);
		grid.setNumberOfNeighbours(12, 10, 3);
		grid.born(12, 11);
		grid.setNumberOfNeighbours(12, 11, 2);
		grid.born(12, 12);
		grid.setNumberOfNeighbours(12, 12, 1);
		grid.born(11, 12);
		grid.setNumberOfNeighbours(11, 12, 2);
		grid.born(10, 12);
		grid.setNumberOfNeighbours(10, 12, 3);
		grid.born(9, 12);
		grid.setNumberOfNeighbours(9, 12, 2);
		grid.born(8, 12);
		grid.setNumberOfNeighbours(8, 12, 1);
		grid.born(8, 11);
		grid.setNumberOfNeighbours(8, 11, 2);
		grid.born(8, 10);
		grid.setNumberOfNeighbours(8, 10, 3);
		
		ReproductionRules reproductionRules = new EmptyReproductionRules();
		reproductionRules.apply(grid, actions);
		

		assertEquals(0, actions.size(), 
				"reproductionRules.apply returned wrong number of actions");
	}
}
