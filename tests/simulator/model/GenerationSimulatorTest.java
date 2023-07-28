package simulator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GenerationSimulatorTest {
	
	@Test
	void basic_behaviour() {
		
		GenerationSimulator gs = new GenerationSimulator(30, new ClassicReproductionRules(),new ClassicSurvivalRules());
		ObserverTest observerTest = new ObserverTest();
		gs.addObserver(observerTest);
		
		gs.reset();
		//testing advance()
		
		gs.addCell(9,9);
		gs.addCell(10,9);
		gs.addCell(11,9);
		gs.addCell(9,10);
		gs.addCell(10,10);
		gs.addCell(11,10);
		gs.addCell(9,11);
		gs.addCell(10,11);
		gs.addCell(11, 11);
		gs.addCell(13, 13);
		

		
		assertEquals(10, observerTest.grid.getCellsAlive(), 
				"addCell on GenerationSimulator returned wrong number of cells");
		


		
	
		gs.advance();
		gs.advance();
		gs.advance();
		
		
		assertEquals(12, observerTest.grid.getCellsAlive(), 
				"advance on GenerationSimulator returned wrong number of cells");
		

		assertEquals(true, observerTest.grid.isAlive(9, 8), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(10, 8), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(11, 8), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(9, 12), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(10, 12), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(11, 12), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(8, 9), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(8, 10), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(8, 11), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(12, 9), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(12, 10), 
				"advance on GenerationSimulator returned incorrect cells");
		assertEquals(true, observerTest.grid.isAlive(12, 11), 
				"advance on GenerationSimulator returned incorrect cells");
	}
	
	class ObserverTest implements SimulatorObserver{

		public Grid grid;
		@Override
		public void onAdvance(Grid grid, int generationCount) {
			// TODO Auto-generated method stub
			this.grid = grid;
		}

		@Override
		public void onReset() {
			// TODO Auto-generated method stub
			grid.reset();
		}

		@Override
		public void onRegister(Grid grid) {
			// TODO Auto-generated method stub
			this.grid = grid;
		}

		@Override
		public void onCellAdded(Grid grid, int row, int col) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onCellRemoved(Grid grid, int row, int col) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
