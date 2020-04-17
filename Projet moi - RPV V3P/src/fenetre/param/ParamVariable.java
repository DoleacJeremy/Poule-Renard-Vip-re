package fenetre.param;

public class ParamVariable<T> {
	private T paramValue;
	
	public ParamVariable(T paramValue) {
		this.paramValue = paramValue;
	}
	
	public T getValue() {
		return(this.paramValue);
	}
}