package simulator.factories;

public abstract class Builder<T> {

	String type;
	
	Builder(String type){
		this.type = type;
	}
	
	abstract protected T createinstance();
}
