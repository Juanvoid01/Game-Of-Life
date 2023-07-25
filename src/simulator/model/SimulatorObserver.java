package simulator.model;

import java.util.Map;

import simulator.miscellany.Vector2D;

public interface SimulatorObserver {
	public void onAdvance(Map<Vector2D, Cell> cells, int generationCount);
	public void onReset();
	public void onRegister(Map<Vector2D, Cell> cells);
}
