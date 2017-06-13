package easy.guild.wars.domainmodel;

import java.util.Date;

import easy.guild.wars.streamable.util.StreamableMap;

public abstract class AbstractResult<T extends BasicMemberResult> extends StreamableMap<T> {
	public Guild guild;
	public Date date;
	public int rank;

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
