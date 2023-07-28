package simulator.model;

import java.util.Objects;

public class Cell {
	
	private boolean alive;
	private int numberOfNeighbours;
	private int age;
	
	public Cell() {
		alive = false;
		numberOfNeighbours = 0;
		age = 0;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public int getNumberOfNeighbours() {
		return numberOfNeighbours;
	}

	public void setNumberOfNeighbours(int numberOfNeighbours) {
		this.numberOfNeighbours = numberOfNeighbours;
	}

	public void incrementNeighbours() {
		this.numberOfNeighbours++;
	}
	
	public void decrementNeighbours() {
		this.numberOfNeighbours--;
	}
	
	public int getAge() {
		return age;
	}

	public void incrementAge() {
		this.age++;
	}
	
	public void kill() {
		alive = false;
		age = 0;
	}
	
	public void born() {
		alive = true;
		age = 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(age, alive, numberOfNeighbours);
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
		return age == other.age && alive == other.alive && numberOfNeighbours == other.numberOfNeighbours;
	}
	
	
}
