package com.JD.fenetre.param;

import java.awt.Dimension;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ParamFrame extends JFrame {
	private ParamPanel panel;
	
	public ParamFrame() {
		this.panel = new ParamPanel();
		this.setContentPane(this.panel);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Settings");
		this.setSize(new Dimension(400,400));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}