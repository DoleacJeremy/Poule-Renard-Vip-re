package com.JD.fenetre.param;

import java.util.Hashtable;

public class ParamSettingsUser {
	private Hashtable<EnumParam,ParamVariable<?>> paramSettings = new Hashtable<EnumParam,ParamVariable<?>>();
	private static ParamSettingsUser paramSettingsUser;
	
	private ParamSettingsUser() {
		EnumParam.setUpTypes();
	}
	
	public static ParamSettingsUser getParamSettingsUser() {
		if(null == paramSettingsUser)
			paramSettingsUser = new ParamSettingsUser();
		return(paramSettingsUser);
	}
	
	public ParamVariable<?> getParam(EnumParam settingName){
		return(this.paramSettings.get(settingName));
	}
	public void setParam(EnumParam settingName , ParamVariable<?> paramValue) {
		this.paramSettings.put(settingName, paramValue);
	}
}