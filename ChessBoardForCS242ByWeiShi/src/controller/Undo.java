package controller;

import java.awt.event.*;

import playChess.*;
import helpers.*;
import javax.swing.JOptionPane;

public class Undo implements ActionListener {
	
	
	private Game theGame;
	
	/**
	 * Instantiates a new undo action.
	 *
	 * @param g the g
	 */
	public Undo(Game g) {
		this.theGame = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int b = JOptionPane.showConfirmDialog(null, "Sure you want to allow your opponent to undo?");
        if(b==JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(null,"Now undo"+e.getActionCommand(),"",JOptionPane.INFORMATION_MESSAGE);
            theGame.undo();
        }
	}

}