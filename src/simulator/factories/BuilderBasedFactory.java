package simulator.factories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderBasedFactory<T> implements Factory<T> {
	private Map<String,Builder<T>> _builders;

	public BuilderBasedFactory() {
		// Create a HashMap for _builders, a LinkedList _buildersInfo
		// ...
			this._builders = new HashMap<String,Builder<T>>();
	}
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this();
		// call addBuilder(b) for each builder b in builder
		// ...
		for(Builder<T> b : builders) {
			this.addBuilder(b);
		}
	}
	
	public void addBuilder(Builder<T> b) {
		this._builders.put(b.type, b);
	}
	
	@Override
	public T createInstance(String type) {
		// TODO Auto-generated method stub
		
		if(_builders.containsKey(type)) {
			return _builders.get(type).createinstance();
		}
		return null;
	}
	
}
