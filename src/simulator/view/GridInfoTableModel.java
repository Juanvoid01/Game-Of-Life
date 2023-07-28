package simulator.view;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Grid;
import simulator.model.SimulatorObserver;

class GridInfoTableModel extends AbstractTableModel implements SimulatorObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String[] _header = { "Row", "Col", "Neighbours", "Age"};
	Grid grid;
	
	GridInfoTableModel(Controller ctrl) {
		
		
		ctrl.addObserver(this);
	}


	public String getColumnName(int col) {
		return _header[col];
	}
	
	@Override
	public int getRowCount() {
		
		return this.grid.getCellsAlive();
	}

	@Override
	public int getColumnCount() {
		
		return _header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		int rows = grid.getRows();
		int cols = grid.getCols();
		
		int row = 0, col = 0, neighbours = 0, age = 0;
		
		int counter = 0;
		boolean found = false;
		int i = 0;
		int limit = rows * cols;
		int cellsAlive = grid.getCellsAlive();
		while(!found && i < limit && counter < cellsAlive) {
			
			row = i / cols;
			col = i % cols;
			
			if(grid.isAlive(row, col)) {
				counter++;
				if(counter == rowIndex) {
					neighbours = grid.getNumberOfNeighbours(row, col);
					age = grid.getAge(row, col);
					found = true;
				}
			}
			i++;
		}

		
		if(columnIndex == 0) {
			return row;
		}
		else if(columnIndex == 1) {
			return col;
		}
		else if(columnIndex == 2) {
			return neighbours;
		}
		else if(columnIndex == 3) {
			return age;
		}
		
		return null;
	}
	@Override
	public void onAdvance(Grid grid, int generationCount) {
		
		int cellsAlive = grid.getCellsAlive();
		this.grid = grid;
		if(cellsAlive!=grid.getCellsAlive()) {
			fireTableStructureChanged();
		}

		fireTableDataChanged();
	}
	@Override
	public void onReset() {
		
		this.grid.reset();
		fireTableStructureChanged();
		fireTableDataChanged();
	}
	@Override
	public void onRegister(Grid grid) {
		
		this.grid = grid;
		fireTableStructureChanged();
		fireTableDataChanged();
	}


	@Override
	public void onCellAdded(Grid grid, int row, int col) {
		
		
		this.grid = grid;
		fireTableStructureChanged();
		fireTableDataChanged();
	}


	@Override
	public void onCellRemoved(Grid grid, int row, int col) {
		
		this.grid = grid;
		fireTableStructureChanged();
		fireTableDataChanged();
	}


}

