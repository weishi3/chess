package chessboardDesign;
import helpers.*;
public class Queen extends WOG{

	public Queen(){
		this.type=	ChessType.queen;
	}
	
	
	//I am combo of rook and bishop!
	//I am the super version of "king"
	@Override
	public boolean ruleChecking(Chessboard board, Action plan, PlayerIE player) {
		//so I have the ability of bishop and rook
		Bishop virtualBishop = new Bishop();
		Rook virtualRook = new Rook();
		
		return (virtualBishop.ruleChecking(board, plan, player) || virtualRook.ruleChecking(board, plan, player));
		
		
		
	}
	
	//kill the other king, in the name of your husband!
	@Override
	public boolean beatKing(Chessboard board,Coordinate kingCoor, Coordinate attackerCoor) {
		Action move = new Action(attackerCoor, kingCoor);
		
		return ruleChecking(board, move, player);	
	}
	
}
