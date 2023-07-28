package simulator.model;

import java.util.Objects;

public class Action {

	private ActionType type;
	private int row;
	private int col;
	
	public Action(ActionType type, int row, int col) {
		this.type = type;
		this.row = row;
		this.col = col;
	}

	public ActionType getType() {
		return type;
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Action other = (Action) obj;
		return col == other.col && row == other.row && type == other.type;
	}


	
}

enum ActionType{
	ADD_FUTURECELL,ADD_CELL,KILL_FUTURECELL,KILL_CELL
}