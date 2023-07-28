package simulator.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderBasedFactory<T> implements Factory<T> {
	private Map<String,Builder<T>> _builders;

	public BuilderBasedFactory() {
			this._builders = new HashMap<String,Builder<T>>();
	}
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this();
		for(Builder<T> b : builders) {
			this.addBuilder(b);
		}
	}
	
	public void addBuilder(Builder<T> b) {
		this._builders.put(b.type, b);
	}
	
	@Override
	public T createInstance(String type) {
		
		if(_builders.containsKey(type)) {
			return _builders.get(type).createinstance();
		}
		return null;
	}

	@Override
	public List<String> getInfo() {
		List<String> info = new ArrayList<String>();
		
		for(String b : _builders.keySet()) {
			info.add(b + " " + _builders.get(b).desc);
		}
		return info;
	}
	
}
