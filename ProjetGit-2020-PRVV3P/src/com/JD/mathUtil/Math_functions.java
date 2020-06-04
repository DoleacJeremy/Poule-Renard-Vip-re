package com.JD.mathUtil;

public class Math_functions {
	
	// float to int with a better value (1.99999F > 2 instead of 1.99999F > 1)
	public static int round(float floatVar){
		int intPart = (int)floatVar;
		int result = intPart;
		//positive
		if(floatVar > 0) {
			if(floatVar-intPart >= 0.5F)
				result++;
		//negative 
		}else {
			if(intPart-floatVar >= 0.5F)
				result--;
		}
		return(result);
	}
	
}