package chessboardDesign;
import helpers.*;
public class hanWarrior extends WOG {
	
	//constructor
	public hanWarrior(){
		this.type=	ChessType.hanWarrior;
	}
	
	/**
	 * The rule of Chinese warrior is:
	 * a.before passing the mid river, it can advance only one grid.
	 * b.after passing the mid, it can move a grid to left or right (or forward one again) 
	 * c.it can never withdraw
	 */
	@Override 
	public boolean ruleChecking(Chessboard board, Action plan, PlayerIE turn) {
		
		int departX = plan.getDepartX();
		int departY = plan.getDepartY();
		
		int destX = plan.getDestX();
		int destY = plan.getDestY();
		int diffX = Math.abs(destX-departX);
		
		
		//red pawn start at y=1
		if (turn==PlayerIE.red){
			if ((departX == destX) && (departY == (destY - 1)))
				return true;
			if ((departY >3) &&  (diffX == 1) && (departY ==destY))
				return true;
			
			return false;
		}
		else //black pawn start at y=6
		{
			if ((departX == destX) && (departY == (destY + 1)))
				return true;
			if ((departY < 4) &&  (diffX == 1) && (departY ==destY))
				return true;
			
			return false;
			
		}
	}
	
	//not necessary to be the king, but count if it can attack a grid
	@Override
	public boolean beatKing(Chessboard board,Coordinate kingCoor, Coordinate attackerCoor) {
		Action move=new Action(attackerCoor,kingCoor);
		return ruleChecking(board,move,player);	
	}
	
	
}
