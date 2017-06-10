package easy.guild.wars.domainmodel;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import easy.guild.wars.scores.EnhancedResult;

public class Guild {
	private String name;

	private List<Member> members = new LinkedList<>();

	private List<EnhancedResult> guildWarsResults = new LinkedList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<EnhancedResult> getGuildWarsResults() {
		return guildWarsResults;
	}

	public void setGuildWarsResults(List<EnhancedResult> guildWarsResults) {
		this.guildWarsResults = guildWarsResults;
	}

	public Optional<Member> memberByName(String name) {
		return getMembers().stream().filter(m -> m.getName().equals(name)).findAny();
	}

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
