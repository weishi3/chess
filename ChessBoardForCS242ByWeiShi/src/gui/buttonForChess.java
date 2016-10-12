package gui;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.Move;
import playChess.*;

public class buttonForChess {
	//coordinate may helpful
	int X, Y;

	private JButton button = new JButton();
	//css is also unicode..
    private static Font unicode = new Font("Arial Unicode MS", Font.ITALIC, 50);
    private static LineBorder border = new LineBorder(Color.GRAY, 1);
    
    public buttonForChess(String type, Color gridColor, int X, int Y, Game gamecontrol) {
    	button.setText(type);
    	this.X = X;
        this.Y = Y;
        
        button.setFont(unicode);
        button.setBorder(border);
        
        button.setHorizontalAlignment(JButton.CENTER);
        
        button.setBackground(gridColor);
        button.setOpaque(true);
        
        button.addActionListener(new Move(this.X, this.Y, gamecontrol));
    }
    //useful for type
    public void setButtonText(String s) {
    	button.setText(s);
    }
    
    public void setButtonBackground(Color color) {
    	button.setBackground(color);
    }
    
    public Color getButtonBackground() {
    	return button.getBackground();
    }
    
    public String getButtonText() {
    	return button.getText();
    }
    
	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
}