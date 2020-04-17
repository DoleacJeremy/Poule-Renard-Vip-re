package fenetre.simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import core.CellGroup;
import core.SingletonStat;

@SuppressWarnings("serial")
public class SimulationPannel extends JPanel{
	private CellGroup cellGroup;
	private BufferedImage background;
	
	private int size;
	private int turn;

	private SingletonStat singletonStat = SingletonStat.getSingletonStat();
	
	public SimulationPannel(CellGroup cellgroup , int size) {
		this.size = size;
		this.cellGroup = cellgroup;
		this.turn = 0;
		

		this.setPreferredSize(new Dimension(size,size));
		this.setSize(new Dimension(size,size));
		this.setBackground(Color.BLUE);
		
		
		try {
		    this.background = ImageIO.read(new File("ressources/background_simulation.png"));
		} catch (IOException e) {
			System.out.println("[ParamPanel:35] impossible to get the image due to : ");
			e.printStackTrace();
			
		}
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.background, 0, 0, this.size, this.size, null);
		this.cellGroup.drawCellGroup(g);
	}
	
	
	public boolean conputeTurn() {
		Date debut = new Date();
		boolean retour = this.cellGroup.computeOneTurn();
		Date fin = new Date();
		
		this.singletonStat.setMilis((int)(fin.getTime()-debut.getTime()));
		this.singletonStat.setTours(this.turn);
		
		if(this.singletonStat.isLastTurn(this.turn))
			System.out.println("[UltimatePanel:83]   "+this.singletonStat.getLog());
		
		
		this.repaint();
		this.turn++;
		return(retour);
	}
}