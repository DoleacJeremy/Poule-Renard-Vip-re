package com.JD.fenetre.param;

import java.util.Hashtable;

public enum EnumParam {
	size,
	nbCell,
	delay;

	private static Hashtable<EnumParam,EnumSuportedParamType> paramType = new Hashtable<EnumParam,EnumSuportedParamType>();
	private static Hashtable<EnumParam,String> paramInfo = new Hashtable<EnumParam,String>();
	private static Hashtable<EnumParam,ParamVariable<?>> paramDefaultValues = new Hashtable<EnumParam,ParamVariable<?>>();

	public static EnumSuportedParamType getType(EnumParam setting) {
		return(paramType.get(setting));
	}
	public static String getInfo(EnumParam setting) {
		return(paramInfo.get(setting));
	}
	public static ParamVariable<?> getDefaultValues(EnumParam setting) {
		return(paramDefaultValues.get(setting));
	}
	
	
	public static void setUpTypes() {
		EnumParam[] name = {
				EnumParam.size,
				EnumParam.nbCell,
				EnumParam.delay
		};
	
		EnumSuportedParamType[] type = {
				EnumSuportedParamType.number,
				EnumSuportedParamType.number,
				EnumSuportedParamType.number
		};
		String[] info = {
				"Size of the simulation window",
				"Number of cells per camps",
				"Pause delay between turn"
		};
		ParamVariable<?>[] defaultValue = {
				new ParamVariable<Integer>(800),
				new ParamVariable<Integer>(500),
				new ParamVariable<Integer>(10),
		};
		
		for(int i = 0 ; i < name.length ; i++) {
			paramType.put(name[i], type[i]);
			paramInfo.put(name[i], info[i]);
			paramDefaultValues.put(name[i], defaultValue[i]);
		}
	}
}