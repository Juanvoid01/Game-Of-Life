package simulator.miscellany;

import java.util.Objects;

public class Vector2D {

	private int x;
	private int y;
	
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Vector2D vector) {
		x = vector.getX();
		y = vector.getY();
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void add(Vector2D vector) {
		x += vector.getX();
		y += vector.getY();
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		return x == other.x && y == other.y;
	}

	// return a string representation of the vector
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
}
