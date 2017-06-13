package easy.guild.wars.streamable.util;

import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;

import easy.guild.wars.streamable.GsonProvider;
import easy.guild.wars.streamable.Streamable;

public class StreamableMap<V extends Streamable> extends TreeMap<String, V> implements Streamable, Map<String, V> {
	@Override
	public Object toJson() {
		return Maps.transformValues(this, v -> v.toJson());
	}

}
