package easy.guild.wars.domainmodel;

import java.util.LinkedList;
import java.util.List;

import easy.guild.wars.streamable.results.EnhancedResult;

public class Guild {
	public String name;

	public List<EnhancedResult> guildWarsResults = new LinkedList<>();

	@Override
	public String toString() {
		return "Guild [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Guild other = (Guild) obj;
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
