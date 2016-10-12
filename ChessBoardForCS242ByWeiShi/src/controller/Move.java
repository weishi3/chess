package controller;

import java.awt.event.*;

import playChess.*;
import helpers.*;
import javax.swing.JOptionPane;

public class Move implements ActionListener {
	
	
	private Game theGame;
	
	private int X,Y;
	
	
	
	
	public Move(int x, int y, Game g) {
		this.X = x;
		this.Y = y;
		this.theGame = g;
	}

	public void actionPerformed(ActionEvent e)
    {
        theGame.click(new Coordinate(X,Y));
    }

}