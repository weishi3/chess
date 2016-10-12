package playChess;
import chessboardDesign.*;
import gui.*;
import helpers.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import gui.*;
//chess game object
public class Game {
	
	private Chessboard board;
	private ChessboardGUI linkedGui;
	private Stack<Action> log = new Stack<Action>();
	
	private boolean isRedInCheck, isBlackInCheck, isRedCheckMate, isBlackCheckMate;
	
	private PlayerIE  turn, winner;
	
	//red's and black's pieces of chess which haven't been captured
	public ArrayList<Grid> aliveRed, aliveBlack;
	
	//attempt to move a chess from a coordinate to another
	private Coordinate fromCoor, toCoor;
	
	//coordinate for both kings
	public Coordinate kingRCoor, kingBCoor;
	
	
	//reset fromCoor and toCoor
	public void newAttempt(Action move){
		
		fromCoor = move.getDepartCoor();
		toCoor = move.getDestCoor();
	}
	
	
	public Chessboard getBoard() {
		return this.board;
	}
	
	public PlayerIE getGameTurn() {
		return this.turn;
	}
	
	public PlayerIE getWinner() {
		return this.winner;
	}
	
	//constructor for game object
	public Game(ChessboardGUI linkedGui)
	{
		this.linkedGui = linkedGui;
		isRedInCheck = isBlackInCheck = isRedCheckMate = isBlackCheckMate = false;
		
		board = new Chessboard();//initialize a 8*8 board
		
		turn = PlayerIE.red;
		
		winner = PlayerIE.white;//no one win
	
		
		
		//initial no move, to prevent NULL coor, initialize them with (-1,-1)
		fromCoor=new Coordinate(-1,-1);
		toCoor=new Coordinate(-1,-1);
		
		kingRCoor=new Coordinate(4,0);
		kingBCoor=new Coordinate(4,7);
		
		aliveRed = new ArrayList<Grid>();
		aliveBlack = new ArrayList<Grid>();
		
		int[] initY = {0, 1, 6, 7};
		for(int j = 0; j < initY.length; j++) {
			for(int i = 0; i < 8; i++) {
				
				if(initY[j] == 0 || initY[j] == 1){
					aliveRed.add(board.getAGrid(new Coordinate(i,initY[j])));
					
					
				}else
					aliveBlack.add(board.getAGrid(new Coordinate(i,initY[j])));
				
			}
		}
		
	}
	
	
	/**
	 * This function distinguish whether you want to choose or move a piece of chess
	 * Also it would change the background color of the single grid to remind you of the status
	 * 
	 * @param input -- the position of a button you click on 
	 */
	public void click(Coordinate input) {
		int x = input.getX();
		int y = input.getY();
		if(fromCoor.getX() < 0) {
			fromCoor.setX(x);
			fromCoor.setY(y);
			this.linkedGui.changeButtonColor(input);
		}
		else {
			toCoor.setX(x);
			toCoor.setY(y);
		
			Move();
			this.linkedGui.recoverButtonColor(fromCoor);
			

			toCoor = new Coordinate(-1,-1);
			fromCoor = new Coordinate(-1,-1);
		}
	}
	
	//check if the planed move is valid
	private boolean isMoveValid(Action move) {
		
		
		if (!move.boundaryCheck(board.getBoardX(), board.getBoardY())) 
			return false;
		WOG chessToMove = board.getAGrid(move.getDepartCoor()).getEleOnGrid();
		
		//no chess there
		if (chessToMove==null) 
			return false;
		
		
		// check for general rule
		boolean generalvalid = ( chessToMove.positionHelper(board, move, turn));
		
		if(!generalvalid)	
			return false;
		
		//fits the type?
		WOG active=board.getAGrid(move.getDepartCoor()).getEleOnGrid();
		
		if (active.ruleChecking(board, move,active.getPlayer())==false) {
			
			
			return false;
			
		}
			
		Chessboard virtual = new Chessboard(board);
		virtual.moveAChess(move);
		
		Coordinate tempkingR=kingRCoor;
		Coordinate tempkingB=kingBCoor;
		
		if (board.getAGrid(move.getDepartCoor()).getEleOnGrid().getType()==ChessType.king){
			if (board.getAGrid(move.getDepartCoor()).getEleOnGrid().getPlayer()==PlayerIE.red){
				tempkingR = move.getDestCoor();
			}	else if (board.getAGrid(move.getDepartCoor()).getEleOnGrid().getPlayer()==PlayerIE.black){
				tempkingB = move.getDestCoor();
				
			}
		}		
				
				
		//System.out.println(chessInCheck(virtual,turn == PlayerIE.red? kingRCoor:kingBCoor,turn == PlayerIE.red ? aliveBlack : aliveRed));
		// move a chess would cause king to die?
		if(chessInCheck(virtual,turn == PlayerIE.red? tempkingR:tempkingB,turn == PlayerIE.red ? aliveBlack : aliveRed)){
			
			return false;
		}	
		else {
			//recover uncheck status
			//the planed action is valid and king is no longer in check
			if(turn == PlayerIE.red && isRedInCheck == true)
				isRedInCheck = false;
			else
				isBlackInCheck = false;
			return true;
		}	
	}
	
	
	//update inCheck, checkMate, winner value
	private void updateStatus() {
		if(turn == PlayerIE.red) {
			
			
			isRedInCheck = chessInCheck(this.board, this.kingRCoor,this.aliveBlack);
			if (isRedInCheck)
				linkedGui.showInCheck(PlayerIE.red);
			
			isRedCheckMate = isKingCheckMate(this.turn, this.kingRCoor);
			
			
			if(isRedCheckMate) {
				winner = PlayerIE.black;
				linkedGui.showWinner(winner);
				this.linkedGui.bwin();
				linkedGui.startBoard();
			}	
				
			
				
			
		}
		else  if (turn == PlayerIE.black){
			isBlackInCheck = chessInCheck(this.board, this.kingBCoor,this.aliveRed);
			if (isRedInCheck)
				linkedGui.showInCheck(PlayerIE.red);
			
			
			isBlackCheckMate = isKingCheckMate(this.turn, this.kingBCoor);
			
			if(isBlackCheckMate){ 
				winner = PlayerIE.red;
				linkedGui.showWinner(winner);
				this.linkedGui.rwin();
				linkedGui.startBoard();
			}
			
			
				
		}
		
		this.linkedGui.displayScore();
		
	}
	
	
	
	
	//who's still alive after a move?
	private void updateAliveList(Action move, boolean capture) {
		
		
		if(turn == PlayerIE.red) {
			aliveRed.remove(board.getAGrid(move.getDepartCoor()));
			aliveRed.add(board.getAGrid(move.getDestCoor()));
			
			
			if(capture)
				aliveBlack.remove(board.getAGrid(move.getDestCoor()));
		} else {
			aliveBlack.remove(board.getAGrid(move.getDepartCoor()));
			aliveBlack.add(board.getAGrid(move.getDestCoor()));
			
			if(capture)
				aliveRed.remove(board.getAGrid(move.getDestCoor()));
		}
	}

	
	
	private void updateAliveAfterUndo(Action pMove) {
		Coordinate backWardTo = new Coordinate(pMove.getDepartX(),pMove.getDepartY()); 
		Coordinate backWardFrom = new Coordinate(pMove.getDestX(),pMove.getDestY()); 
		if( turn == PlayerIE.black) {
			aliveBlack.remove(board.getAGrid(backWardFrom));
			aliveBlack.add(board.getAGrid(backWardTo));
			if(pMove.getCapType() != ChessType.nothing) {
				aliveRed.add(board.getAGrid(backWardFrom));
			}
		}
		else {
			aliveRed.remove(board.getAGrid(backWardFrom));
			aliveRed.add(board.getAGrid(backWardTo));
			if(pMove.getCapType() != ChessType.nothing) {
				aliveBlack.add(board.getAGrid(backWardFrom));
			}
		}	
	}
	
	/**
	 * restart a new board without changing the score
	 * 
	 */
	public void restart() {
		linkedGui.startBoard();
	}
	
	/**
	 *  forfeit a game and set the winner info
	 *  Then restart the board and keep the score
	 * 
	 */
	public void forfeit() {
		if(linkedGui != null) {
			if(turn == PlayerIE.red){
				winner = PlayerIE.black;
				this.linkedGui.bwin();}
			else{
				winner = PlayerIE.red;
				this.linkedGui.rwin();}
		
		
			linkedGui.showWinner(winner);
			linkedGui.startBoard();
		}
	}
	
	public void newPlayer(){
		
		linkedGui.startBoard();
		linkedGui.zeroScore();
	}
	
	/**
	 * Both players would move"backward" for one step
	 * 
	 * 
	 */
	public void undo() {
		if(log.size()>=2) {
			if(turn == PlayerIE.black){
				
				//redP stands for previous red move
				turn = PlayerIE.red;
				Action redP = log.pop();
				board.undoOne(redP);
				updateAliveAfterUndo(redP);
				linkedGui.showUndoOne(redP);
				
				if(kingRCoor.isSame(redP.getDestCoor())) {
					kingRCoor = redP.getDepartCoor();
				}
				
				turn = PlayerIE.black;
				Action blackP = log.pop();
				board.undoOne(blackP);
				updateAliveAfterUndo(blackP);
				linkedGui.showUndoOne(blackP);
				
				if(kingBCoor.isSame(blackP.getDestCoor())) {
					kingBCoor = blackP.getDepartCoor();
				}
				
			}else{
				turn = PlayerIE.black;
				Action blackP = log.pop();
				board.undoOne(blackP);
				updateAliveAfterUndo(blackP);
				linkedGui.showUndoOne(blackP);
				if(kingBCoor.isSame(blackP.getDestCoor())) {
					kingBCoor = blackP.getDepartCoor();
				}
				
				
				turn = PlayerIE.red;
				Action redP = log.pop();
				board.undoOne(redP);
				updateAliveAfterUndo(redP);
				linkedGui.showUndoOne(redP);
				
				if(kingRCoor.isSame(redP.getDestCoor())) {
					kingRCoor = redP.getDepartCoor();
				}
				
			}
			
			
			
		}
	}
	
	//sequence...
	/*

	 * Execute move.
	 */
	public boolean Move() {
		Action move = new Action(fromCoor,toCoor);
		if(isMoveValid(move)) {
			//if valid, execute move and swap turn
			boolean capture =board.moveAChess(move);
			updateAliveList(move, capture);
			log.push(move);
			
			if(!isRedInCheck && !isBlackInCheck)
				linkedGui.showInCheck(PlayerIE.white);
			
			if(turn == PlayerIE.red)
				turn = PlayerIE.black;
			else
				turn = PlayerIE.red;
			
			linkedGui.displayValidMove(move, capture);
			//updateStatus();//turn difference
			
			if(kingRCoor.isSame(fromCoor)) {
				kingRCoor = toCoor;
			} else if(kingBCoor.isSame(fromCoor)) {
				kingBCoor = toCoor;
			}
			
			
			updateStatus();
			
			return true;
		}
		linkedGui.showInvalidInfo(move);
		return false;
	}
	
	
	
	/*
	 * Checks if is king in check.
	 *
	 * @param board - current board
	 * @param kingCoor - the coordinate of king
	 * @param aliveList - the alive pieces of enemy
	 * @return true - if is king in check 
	 * @return false - otherwise
	 */
	public boolean chessInCheck(Chessboard board, Coordinate kingCoor, ArrayList<Grid> aliveList) {
		for(Grid i : aliveList)
		{
		//	System.out.println(i.getEleOnGrid().getType());
			WOG potentialAttacker = i.getEleOnGrid();
			
			if(potentialAttacker.beatKing(board, kingCoor, i.getCoor())){
				
					return true;
			}
		}
		return false;
	}
	
	//whether a piece of chess can "attack" to the empty area between attacker and the king
	//can be any piece in the king's team except for king himself
	public boolean chessInCheckP(Chessboard board, Coordinate kingCoor, ArrayList<Grid> aliveList) {
		for(Grid i : aliveList)
		{
		
			WOG potentialAttacker = i.getEleOnGrid();
			
			
			
			if(potentialAttacker.beatKing(board, kingCoor, i.getCoor())&&potentialAttacker.getType()!=ChessType.king){
				
					return true;
			}
		}
		return false;
	}

	
	
	public Grid getAttackerGrid(Coordinate kingCoor, ArrayList<Grid> aliveList) {
		for(Grid i : aliveList)
		{
			
			WOG potentialAttacker = i.getEleOnGrid();
			if(potentialAttacker.beatKing(board, kingCoor, i.getCoor()) == true)
					return i;
				
		}
		return null;
	}
	
	
	/*
	 * Checks if is king check mate.
	 * @param turn - whose turn is it
	 * @param kingCoor the coordinate of king
	 * @return true - if the king would be killed
	 * @return false - otherwise
	 */
	public boolean isKingCheckMate(PlayerIE turn, Coordinate kingCoor) {
		
		if (turn==PlayerIE.red)
			return  kingFailEscape(kingCoor,aliveBlack) &&kingHasNoGuard(kingCoor,aliveBlack,aliveRed);
		
		
		if (turn==PlayerIE.black)	
			return kingFailEscape(kingCoor,aliveRed) && kingHasNoGuard(kingCoor,aliveRed,aliveBlack);
			
		return false;
	
	}

	
	/*
	 * king is in check at origin position
	 * even it can move, it is still in check?
	 */
	public boolean kingFailEscape(Coordinate kingCoor,ArrayList<Grid>aliveList){
		int kingX=kingCoor.getX();
		int kingY=kingCoor.getY();
		int[] Xposi = {kingX - 1, kingX,kingX + 1};
		int[] Yposi = {kingY-1,kingY,kingY + 1};
		
		
		for(int i = 0; i < Xposi.length; i++){
			for(int j = 0; j < Yposi.length; j++){
				if((Xposi[i] >= 0 && Xposi[i] < 8) && (Yposi[j] >= 0 && Yposi[j] < 8)){
						Coordinate possibleEscape = new Coordinate(Xposi[i],Yposi[j]);
						WOG eleDest = board.getAGrid(possibleEscape).getEleOnGrid();
						boolean blocked = (eleDest != null && eleDest.getPlayer() == turn);
						
						//here
						if(!blocked)
						{
							Action move = new Action(kingCoor,possibleEscape);
							Chessboard virtual = new Chessboard(board);
							
							
							virtual.moveAChess(move);
							

							
							//bug!!! it's in grid /coorG!!!!!!!!!
							//take me three hours!!!!!
							
							
							if(!chessInCheck(virtual,possibleEscape,aliveList))
								return false;
						}
				 }
		
			}
			
			
		}
		return true;
		
	}
	
	/*
	 * when the attacker is rook, bishop,queen, the route can be blocked 
	 * also, some piece of chess may capture the attacker
	 * When king is not in check, return false
	 * 
	 */
	public boolean kingHasNoGuard(Coordinate kingCoor,ArrayList<Grid>aliveA,ArrayList<Grid>aliveKingSide){
		
		if(!chessInCheck(board, kingCoor, aliveA)) 
			return false;
		Grid attackerGrid=getAttackerGrid(kingCoor, aliveA);
		
		//attacker can be captured
		
		
		if (chessInCheck(board,attackerGrid.getCoor(),aliveKingSide)){
			
			 return false;
			
		}
			//System.out.println(chessInCheck(board,attackerGrid.getCoor(),aliveKingSide));
			
		
		//attacker can be blocked?
		
		
		//if attacker is not rook, bishop or queen, it cannot be blocked
		WOG attacker = attackerGrid.getEleOnGrid();
		if ((attacker.getType() != ChessType.queen) && (attacker.getType() != ChessType.rook) && (attacker.getType() != ChessType.bishop))
			return true;
		
	
		//when it can be blocked:
		int kingX = kingCoor.getX();
		int kingY = kingCoor.getY();
		
		int attackerX = attackerGrid.getCoor().getX();
		int attackerY = attackerGrid.getCoor().getY();
		
		int midX = Math.abs(kingX-attackerX)-1;
		int midY = Math.abs(kingY-attackerY)-1;
		
		int maxMid = Math.max(midX, midY);
		
		for (int i = 0; i < maxMid; i++){
			//start from attacker's side
			
			
			int tempX=(kingX-attackerX)<0? -1:1;
			int tempY=(kingY-attackerY)<0? -1:1;
			
			tempX=(kingX-attackerX)==0? 0:tempX;
		    tempY=(kingY-attackerY)==0? 0:tempY;
			
			int blockX = attackerX + tempX *(i+1);
			int blockY = attackerY + tempY*(i+1);
			
			
			if (chessInCheckP(board,new Coordinate(blockX,blockY),aliveKingSide))
				return false;
			
		}
		
		return true;
		
		
	}
	
	
}
