package easy.guild.wars.streamable.util;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

import easy.guild.wars.streamable.GsonProvider;
import easy.guild.wars.streamable.Streamable;

public class StreamableList<T extends Streamable> extends LinkedList<T> implements Streamable {
	@Override
	public Object toJson() {
		return Lists.transform(this, s -> s.toJson());
	}

}
