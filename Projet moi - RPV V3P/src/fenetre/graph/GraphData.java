package fenetre.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import mathUtil.Math_functions;

public class GraphData {
	private List<Integer> valuesList = new ArrayList<Integer>(); 
	private Color color;
	
	public GraphData(Color color) {
		this.color = color;
	}

	public void addValeur(int value) {
		this.valuesList.add(value);
	}
	public void addValues(List<Integer> values) {
		this.valuesList = values;
	}
	
	//draw the graph on a determined zone
	public void drawGraph(Graphics g , int xSize , int ySize , float rescaleGraphY) {
		float rescaleGraphX = ((this.valuesList.size()-1)/((float)(xSize)));
		Color gcolor = g.getColor();
		g.setColor(this.color);
		
		// drawing of the datas with rescale to make sure the Y ans X axis are used fully
		for(int indeX = 0 ; indeX < xSize ; indeX++) {
			int i = Math_functions.round(indeX*rescaleGraphX);
			if(i >= this.valuesList.size()-1)
				i = this.valuesList.size()-2;
			
			int y1 = ySize-Math_functions.round(this.valuesList.get(i)*rescaleGraphY);
			int y2 = ySize-Math_functions.round(this.valuesList.get(i+1)*rescaleGraphY);
			g.drawLine(indeX, y1, indeX+1, y2);
		}
		g.setColor(gcolor);
	}
	
	//return tje max value of all the values
	public int getMaxValue() {
		int result = 0;
		for(int value : this.valuesList)
			if(value > result)
				result = value;
		return(result);
	}
}