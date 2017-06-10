package easy.guild.wars.domainmodel;

import java.util.Date;

public class Member {
	private Guild guild;
	private String name;
	private Date joined;
	private Date left;

	public Member(Guild guild, String name, Date joined) {
		this.guild = guild;
		this.name = name;
		this.joined = joined;
	}

	public Guild getGuild() {
		return guild;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public Date getLeft() {
		return left;
	}

	public void setLeft(Date left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return "Member [guild=" + guild + ", name=" + name + ", joined=" + joined + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guild == null) ? 0 : guild.hashCode());
		result = prime * result + ((joined == null) ? 0 : joined.hashCode());
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
		Member other = (Member) obj;
		if (guild == null) {
			if (other.guild != null) {
				return false;
			}
		} else if (!guild.equals(other.guild)) {
			return false;
		}
		if (joined == null) {
			if (other.joined != null) {
				return false;
			}
		} else if (!joined.equals(other.joined)) {
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
