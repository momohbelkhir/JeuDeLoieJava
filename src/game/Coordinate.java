package game;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Coordinate implements Serializable {

  //
	private int y;
	private int x;

	public Coordinate(int x, int y) {
		this.y = y;
		this.x = x;
	
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

    public String toString() {
    	return "Coordinate "+x+" , "+y;
    }

}
