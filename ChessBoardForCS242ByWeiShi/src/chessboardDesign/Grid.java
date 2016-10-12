package chessboardDesign;
import helpers.*; 

public class Grid {

	Coordinate coorG;
	
	//the element on grid
	WOG eleOnGrid;
	
	//empty grid constructor
	public Grid(Coordinate coorG) {
		eleOnGrid = null;
		this.coorG = coorG;
	} 
	
	//construct a grid occupied by a chess already
	public Grid(Coordinate coorG, PlayerIE player, ChessType type) {
		
		this.setWOG(player, type);
		this.coorG = coorG;
	}
	
	//copy constructor
	public Grid(Grid other){
		
		if (other.eleOnGrid==null) {
			
			eleOnGrid = null;
			coorG=new Coordinate(other.getCoor().getX(),other.getCoor().getY());
			
			
		}else{
			coorG=new Coordinate(other.getCoor().getX(),other.getCoor().getY());
			this.setWOG(other.eleOnGrid.player,other.eleOnGrid.type);
			
		}
		
		
	}
	
	
	//change the coordinate of a grid
	public void setCoor(Coordinate newCoor){
		coorG.setX(newCoor.getX());
		coorG.setY(newCoor.getY());
		
	}
	
	public Coordinate getCoor(){
		
		
		return coorG;
		
	}
	
	
	
	/*
	 * set the type and ownership of this grid
	 * @param player Once we need to set it, it's red or black but can't be white
	 * @param type: type of pieces chess
	 */
	public void setWOG(PlayerIE player, ChessType type) {
		if (type==ChessType.nothing){
			eleOnGrid=null;
			return;
		}	
			
		switch(type) {
			case pawn: {
				eleOnGrid = new Pawn();
				break;
			}
			case chineseCannon: {
				eleOnGrid = new chineseCannon();
				break;
			}
			case hanWarrior: {
				eleOnGrid = new hanWarrior();
				break;
			}
			case rook:{
				eleOnGrid = new Rook();
				break;
			}
			case knight:{
				eleOnGrid = new Knight();
				break;
			}
			case bishop:{
				eleOnGrid = new Bishop();
				break;
			}
			case queen:{
				eleOnGrid = new Queen();
				break;
			}
			case king:{
				eleOnGrid = new King();
				break;
			}
		
		}
		eleOnGrid.setPlayer(player);
	}
	
	//get WOG on this grid
	public WOG getEleOnGrid(){
		
		return eleOnGrid;	
	}
}