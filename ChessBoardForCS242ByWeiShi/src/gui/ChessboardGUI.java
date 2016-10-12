package gui;
import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.Forfeit;
import controller.NewPlayer;
import controller.Restart;
import controller.Undo;

import java.awt.*;

import playChess.*;
import helpers.*;
import chessboardDesign.*;
import chessboardDesign.Action;


public class ChessboardGUI {
	//a great website for symbols: http://www.w3cfuns.com/blog-5458304-5402820.html
	//css code for red   

    private int blackWin=0;
    private int redWin=0;
    private JFrame boardFrame;
    private buttonForChess[][] board;
    private int boardWidth, boardHeight;
    private Game theGame;
    private JTextField turn, inCheck,score,warning;
    
    /**
     * 
     * 
     * @param coor - Coordinate of the button whose background is going to change
     */
    public void changeButtonColor(Coordinate coor){
    	int buttonX = coor.getX();
    	int buttonY = coor.getY();
    	if ((buttonX + buttonY) % 2 == 0)
    		board[buttonX][buttonY].setButtonBackground(Color.DARK_GRAY);
    	else 
    		board[buttonX][buttonY].setButtonBackground(Color.BLUE);
    }
    public void zeroScore(){
    	blackWin=0;
    	redWin=0;
    	
    }
    
    /**
     * recover background color of the button at coor position
     * 
     * @param coor
     */
    public void recoverButtonColor(Coordinate coor){
    	int buttonX = coor.getX();
    	int buttonY = coor.getY();
    	if ((buttonX + buttonY) % 2 == 0)
    		board[buttonX][buttonY].setButtonBackground(Color.LIGHT_GRAY);
    	else 
    		board[buttonX][buttonY].setButtonBackground(Color.CYAN);
    		
    }
   
    
    /**
     * constructor
     */
    public ChessboardGUI() {
		startBoard();
    }
    
    /**
     * black wins a game
     * 
     */
    public void bwin(){
    	blackWin++;
    	
    }
    
    /**
     * red wins a game
     * 
     */
    public void rwin(){
    	redWin++;
    	
    }
    
    private void setupFrame() {
    	boardFrame = new JFrame();
    	//why can't add 1 on the right?
    	GridLayout grid = new GridLayout(boardWidth+1, boardHeight);
    	boardFrame.setSize(1000, 1000);
    	boardFrame.setLayout(grid);
    	boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     *set up the restart button
     */
    private void setupRestart() {
   	 JButton restart = extraButton();
        restart.setText("Restart");
        restart.addActionListener(new Restart(theGame));
        this.boardFrame.add(restart);
   }
    
    
    private void setupNewPlayer() {
      	 JButton newplayer = extraButton();
           newplayer.setText("NewPlayer");
           newplayer.addActionListener(new NewPlayer(theGame));
           this.boardFrame.add(newplayer);
    }
   
    /**
     * set up the forfeit button
     */
   private void setupForfeit() {
   	  
       JButton forfeit = extraButton();
       forfeit.setText("Forfeit");
       forfeit.addActionListener(new Forfeit(theGame));
       this.boardFrame.add(forfeit);   
   }
   
   /**
    * set up the undo button
    */
   private void setupUndo() {
 	  
       JButton undo = extraButton();
       undo.setText("Undo");
       undo.addActionListener(new Undo(theGame));
       this.boardFrame.add(undo);
   }
   
   
   /**
    * setting textbars
    * 
    */
   private void setupTextbar() {
   	   turn = new JTextField(20);
       this.boardFrame.add(turn);
       inCheck = new JTextField(20);
       this.boardFrame.add(inCheck);
       score = new JTextField(20);
       this.boardFrame.add(score);
       warning = new JTextField(20);
       this.boardFrame.add(warning);
      
   }
    
   /**
    * relate a piece of chess to its unicode
    * 
    * @param player
    * @param type
    * @return
    */
    private String cssToString(PlayerIE player, ChessType type) {
    	if(player == PlayerIE.black) {
    		switch(type) {
	    		case pawn: return Character.toString('\u265F');
	    		case bishop: return Character.toString('\u265D');
	    		case knight: return Character.toString('\u265E');
	    		case rook: return Character.toString('\u265C');
	    		case queen: return Character.toString('\u265B');
	    		case king: return Character.toString('\u265A');
    		}
    	} 
    	switch(type) {
	    	case pawn: return Character.toString('\u2659');
	    	case bishop: return Character.toString('\u2657');
	    	case knight: return Character.toString('\u2658');
	    	case rook: return Character.toString('\u2656');
	    	case queen: return Character.toString('\u2655');
	    	case king: return Character.toString('\u2654');
    	}
    	//unreachable
    	return "";
    }
    
    
    /**
     * setup a new kind of JButton
     */
    private JButton extraButton() {
    	JButton b = new JButton();
        b.setFont(new Font("Arial Unicode MS", Font.ITALIC, 20));
        b.setHorizontalAlignment(JButton.CENTER);
        b.setBorder(new LineBorder(Color.BLACK, 1));
        b.setOpaque(true);
        return b;
    }
    
    /**
     * set up the position of pieces of chess
     */
    private void setupPieces() {
    	
    	board = new buttonForChess[boardWidth][boardHeight];
        for(int X = 0; X < boardWidth; X++)
        	for(int Y = 0; Y < boardHeight; Y++) {
        		//set color
        		if ((X + Y) % 2 == 0) {
                    board[X][Y] = new buttonForChess("", Color.lightGray, X, Y, theGame);
                } 
                else {
                    board[X][Y] = new buttonForChess("", Color.CYAN, X, Y, theGame);
                }
        		//.that's important..don't miss again..
                this.boardFrame.add(board[X][Y].getButton());
                
              //  if (Y < 2 ||Y >= 6){
               // 	board[X][Y].getButton().setIcon(new ImageIcon(getClass().getResource("pictures/blackpawn.png")));
                	
                	
             //   }
                
                if(Y < 2 || Y >= 6) {
               	board[X][Y].setButtonText(cssToString(theGame.getBoard().getAGrid(new Coordinate(X,Y)).getEleOnGrid().getPlayer(), theGame.getBoard().getAGrid(new Coordinate(X,Y)).getEleOnGrid().getType()));
                }
            }
    }
    

    
   
    
    /**
     * start a new boardGui
     * 
     */
    public void startBoard() {
    	if(this.boardFrame != null)
    		this.boardFrame.setVisible(false);
    	
    	boardWidth = 8;
    	boardHeight = 8;
    	theGame = new Game(this);
    	setupFrame();
    	setupPieces();
    	
    	setupNewPlayer();
    	setupTextbar();
    	setupRestart();
    	setupForfeit();
    	setupUndo();
    	
    	// !!!!!!!WTF DOES THIS FUNCTION make any sense?
    	this.boardFrame.setVisible(true);	
    }
      
   //start from building a board
   
    
    
    
    /**
     * display the effect of a single undo
     * 
     * @param m - a previous step
     */
    public void showUndoOne(Action m) {
    	String oriFromText = board[ m.getDestX() ][m.getDestY()].getButtonText();
    	String oriToText = board[ m.getDepartX() ][m.getDepartY()].getButtonText();
    	board[ m.getDepartX() ][m.getDepartY()].setButtonText(oriFromText);
    	if(m.getCapType() == ChessType.nothing)
    		board[m.getDestX() ][m.getDestY()].setButtonText(oriToText);
    	else
    		board[m.getDepartX() ][m.getDepartY()].setButtonText(cssToString(m.getCapColor(), m.getCapType()));
    }
    
    /**
     * Display a valid move.
     *
     * @param m the move
     * @param wasCapture boolean true if it was a capture
     */
    public void displayValidMove(Action m, boolean wasCapture) {
    	String FromText = board[m.getDepartX() ][m.getDepartY()].getButtonText();
    	String ToText = board[ m.getDestX() ][m.getDestY()].getButtonText();
    	board[ m.getDestX()][m.getDestY()].setButtonText(FromText);
    	if(wasCapture)
    		board[ m.getDepartX() ][m.getDepartY()].setButtonText("");
    	else
    		board[m.getDepartX() ][m.getDepartY()].setButtonText(ToText);
    	
    	turn.setText("Turn:" + theGame.getGameTurn());
    }
    
    /**
     * Display invalid information
     *
     * @param m - the proposed invalid move action
     */
    public void showInvalidInfo(Action m) {
    	warning.setText("Invalid!");      
    }
    
    /**
     * Display win.
     *
     * @param player - the player who is the winner
     */
    public void showWinner(PlayerIE player) {
    	if (player == PlayerIE.red)
    		JOptionPane.showMessageDialog(null,  " white wins!");  
    	if (player == PlayerIE.black)
    		JOptionPane.showMessageDialog(null,  " black wins!");  
    	
    	
    	this.boardFrame.setVisible(false);
    }
    
    /**
     * Display check.
     *
     * @param player - player whose king is in check
     */
    public void showInCheck(PlayerIE player) {
    	if(player == PlayerIE.white)
    		inCheck.setText("");  
    	else if (player == PlayerIE.red)
    		inCheck.setText("white is in check!!!");
    	else 
    		inCheck.setText("black is in check!!!");
    }
    
    /**
     * Display score
     *
     */
    public void displayScore() {
    	score.setText("Black:" + blackWin + " White:" + redWin);
    }
    

    
  
}