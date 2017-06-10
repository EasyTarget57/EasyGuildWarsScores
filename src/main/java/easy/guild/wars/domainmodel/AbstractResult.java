package easy.guild.wars.domainmodel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractResult<T extends BasicMemberResult> {
	private Guild guild;
	private List<T> memberResults = new LinkedList<>();
	private Date date;
	private int rank;

	public AbstractResult(Guild guild, Date date, int rank, Class<T> memberScoreClass) {
		this.guild = guild;
		this.date = date;
		this.rank = rank;
	}

	public Guild getGuild() {
		return guild;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public List<T> getMemberResults() {
		return memberResults;
	}

	public void setMemberResults(List<T> memberResults) {
		this.memberResults = memberResults;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getGuildPoints() {
		return getMemberResults().stream().collect(Collectors.summingInt(t -> t.getPoints()));
	}

	@Override
	public String toString() {
		return "GuildWarsResult [guild=" + guild + ", date=" + date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((guild == null) ? 0 : guild.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
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
		AbstractResult other = (AbstractResult) obj;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (guild == null) {
			if (other.guild != null) {
				return false;
			}
		} else if (!guild.equals(other.guild)) {
			return false;
		}
		return true;
	}
}
