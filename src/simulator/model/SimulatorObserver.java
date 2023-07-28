package simulator.model;

public interface SimulatorObserver {
	public void onAdvance(Grid grid, int generationCount);
	public void onReset();
	public void onRegister(Grid grid);
	public void onCellAdded(Grid grid, int row, int col);
	public void onCellRemoved(Grid grid, int row, int col);
}
