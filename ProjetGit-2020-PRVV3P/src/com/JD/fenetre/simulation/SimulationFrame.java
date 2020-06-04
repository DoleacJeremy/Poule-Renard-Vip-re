package com.JD.fenetre.simulation;

import java.awt.Dimension;
import javax.swing.JFrame;

import com.JD.core.CellGroup;
import com.JD.core.EnumCampCell;
import com.JD.core.SingletonStat;
import com.JD.fenetre.graph.GraphData;
import com.JD.fenetre.graph.GraphFrame;
import com.JD.fenetre.param.EnumParam;
import com.JD.fenetre.param.ParamSettingsUser;

@SuppressWarnings("serial")
public class SimulationFrame extends JFrame implements Runnable{
	private SimulationPannel panel;
	private int delayMS;
	private int nbBaseCell;
	
	public SimulationFrame() {
		super();
		ParamSettingsUser paramSettingsUser = ParamSettingsUser.getParamSettingsUser();
		
		this.delayMS = (int) paramSettingsUser.getParam(EnumParam.delay).getValue();
		this.nbBaseCell = (int) paramSettingsUser.getParam(EnumParam.nbCell).getValue();
		int size = (int) paramSettingsUser.getParam(EnumParam.size).getValue();
		CellGroup cellGroup = new CellGroup(size,this.nbBaseCell);
		
		
		this.panel = new SimulationPannel(cellGroup,size);
		this.setContentPane(this.panel);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("PRV - simulation - "+this.nbBaseCell);
		this.setSize(new Dimension(size+6,size+29));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	//make the simulation run until there is one camp left
	public void runSimulation() throws InterruptedException {
		boolean fin = false;
		
		while(!fin) {
			fin = this.panel.conputeTurn();
			this.panel.repaint();

			Thread.sleep(this.delayMS);
		}
		
		this.setTitle("PRV - simulation - "+this.nbBaseCell+" - fini");
		this.runGraph();
	}

	//create the graph windows with the datas gathered from the simulation
	public void runGraph() {
		SingletonStat stats = SingletonStat.getSingletonStat();
		
		GraphData graphHen = new GraphData(EnumCampCell.getColor(EnumCampCell.hen));
		GraphData graphViper = new GraphData(EnumCampCell.getColor(EnumCampCell.viper));
		GraphData graphFox = new GraphData(EnumCampCell.getColor(EnumCampCell.fox));

		graphHen.addValues(stats.getHens());
		graphViper.addValues(stats.getVipers());
		graphFox.addValues(stats.getFoxs());
		
		GraphFrame graphwindow = new GraphFrame(700, 300, "Cells camps size evolution");
		graphwindow.addDataGraph(graphHen);
		graphwindow.addDataGraph(graphViper);
		graphwindow.addDataGraph(graphFox);
		graphwindow.updatePanel();
	}

	@Override
	public void run() {
		try {
			this.runSimulation();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}