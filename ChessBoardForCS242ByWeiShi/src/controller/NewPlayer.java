package controller;

import java.awt.event.*;

import playChess.*;
import helpers.*;
import javax.swing.JOptionPane;

public class NewPlayer implements ActionListener {

	private Game theGame;
	
	public NewPlayer(Game g) {
		this.theGame = g;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int r = JOptionPane.showConfirmDialog(null,"Red player: Are you sure you want to restart the game without keeping the score?");
        
        if (r==JOptionPane.YES_OPTION)
        {
            int b = JOptionPane.showConfirmDialog(null, "User 2: Are you sure you want to restart the game without keeping score?");
            if(b==JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(null,"Now setup a new player","",JOptionPane.INFORMATION_MESSAGE);
                theGame.newPlayer();
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Give up resetting","",JOptionPane.INFORMATION_MESSAGE);
	}
}