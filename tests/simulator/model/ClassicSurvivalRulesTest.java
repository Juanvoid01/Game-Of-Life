package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ClassicSurvivalRulesTest {
	@Test
	void basic_behaviour() {
		Grid grid = new Grid(20,20);
		
		List<Action> actions = new ArrayList<Action>();
		
		grid.born(9,9);
		//grid.setNumberOfNeighbours(9,9, 3);
		grid.born(10,9);
		//grid.setNumberOfNeighbours(10,9, 5);
		grid.born(11,9);
		//grid.setNumberOfNeighbours(11,9, 3);
		grid.born(9,10);
		//grid.setNumberOfNeighbours(9,10, 5);
		grid.born(10,10);
		//grid.setNumberOfNeighbours(10,10, 8);
		grid.born(11,10);
		//grid.setNumberOfNeighbours(11,10, 5);
		grid.born(9,11);
		//grid.setNumberOfNeighbours(9,11, 3);
		grid.born(10,11);
		//grid.setNumberOfNeighbours(10,11, 5);
		grid.born(11, 11);
		//grid.setNumberOfNeighbours(11, 11, 3);
		
		grid.born(13, 13);
		//grid.setNumberOfNeighbours(13, 13, 0);
		
		SurvivalRules survivalRules = new ClassicSurvivalRules();
		survivalRules.apply(grid, actions);
		

		assertEquals(6, actions.size(), 
				"survivalRules.apply returned wrong number of actions");
		
		assertEquals(true, actions.contains(new Action(ActionType.KILL_CELL,10,10)), 
				"survivalRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.KILL_CELL,9,10)), 
				"survivalRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.KILL_CELL,11,10)), 
				"survivalRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.KILL_CELL,10,9)), 
				"survivalRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.KILL_CELL,10,11)), 
				"survivalRules.apply returned incorrect actions");
		assertEquals(true, actions.contains(new Action(ActionType.KILL_CELL,13,13)), 
				"survivalRules.apply returned incorrect actions");
	}
}
