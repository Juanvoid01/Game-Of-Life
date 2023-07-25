package simulator.model;

import java.util.Objects;

import simulator.miscellany.Vector2D;

public class Cell {
	
	private Vector2D pos;
	private int numberOfNeighbours;
	private int age;
	
	public Cell(Vector2D pos) {
		this.pos = pos;
		this.numberOfNeighbours = 0;
		this.age = 0;
	}
	
	public Cell(Vector2D pos, int numberOfNeighbours) {
		this.pos = pos;
		this.numberOfNeighbours = numberOfNeighbours;
		this.age = 0;
	}

	public Cell(Cell cell) {
		pos = cell.getPos();
		numberOfNeighbours = cell.getNumberOfNeighbours();
		age = cell.getAge();
	}

	public Vector2D getPos() {
		return pos;
	}
	
	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	public int getNumberOfNeighbours() {
		return numberOfNeighbours;
	}

	public void setNumberOfNeighbours(int numberOfNeighbours) {
		this.numberOfNeighbours = numberOfNeighbours;
	}

	public void incrementNumberOfNeighbours(int numberOfnewNeighbours) {
		this.numberOfNeighbours += numberOfnewNeighbours;
	}
	public int getAge() {
		return age;
	}

	public void incrementAge() {
		this.age++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		return pos == other.getPos();
	}
	
}
