package chessboardDesign;
import helpers.*;

public class Pawn extends WOG{
	public Pawn(){
		this.type=	ChessType.pawn;
	}
	
	
	
	
	@Override
	public boolean ruleChecking(Chessboard board, Action plan, PlayerIE turn) {
		
		int departX = plan.getDepartX();
		int departY = plan.getDepartY();
		
		int destX = plan.getDestX();
		int destY = plan.getDestY();
		
		Grid destG = board.getAGrid(plan.getDestCoor());
		
		//red pawn start at y=1
		if(turn == PlayerIE.red)
		{
			
			//if not capture something 
			if(destG.getEleOnGrid() == null)
			{
				//at first move, it can advance 2
				if ((departY==1) &&(departX == destX) && (departY == (destY - 2)))
					return true;
				
				//otherwise, only advance 1
				if((departX == destX) && (departY == (destY - 1)))
					return true;
				else
					return false;
				
			}
			else
			{
				//capture? go diagonally by 1 step
				if(Math.abs(destX - departX) == 1 && (departY == (destY - 1)))
					return true;
				else
					return false;
				
			}
		}
		else //black pawn start at y=6
		{
			if(destG.getEleOnGrid() == null)
			{
				
				if ((departY==6) &&(departX == destX) && (departY == (destY + 2)))
					return true;
				
				if((departX == destX) && (departY == (destY + 1)))
					return true;
				else
					return false;
				
			}
			else
			{
				if(Math.abs(destX - departX) == 1 && (departY == (destY + 1)))
					return true;
				else
					return false;
				
			}
		}
	}
	

	
	//not necessary to be the king, but count if it can attack a grid
	@Override
	public boolean beatKing(Chessboard board,Coordinate kingCoor, Coordinate attackerCoor) {
		Action move=new Action(attackerCoor,kingCoor);
		return ruleChecking(board,move,player);	
	}
	
	
	
	
	
	
}