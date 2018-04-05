package game;



public class Path {
	
	Coordinate pos;
	PathType type;
	
	
	
	
	
	
	
	public Path(Coordinate pos, PathType type) {
		super();
		this.pos = pos;
		this.type = type;
	}
	
	public Coordinate getPos() {
		return pos;
	}
	public void setPos(Coordinate pos) {
		this.pos = pos;
	}
	
	
	public PathType getType() {
		return type;
	}
	public void setType(PathType type) {
		this.type = type;
	}
	
	

}
