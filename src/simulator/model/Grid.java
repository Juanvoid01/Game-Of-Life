package simulator.model;

public class Grid {

	private Cell grid[][];
	private int rows;
	private int cols;

	private int cellsAlive;
	
	public Grid(int rows, int cols) {
		
		this.rows = rows;
		this.cols = cols;
		reset();
	}
	
	public int getCellsAlive() {
		return cellsAlive;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public void reset() {
		
		cellsAlive = 0;
		grid = new Cell[rows][cols];
		
		for(int i = 0;i<rows;i++) {
			for(int j = 0;j<rows;j++) {
				grid[i][j] = new Cell();
			}
		}
		
	}
	
	public boolean isAlive(int row,int col) {
		
		row = wrapRow(row);
		col = wrapCol(col);
		
		return grid[row][col].isAlive();
	}
	
	public int getNumberOfNeighbours(int row,int col) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		return grid[row][col].getNumberOfNeighbours();
	}
	
	public void setNumberOfNeighbours(int row,int col , int n) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		grid[row][col].setNumberOfNeighbours(n);
	}
	
	public void incrementNeighbours(int row,int col) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		grid[row][col].incrementNeighbours();
	}
	
	public void decrementNeighbours(int row,int col) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		grid[row][col].decrementNeighbours();
	}
	
	public void incrementAge(int row,int col) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		grid[row][col].incrementAge();
	}
	
	public int getAge(int row,int col) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		return grid[row][col].getAge();
	}
	
	public Cell getCell(int row,int col) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		return grid[row][col];
	}
	
	public void setCell(int row,int col, Cell cell) {
		row = wrapRow(row);
		col = wrapCol(col);
		
		if(grid[row][col].isAlive()) {
			if(!cell.isAlive()) {
				cellsAlive--;
			}
		}
		else{
			if(cell.isAlive()) {
				cellsAlive++;
			}
		}
		grid[row][col] = cell;
	}
	
	public void born(int row,int col) {
		
		row = wrapRow(row);
		col = wrapCol(col);
		
		if(!grid[row][col].isAlive()) {
			cellsAlive++;
			grid[row][col].born();
			
			for(int i = -1; i<=1; i++) {
				for(int j = -1; j<=1; j++) {
					if(i!=0 || j != 0) {
						incrementNeighbours(row + i, col + j);
					}
				}	
			}
		}
	}
	
	public void kill(int row, int col) {

		row = wrapRow(row);
		col = wrapCol(col);
		
		if (!grid[row][col].isAlive()) {
			return;
		}
			
		
		cellsAlive--;
		grid[row][col].kill();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) {
					decrementNeighbours(row + i, col + j);
				}
			}
		}
		

	}
	
	public boolean validPosition(int row,int col) {
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}
	
	//methods to convert the array in a toloidal array
	public int wrapRow(int row) {
        return (row + rows) % rows;
    }

    public int wrapCol(int col) {
        return (col + cols) % cols;
    }
}
