package simulator.factories;

public interface Factory<T> {
	public T createInstance(String info);
}
