package simulator.model;

import java.util.Objects;

import simulator.miscellany.Vector2D;

public class Action {

	private ActionType type;
	private Vector2D pos;
	
	public Action(ActionType type, Vector2D pos) {
		this.type = type;
		this.pos = pos;
	}

	public ActionType getType() {
		return type;
	}

	public Vector2D getPos() {
		return pos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pos, type);
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
		return Objects.equals(pos, other.pos) && type == other.type;
	}
	
}

enum ActionType{
	ADD_FUTURECELL,ADD_CELL,KILL_FUTURECELL,KILL_CELL
}