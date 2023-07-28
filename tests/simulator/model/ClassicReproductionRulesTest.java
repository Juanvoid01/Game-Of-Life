package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ClassicReproductionRulesTest {
	@Test
	void basic_behaviour() {
		Grid grid = new Grid(20,20);
		List<Action> actions = new ArrayList<Action>();
		
		grid.born(9,9);
		grid.born(10,9);
		grid.born(11,9);
		grid.born(9,10);
		grid.born(10,10);
		grid.born(11,10);
		grid.born(9,11);
		grid.born(10,11);
		grid.born(11, 11);
		grid.born(13, 13);
		
		ReproductionRules reproductionRules = new ClassicReproductionRules();
		reproductionRules.apply(grid, actions);
		

		assertEquals(4, actions.size(), 
				"reproductionRules.apply returned wrong number of actions");
		
		assertEquals(true, actions.contains(new Action(ActionType.ADD_CELL,12,10)), 
				"reproductionRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.ADD_CELL,10,12)), 
				"reproductionRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.ADD_CELL,8,10)), 
				"reproductionRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.ADD_CELL,10,8)), 
				"reproductionRules.apply returned incorrect actions");
	}
}
