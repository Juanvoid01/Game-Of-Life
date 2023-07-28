package simulator.factories;

import java.util.List;

public interface Factory<T> {
	public T createInstance(String info);

	public List<String> getInfo();
}
