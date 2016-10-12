package chessboardDesign;

import helpers.*;

//subclass for WOG, king
public class King extends WOG{
	//constructor
	public King(){
		this.type=	ChessType.king;
	}
	
	
	//check if a king's action is valid
	@Override
	public boolean ruleChecking(Chessboard board,Action plan, PlayerIE player){
		
		
		int departX = plan.getDepartX();
		int departY = plan.getDepartY();
		int destX = plan.getDestX();
		int destY = plan.getDestY();
		int diffX = Math.abs(departX - destX);
		int diffY = Math.abs(departY - destY);
		
		
		/*
		System.out.println("kingchecking");
		System.out.println(diffY);
		System.out.println(departY);
		System.out.println(destY);*/
		//if ((diffY == 1)||(diffX == 1)) return true;
		if ((diffY <= 1)&&(diffX <= 1)) return true;
		return false;
		
		
		
	}
	
	//see if a king beats the other king
	@Override
	public boolean beatKing(Chessboard board, Coordinate kingCoor, Coordinate attackerCoor){
		//if (board.getAGrid(attackerCoor).getEleOnGrid().getType()==ChessType.king
			//	&& board.getAGrid(attackerCoor).getEleOnGrid().getPlayer()==PlayerIE.red)
			//System.out.println(attackerCoor.getY());//king
		//System.out.println(kingCoor.getY()); //queen
	
		Action move = new Action(attackerCoor, kingCoor);
		return ruleChecking(board, move,player);
		
		
	}
	
	
}
