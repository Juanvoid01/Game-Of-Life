package simulator.view;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.miscellany.Vector2D;
import simulator.model.Cell;
import simulator.model.SimulatorObserver;

public class InfoTableModel extends AbstractTableModel implements SimulatorObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] _headers = { "Row", "Col", "Neighbours", "Age" };
	
	Map<Vector2D, Cell> cells;
	
	private int numberOfCells;
	
	InfoTableModel(Controller ctrl) {

		numberOfCells = 0;
		ctrl.addObserver(this);
	}
	
	public String getColumnName(int col) {
		return _headers[col];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return numberOfCells;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return _headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		/*if(columnIndex == 0) {
			return cells.get(rowIndex).getId();
		}
		else if(columnIndex == 1) {
			return _groups.get(rowIndex).getForceLawsInfo();
		}
		else if(columnIndex == 2) {
			String s = "";
			for(Body b : _groups.get(rowIndex)) {
				s += b.getId() + " ";
			}
			return s;
		}
		*/
		return null;
	}

	@Override
	public void onAdvance(Map<Vector2D, Cell> cells, int generationCount) {
		// TODO Auto-generated method stub
		this.cells = cells;
	}

	@Override
	public void onReset() {
		// TODO Auto-generated method stub
		cells.clear();
	}

	@Override
	public void onRegister(Map<Vector2D, Cell> cells) {
		// TODO Auto-generated method stub
		this.cells = cells;
	}

}
