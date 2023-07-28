package simulator.model;

import java.util.ArrayList;
import java.util.List;


public class GenerationSimulator implements Observable<SimulatorObserver>{
	
	private Grid grid;
	private ReproductionRules reproductionRules;
	private SurvivalRules survivalRules;
	private List<Action> actions;
	private int generationCount;
	
	private List<SimulatorObserver> observers;
	
	public GenerationSimulator(int tamGrid, ReproductionRules reproductionRules, SurvivalRules survivalRules){
		
		
		grid = new Grid(tamGrid,tamGrid);
		actions = new ArrayList<Action>();
		generationCount = 0;
		
		observers = new ArrayList<SimulatorObserver>();
		
		this.reproductionRules = reproductionRules;
		this.survivalRules = survivalRules;
		
		initializeCells();

	}
	

	
	public void advance() {
		survivalRules.apply(grid, actions);
		reproductionRules.apply(grid,actions);
		executeActions();
		generationCount++;
		
		incrementAge();
		
		for(SimulatorObserver o : observers) {
			o.onAdvance(grid, generationCount);
		}
		
	}
	
	void incrementAge() {
		int rows = grid.getRows();
		int cols = grid.getCols();
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(grid.isAlive(row, col)) {
					grid.incrementAge(row, col);
				}
			}
		}
	}
	
	public void reset() {
		grid.reset();
		actions.clear();
		
		for(SimulatorObserver o : observers) {
			o.onReset();
		}
	}
	
	public void addCell(int row, int col) {
		
		grid.born(row, col);
		
		for(SimulatorObserver o : observers) {
			o.onCellAdded(grid, row, col);;
		}
	}
	

	
	public void killCell(int row, int col) {
		
		grid.kill(row, col);
		
		for(SimulatorObserver o : observers) {
			o.onCellRemoved(grid, row, col);;
		}
	}
	
	private void executeActions() {
		for(Action action:actions) {
			switch(action.getType()) {
			case ADD_CELL: grid.born(action.getRow(), action.getCol());;
				break;
			case KILL_CELL: grid.kill(action.getRow(),action.getCol());
				break;
			default:
				break;
			}
		}
		actions.clear();
	}
	
	private void initializeCells() {

		grid.born(29,31);
		grid.born(31,30);
		grid.born(30,29);
		grid.born(29,29);
		grid.born(29,30);

	}


	public void setReproductionRules(ReproductionRules newReproductionRules) {
		reproductionRules = newReproductionRules;
	}


	public void setSurvivalRules(SurvivalRules newSurvivalRules) {
		survivalRules = newSurvivalRules;
	}
	
	public ReproductionRules getReproductionRules() {
		return reproductionRules;
	}


	public SurvivalRules getSurvivalRules() {
		return survivalRules;
	}

	@Override
	public void addObserver(SimulatorObserver o) {
		// TODO Auto-generated method stub
		if(!observers.contains(o)) {
			observers.add(o);
			
			o.onRegister(grid);
		}
	}



	@Override
	public void removeObserver(SimulatorObserver o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}



	
}
