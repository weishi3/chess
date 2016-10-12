package controller;



import java.awt.event.*;

import playChess.*;
import helpers.*;
import javax.swing.JOptionPane;

public class Restart implements ActionListener {

	private Game theGame;
	
	public Restart(Game g) {
		this.theGame = g;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int r = JOptionPane.showConfirmDialog(null,"Red player: Are you sure you want to restart the game without changing the current score?");
        
        if (r==JOptionPane.YES_OPTION)
        {
            int b = JOptionPane.showConfirmDialog(null, "User 2: Are you sure you want to restart the game without changing the current score?");
            if(b==JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(null,"Now resetting" +e.getActionCommand(),"",JOptionPane.INFORMATION_MESSAGE);
                theGame.restart();
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Give up resetting"+e.getActionCommand(),"",JOptionPane.INFORMATION_MESSAGE);
	}
}