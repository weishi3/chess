package chessboardDesign;
import java.util.ArrayList;

import helpers.*;

//subclass for WOG
public class Bishop extends WOG{
	public Bishop(){
		this.type=	ChessType.bishop;
	}
	
	
	//check for rule of bishop
	@Override
	public boolean ruleChecking(Chessboard board, Action plan,
			PlayerIE player) {
		ArrayList<Coordinate> route = new ArrayList<Coordinate>();
		
		int departX = plan.getDepartX();
		int departY = plan.getDepartY();
		
		int destX = plan.getDestX();
		int destY = plan.getDestY();
		
		int diffX = Math.abs(departX - destX);
		int diffY = Math.abs(departY - destY);
		
		if (diffY != diffX) 
			return false;
		
		else if ((departX < destX && departY < destY) || (departX > destX && departY > destY)){
			
			int lowerX = (departX < destX)? departX : destX;
			int lowerY = (departY < destY)? departY : destY;
			
			for(int i =  1; i < diffY; i++)
				route.add(new Coordinate(lowerX+i, lowerY+i));
				
			
			
			if(jumpOver(board, route))
				return false;
			else
				return true;
			
			
			
		}else if (departX < destX && departY > destY){
			
			for(int i =  1; i < diffY; i++)
				route.add(new Coordinate(departX+i, departY-i));
			
			if(jumpOver(board, route))
				return false;
			else
				return true;
			
			
			
		}else{
			
			for(int i =  1; i < diffY; i++)
				route.add(new Coordinate(departX - i,departY + i));
			
			
			if(jumpOver(board, route))
				return false;
			else
				return true;
			
			
		}
	}
	
		//see if the bishop can attack a grid
		@Override
		public boolean beatKing(Chessboard board, Coordinate kingCoor, Coordinate attackerCoor){
			
			Action move=new Action(attackerCoor,kingCoor);
			return ruleChecking(board,move,player);
		
		
	}
		 
	
	
	
	
}
