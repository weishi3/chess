package helpers;

public class Coordinate{
	private int X;
	private int Y;
	
	public Coordinate(int X,int Y){
		this.X = X;
		this.Y = Y;
	}
	
	public int getX() { return X; }
	public int getY() { return Y; }
	public void setX(int X) {this.X = X; }
	public void setY(int Y) {this.Y = Y; }
	
	public boolean isSame(Coordinate c){
		if (c==null) return false;
		
		return (this.X==c.X) && (this.Y == c.Y);
		
		
	}
	
	
	
	
	
}
