package chessboardDesign;
import helpers.*;

public class Knight extends WOG{
	public Knight(){
		this.type=	ChessType.knight;
	}
	
	
	//@param player is actually not needed in this function
	@Override
	public boolean ruleChecking(Chessboard board,Action plan, PlayerIE player){
		
		int departX = plan.getDepartX();
		int departY = plan.getDepartY();
		
		int destX = plan.getDestX();
		int destY = plan.getDestY();
		
		int diffX = Math.abs(departX-destX);
		int diffY = Math.abs(departY-destY);
		
		if ((diffX == 2 &&diffY == 1) || (diffX == 1 && diffY == 2)) 
			return true;
		
		return false;
		
		
		
	}
	
	//this knight check the king?
	@Override
	public boolean beatKing(Chessboard board, Coordinate kingCoor, Coordinate attackerCoor){
		
		Action move = new Action(attackerCoor, kingCoor);
		return ruleChecking(board, move, player);
		
		
	}
	
	
	
	
	
	
	
	
	
}
