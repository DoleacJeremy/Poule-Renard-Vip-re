package fenetre.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphPannel extends JPanel{
	private ArrayList<GraphData> datasList = new ArrayList<GraphData>(); 
	private int xSize;
	private int ySize;
	
	public GraphPannel(int xSize , int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		
		this.setPreferredSize(new Dimension(xSize,ySize));
		this.setSize(new Dimension(xSize,ySize));
		this.setBackground(Color.WHITE);
	}
	
	
	
	
	

	public void addDatasGraph(GraphData donnee) {
		this.datasList.add(donnee);
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		//get tha max value of each of the graphs to rescale 
		int max = 0;
		for(GraphData donee : this.datasList) {
			int maxData = donee.getMaxValue();
			if(maxData > max)
				max = maxData;
		}

		//draw each graph
		float rescaleGraphY = (ySize/(float)max);
		for(GraphData donee : this.datasList)
			donee.drawGraph(g, this.xSize, this.ySize , rescaleGraphY);
	}
}