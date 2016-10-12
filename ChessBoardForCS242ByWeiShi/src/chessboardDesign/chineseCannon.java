package chessboardDesign;
import java.util.ArrayList;

import helpers.*;

public class chineseCannon extends WOG {
	//constructor for a piece of chess from China
	public chineseCannon(){
		this.type=	ChessType.chineseCannon;
	}
	
	
	
	/**
	 * the rule is :
	 * a. it can only move up/down or left/right
	 * b. if not going to capture another piece, it cannot jump over
	 * c. if going to capture, it must jump over another piece (can be every other piece except itself)
	 */
	public boolean ruleChecking(Chessboard board,Action plan, PlayerIE player){
			
			
		ArrayList<Coordinate> route = new ArrayList<Coordinate>();
		
		int departX = plan.getDepartX();
		int departY = plan.getDepartY();
		
		int destX = plan.getDestX();
		int destY = plan.getDestY();
		
		int lower, higher;
		
		//upward or downward
		if(departX == destX)
		{
			
			lower = (departY < destY)? departY : destY;
			higher = (departY < destY)? destY : departY;
			
			for(int i = lower + 1; i < higher; i++)
				route.add(new Coordinate(departX,i));
				
			
			if(jumpOverByOne(board, route) && plan.wouldCapture(board, player))
				return true;
			else if(jumpOver(board, route))
				return false;
			else if (plan.wouldCapture(board, player))
				return false;
			
				return true;
			
		}
		else if(departY == destY)//left or right
		{
			
			lower = (departX < destX)? departX : destX;
			higher = (departX < destX)? destX : departX;
			
			for(int i = lower + 1; i < higher; i++)
				route.add(new Coordinate(departX,i));
				
			
			if(jumpOverByOne(board, route) && plan.wouldCapture(board, player))
				return true;
			else if(jumpOver(board, route))
				return false;
			else if (plan.wouldCapture(board, player))
				return false;
			
				return true;
			
			
		 }
		else
			return false;
			
			
			
	}
	
	
	//not necessary to be the king, but count if it can attack a grid
	@Override
	public boolean beatKing(Chessboard board, Coordinate kingCoor, Coordinate attackerCoor){
		
	
		Action move = new Action(attackerCoor, kingCoor);
		return ruleChecking(board, move,player);
		
		
	}


	
	
}
