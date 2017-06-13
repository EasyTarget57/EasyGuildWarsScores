package easy.guild.wars.streamable.ui;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import easy.guild.wars.streamable.GsonProvider;
import easy.guild.wars.streamable.Streamable;

class GridViewDefinitionImpl extends ViewDefinitionBaseImpl implements GridViewDefinition {

	private final List<String> columnKeys;

	GridViewDefinitionImpl(String name, ViewType type, List<String> columnKeys) {
		super(name, type);
		this.columnKeys = columnKeys;
	}

	@Override
	public List<String> getColumnNames() {
		return columnKeys;
	}

	@Override
	public Object toJson() {
		Map<String, Object> map = new TreeMap<>();
		map.put("name", getName());
		map.put("type", getType().name());
		map.put("columnKeys", columnKeys);
		return map;
	}

}