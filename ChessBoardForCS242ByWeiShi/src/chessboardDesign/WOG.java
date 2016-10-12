package chessboardDesign;
import helpers.*;
import java.util.ArrayList;

//What's On Grid?
public abstract class WOG {
	
	//Coordinate in grid
	protected PlayerIE player;
	protected ChessType type;


	public PlayerIE getPlayer() {
		return player;
	}
	
	public void setPlayer(PlayerIE player) {
		this.player = player;
	}
	
	public ChessType getType() {
		return type;
	}
	
	public void setType( ChessType type) {
		this.type = type;
	}
	
	/*
	 * THis function is helping:
	 * 
	 * @param board - chessboard
	 * @param plan  - the player's planed action
	 * @param playnow - whoses turn
	 * check the general validity the a move's position change
	 * return valid=true, otherwise=false
	 * 
	 */
	public boolean positionHelper(Chessboard board, Action plan, PlayerIE playnow){
		return plan.positionHelper(board, playnow);
		
		
	}
	
	
	
	
	
	/*see if something has been jumped over
	 * @param route - coordinates of the path between two nodes
	 * 
	 * @return true - jump over something
	 * @return false - otherwise
	 */
	public boolean jumpOver(Chessboard board, ArrayList<Coordinate>route)
	{	
		
		Grid[][] myBoard = board.getBoard();
		
		//if one of grid between them is occupied, then something has been jumped over
		for(int i = 0; i < route.size(); i++)
			if((myBoard[route.get(i).getX()][route.get(i).getY()]).getEleOnGrid() != null)
				return true;
		
		
		return false;
	}
	
	
	public boolean jumpOverByOne(Chessboard board, ArrayList<Coordinate>route)
	{	
		
		Grid[][] myBoard = board.getBoard();
		int count=0;
		//if one of grid between them is occupied, then something has been jumped over
		for(int i = 0; i < route.size(); i++)
			if((myBoard[route.get(i).getX()][route.get(i).getY()]).getEleOnGrid() != null)
				count++;
		
		if (count==1) return true;
		
		return false;
	}
	
	
	
	
	
	
	
	/*
	 * abstract function
	 * check if a move fits the chess rule INDEPENDENTLY
	 * @param board - current chess board
	 * @param plan - the player's planed action
	 * @param player - which player is going to move in this turn
	 * @return true - valid;
	 * @return false - violate;
	 * */
	public abstract boolean ruleChecking(Chessboard board, Action plan,PlayerIE player);
	/*
	 * 
	 * abstract function
	 * check is the king is attacked by this WOG
	 * @param board - curent chess board 
	 * @param kingCoor - king's position
	 * @param attackerCoor shows potential attacker's coordinate
	 * @return true - attack the king
	 * @return false - otherwise
	 *
	 */
	public abstract boolean beatKing(Chessboard board, Coordinate kingCoor, Coordinate attackerCoor);
	
	
	
	
	
}
