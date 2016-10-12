package controller;

import java.awt.event.*;

import playChess.*;
import helpers.*;
import javax.swing.JOptionPane;

public class Forfeit implements ActionListener {

	private Game theGame;
	
	public Forfeit(Game g) {
		this.theGame = g;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int b = JOptionPane.showConfirmDialog(null, "Confirm: Sure to forfeit this game? (your opponent would win)");
        if(b==JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(null,"Now forfeiting","",JOptionPane.INFORMATION_MESSAGE);
            theGame.forfeit();
            return;
        }
        
        JOptionPane.showMessageDialog(null,"Not to forfeit","",JOptionPane.INFORMATION_MESSAGE);
        
	}
	
}