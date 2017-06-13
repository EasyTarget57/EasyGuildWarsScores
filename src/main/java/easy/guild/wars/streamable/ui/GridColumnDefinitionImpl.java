package easy.guild.wars.streamable.ui;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import easy.guild.wars.streamable.GsonProvider;
import easy.guild.wars.streamable.Streamable;

class GridColumnDefinitionImpl implements GridColumnDefinition {
	private final String name;
	private final Optional<Integer> maxDigitsAfterComma;

	GridColumnDefinitionImpl(String name, Optional<Integer> maxDigitsAfterComma) {
		this.name = name;
		this.maxDigitsAfterComma = maxDigitsAfterComma;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Optional<Integer> getMaxDigitsAfterComma() {
		return maxDigitsAfterComma;
	}

	@Override
	public Object toJson() {
		Map<String, Object> map = new TreeMap<>();
		map.put("name", getName());
		if (maxDigitsAfterComma.isPresent()) {
			map.put("maxDigitsAfterComma", getMaxDigitsAfterComma().get());
		}

		return map;
	}

	@Override
	public String toString() {
		return "GridColumnImpl [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maxDigitsAfterComma == null) ? 0 : maxDigitsAfterComma.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GridColumnDefinitionImpl other = (GridColumnDefinitionImpl) obj;
		if (maxDigitsAfterComma == null) {
			if (other.maxDigitsAfterComma != null) {
				return false;
			}
		} else if (!maxDigitsAfterComma.equals(other.maxDigitsAfterComma)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}