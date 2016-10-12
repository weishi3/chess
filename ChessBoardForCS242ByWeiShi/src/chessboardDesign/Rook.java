package chessboardDesign;
import helpers.*;

import java.util.ArrayList;
public class Rook extends WOG{
	
	public Rook(){
		this.type =	ChessType.rook;
	}
	
	
	@Override
	public boolean ruleChecking(Chessboard board, Action plan,
			PlayerIE player) {
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
				
			
			if(jumpOver(board, route))
				return false;
			else
				return true;
			
		}
		else if(departY == destY)//left or right
		{
			
			lower = (departX < destX)? departX : destX;
			higher = (departX < destX)? destX : departX;
			
			for(int i = lower + 1; i < higher; i++)
				route.add(new Coordinate(i, departY));
			
			if(jumpOver(board, route))
				return false;
			else
				return true;
			
		 }
		else
			return false;
	}
	
	
	
	
	//kill a king?
	@Override
	public boolean beatKing(Chessboard board,Coordinate kingCoor, Coordinate attackerCoor) {
		Action move=new Action(attackerCoor,kingCoor);
		return ruleChecking(board,move,player);	
	}
}
