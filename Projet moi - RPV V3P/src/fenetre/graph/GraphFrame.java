package fenetre.graph;

import java.awt.Dimension;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GraphFrame extends JFrame implements Runnable{
	private GraphPannel panel;
	
	public GraphFrame(int xSize , int ySize, String name) {
		super();
		
		this.panel = new GraphPannel(xSize,ySize);
		this.setContentPane(this.panel);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("graph - "+name);
		this.setSize(new Dimension(xSize,ySize));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	public void updatePanel() {
		this.panel.repaint();
	}


	public void addDataGraph(GraphData donnee) {
		this.panel.addDatasGraph(donnee);
	}
	
	@Override
	public void run() {
			this.updatePanel();
	}
}