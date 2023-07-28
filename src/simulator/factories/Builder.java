package simulator.factories;

public abstract class Builder<T> {

	String type;
	String desc;
	
	Builder(String type, String desc){
		this.type = type;
		this.desc = desc;
	}
	
	abstract protected T createinstance();
}
