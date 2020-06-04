package com.JD.fenetre.param;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import com.JD.fenetre.simulation.SimulationFrame;

@SuppressWarnings("serial")
public class ParamPanel extends JPanel implements ActionListener{
	private BufferedImage background;
	private HashMap<EnumParam,JTextArea> valueZoneList = new HashMap<EnumParam,JTextArea>();
	private JButton button;
	
	

	public ParamPanel() {
		EnumParam.setUpTypes();
		
		try {
		    this.background = ImageIO.read(new File("ressources/background_param.png"));
		} catch (IOException e) {
			System.out.println("[ParamPanel:35] impossible to get the image due to : ");
			e.printStackTrace();
		}
		
		// for each parameter in the enum, it creates a new line that is added to the panel
		for(EnumParam param : EnumParam.values())
			this.add(this.getPanelParam(param));
		
		//void
		int tailleEspaceY = 320-this.valueZoneList.size()*30-(this.valueZoneList.size()+1)*5;
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(390,tailleEspaceY));
		this.add(panel);
		
		//buton		
		this.button = new JButton();
		this.button .setPreferredSize(new Dimension(100,30));
		this.button.setText("next");
		this.button.addActionListener(this);
		this.add(this.button);
	}
	
	
	// generate a JPanel for one parameter
	public JPanel getPanelParam(EnumParam setting) {
		JPanel panelParam = new JPanel();

		// information text (left)
		JTextPane infoTaille = new JTextPane();
		infoTaille.setText(EnumParam.getInfo(setting));
		infoTaille.setPreferredSize(new Dimension(250,20));
		infoTaille.setEditable(false);
		
		// input zone (center)
		JTextArea texteTaille = new JTextArea();
		texteTaille.setPreferredSize(new Dimension(50,20));
		texteTaille.setText(EnumParam.getDefaultValues(setting).getValue().toString());

		// value type (right)
		JTextArea infoType = new JTextArea();
		infoType.setText(EnumParam.getType(setting).toString());
		infoType.setPreferredSize(new Dimension(50,20));
		infoType.setEditable(false);
		
		// adding to the panel
		panelParam.setPreferredSize(new Dimension(370,30));
		panelParam.add(infoTaille);
		panelParam.add(texteTaille);
		panelParam.add(infoType);
		
		// adding the input zone to the list to get it latter when the user click on next
		this.valueZoneList.put(setting, texteTaille);
		
		return(panelParam);
	}
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(this.background, 0, 0, 400, 400, null);
	}

	// method used when the user click on the button
	@Override
	public void actionPerformed(ActionEvent arg0) {

		boolean inputsValidation = true;
		boolean dataError = false;

		// retrival of the input data from each parameters
		for(EnumParam param : EnumParam.values()) {
			ParamSettingsUser paramSettingsUser = ParamSettingsUser.getParamSettingsUser();
			this.valueZoneList.get(param).setBackground(Color.GREEN);
			String textValue = this.valueZoneList.get(param).getText();
			EnumSuportedParamType paramType = EnumParam.getType(param);
			
			ParamVariable<?> paramValue = null; 
			switch(paramType) {
				case number :
					int paramUserValue = this.valeurIntParam(textValue, param);
					if(paramUserValue > 0) {
						//le cas ou tout ce passe bien
						paramValue = new ParamVariable<Integer>(paramUserValue);
						System.out.println("[ParamPanel:125] InputData are invalid");
					}else {
						dataError = true;
					}
					break;
				case text :
					if(textValue.length() < 1) {
						dataError = true; 
						this.valueZoneList.get(param).setBackground(Color.RED);
						System.out.println("[ParamPanel:120] Inexistant InputData");
					}
					paramValue = new ParamVariable<String>(textValue);
					break;
			}
			if(!dataError) {
				paramSettingsUser.setParam(param, paramValue);
			}else {
				inputsValidation = false;
			}
			
		}
		
		// launching simulation if there is no error in any of the parameters
		if(inputsValidation) {
			SimulationFrame fenetre = new SimulationFrame();
			new Thread(fenetre).start();
		}
	}
	
	// extract and return the integer value from the text
	private int valeurIntParam(String textValue , EnumParam param) {
		int paramUserValue = -1;
		
		try {
			paramUserValue = Integer.parseInt(textValue);
		}catch(Exception e) {
			// the case where the value is in part or a whole text
			this.valueZoneList.get(param).setBackground(Color.RED);
			System.out.println("[ParamPanel:120] Input datas are not numbers.");
			paramUserValue = -1;
		}
		if(paramUserValue < 1) {
			// the case where the value is less than 1
			this.valueZoneList.get(param).setBackground(Color.RED);
			System.out.println("[ParamPanel:125] Input datas can not be inferior to one. you entered : "+paramUserValue);
			paramUserValue = -1;
		}
		return(paramUserValue);
	}
	
	
}