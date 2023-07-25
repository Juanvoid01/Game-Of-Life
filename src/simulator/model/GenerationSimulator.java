package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import simulator.miscellany.Vector2D;

public class GenerationSimulator implements Observable<SimulatorObserver>{

	private Map<Vector2D,Cell> cells;
	private Map<Vector2D,Cell> futureCells;
	private List<Action> actions;

	private List<SimulatorObserver> observers;
	
	private ReproductionRules reproductionRules;
	private SurvivalRules survivalRules;
	
	private int generationCount;
	
	public GenerationSimulator(ReproductionRules reproductionRules, SurvivalRules survivalRules){
		
		cells = new HashMap<Vector2D,Cell>();
		futureCells = new HashMap<Vector2D,Cell>();
		actions = new ArrayList<Action>();
		observers = new ArrayList<SimulatorObserver>();
		
		this.reproductionRules = reproductionRules;
		this.survivalRules = survivalRules;
		generationCount = 0;
		initializeCells();

	}
	

	
	public void advance() {
		survivalRules.apply(cells, actions);
		reproductionRules.apply(futureCells,actions);
		executeActions();
		generationCount++;
		for(SimulatorObserver o : observers) {
			o.onAdvance(cells, generationCount);
		}
		
	}
	

	
	public void reset() {
		cells.clear();
		futureCells.clear();
		actions.clear();
		
		for(SimulatorObserver o : observers) {
			o.onReset();
		}
	}
	
	public void addCell(Vector2D pos) {
		Cell newCell = new Cell(pos);
		
		cells.put(pos, newCell);
		
		futureCells.remove(pos);
		
		//we update the 8 adjacent squares
		//an adjacent square can be another cell, a future cell or an empty square
		
		Vector2D auxPos = new Vector2D();

		for(int i = -1; i<=1; i++) {
			for(int j = -1; j<=1; j++) {
				if(i != 0 || j != 0) {
					auxPos.setX(pos.getX() + i);
					auxPos.setY(pos.getY() + j);
					
					if(cells.containsKey(auxPos)) {
						cells.get(auxPos).incrementNumberOfNeighbours(1);
						newCell.incrementNumberOfNeighbours(1);
					}
					else if(futureCells.containsKey(auxPos)) {
						futureCells.get(auxPos).incrementNumberOfNeighbours(1);
					}
					else {
						addFutureCell(auxPos);
					}
					
				}	
			}
		}

	}
	
	private void addFutureCell(Vector2D pos) {
		
		Cell newFutureCell = new Cell(pos);
		futureCells.put(pos, newFutureCell);
		
		Vector2D auxPos = new Vector2D();
		
		for(int i = -1; i<=1; i++) {
			for(int j = -1; j<=1; j++) {
				
				if(i != 0 || j != 0) {
					
					auxPos.setX(pos.getX() + i);
					auxPos.setY(pos.getY() + j);
					
					if(cells.containsKey(auxPos)) {
						newFutureCell.incrementNumberOfNeighbours(1);
					}
				}
					
			}
		}
	}
	
	public void killCell(Vector2D pos) {
		
		cells.remove(pos);
		
		Vector2D auxPos = new Vector2D();

		for(int i = -1; i<=1; i++) {
			for(int j = -1; j<=1; j++) {
				if(i != 0 || j != 0) {
					auxPos.setX(pos.getX() + i);
					auxPos.setY(pos.getY() + j);
					
					if(cells.containsKey(auxPos)) {
						cells.get(auxPos).incrementNumberOfNeighbours(-1);
					}
					else if(futureCells.containsKey(auxPos)) {
						Cell Caux = futureCells.get(auxPos);
						Caux.incrementNumberOfNeighbours(-1);
						if(Caux.getNumberOfNeighbours() == 0) {
							futureCells.remove(auxPos);
						}
					}
					else {
						
					}
					
				}	
			}
		}
	}
	
	private void executeActions() {
		for(Action action:actions) {
			switch(action.getType()) {
			case ADD_CELL: addCell(action.getPos());
				break;
			case KILL_CELL: killCell(action.getPos());
				break;
			default:
				break;
			}
		}
		actions.clear();
	}
	
	private void initializeCells() {

		addCell(new Vector2D(0,1));
		addCell(new Vector2D(1,0));
		addCell(new Vector2D(0,-1));
		addCell(new Vector2D(-1,-1));
		addCell(new Vector2D(-1,0));

	}


	public void setReproductionRules(ReproductionRules newReproductionRules) {
		reproductionRules = newReproductionRules;
	}


	public void setSurvivalRules(SurvivalRules newSurvivalRules) {
		survivalRules = newSurvivalRules;
	}

	@Override
	public void addObserver(SimulatorObserver o) {
		// TODO Auto-generated method stub
		if(!observers.contains(o)) {
			observers.add(o);
			
			o.onRegister(cells);
		}
	}



	@Override
	public void removeObserver(SimulatorObserver o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}



	
}
