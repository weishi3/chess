package chessboardDesign;

import helpers.*;

//class for move action
public class Action {
	
	//coordinates for departure and destination
	private Coordinate departCoor, destCoor;
	private PlayerIE colorIsCaptured;
	private ChessType typeCaptured;
	
	public Action(Coordinate departCoor,Coordinate destCoor) {
		this.departCoor = departCoor;
		this.destCoor = destCoor;
		colorIsCaptured = PlayerIE.white;
		typeCaptured = ChessType.nothing;
	}

	public PlayerIE getCapColor(){
		return colorIsCaptured;
		
		
	}
	public void setCapColor(PlayerIE p){
		colorIsCaptured=p;
		
		
	}
	public void setCapType(ChessType t){
		typeCaptured=t;
		
		
	}
	
	
	public ChessType getCapType(){
		return typeCaptured;
		
		
	}

	public Coordinate getDepartCoor() {
		return departCoor;
	}
	
	

	

	public Coordinate getDestCoor() {
		return destCoor;
	}
	//helpers:
	
	public int getDepartX(){
		return departCoor.getX();
	}
	
	public int getDepartY(){
		return departCoor.getY();
	}
	
	public int getDestX(){
		return destCoor.getX();
	}
	
	public int getDestY(){
		return destCoor.getY();
	}
	
	
	/*
	 * All the following functions are irrelevant to chessPiece type
	 * 
	 * */
public boolean positionHelper(Chessboard board, PlayerIE playnow){
		
		//go outside boundary?//maybe it should in action//just keep it here
		if (!this.boundaryCheck(8,8)) return false;
		
		//no such chess for moving or try to capture same team's chess
		Coordinate departCoor = this.getDepartCoor();
		Coordinate destCoor = this.getDestCoor();
		
		Grid departG = board.getAGrid(departCoor);
		Grid destG = board.getAGrid(destCoor);
		
		if(departG.getEleOnGrid() == null || departG.getEleOnGrid().getPlayer() != playnow)
			return false;
		else if(destG.getEleOnGrid()!= null && destG.getEleOnGrid().getPlayer() == playnow)
			return false;
		
		//fail to move 
		if (this.failToMove()) 
			return false;
		
		
		
		//otherwise
		return true;
		
	}
	
	
	
	
	public boolean failToMove(){
		Coordinate departCoor = this.getDepartCoor();
		Coordinate destCoor = this.getDestCoor();
		if (departCoor.isSame(destCoor)) 
			return true;
		return false;
		
		
	}
	
	
	public boolean wouldCapture(Chessboard board, PlayerIE playnow){
		Coordinate departCoor = this.getDepartCoor();
		Coordinate destCoor = this.getDestCoor();
		
		Grid departG = board.getAGrid(departCoor);
		Grid destG = board.getAGrid(destCoor);
		if(destG.getEleOnGrid()!= null && destG.getEleOnGrid().getPlayer() != playnow)
			return true;
		
		
		return false;
	}
	/*check if a move would go outside boundary or go from somewhere outside the board
	 *@param boardX,boardY size of axis help when extending board size
	 *@return false if outside boundary
	 *@return true otherwise
	 */
	public boolean boundaryCheck(int boardX,int boardY) {
		
	    int maxX = boardX-1;
	    int maxY = boardY-1;
		int departX = this.getDepartCoor().getX();
		int departY = this.getDepartCoor().getY();
		int destX = this.getDestCoor().getX();
		int destY = this.getDestCoor().getY();
		
		if(departX < 0 || departX > maxX|| departY < 0 || departY > maxY || destX < 0 || destX > maxX || destY < 0 || destY > maxY)
			return false;
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
