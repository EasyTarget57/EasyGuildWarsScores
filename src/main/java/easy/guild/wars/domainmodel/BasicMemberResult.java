package easy.guild.wars.domainmodel;

public class BasicMemberResult {
	private BasicResult guildWars;
	private Member member;
	private int might;
	private int score;

	public BasicMemberResult(BasicResult guildWars, Member member, int might, int score) {
		this.guildWars = guildWars;
		this.member = member;
		this.might = might;
		this.score = score;
	}

	public BasicResult getGuildWars() {
		return guildWars;
	}

	public void setGuildWars(BasicResult guildWars) {
		this.guildWars = guildWars;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getMight() {
		return might;
	}

	public void setMight(int might) {
		this.might = might;
	}

	public int getPoints() {
		return score;
	}

	public void setPoints(int score) {
		this.score = score;
	}

	public int getBasePoints() {
		return (int) Math.floor(Math.pow(Integer.valueOf(getMight()).doubleValue(), 0.47) / 1.33);
	}

	@Override
	public String toString() {
		return "GuildWarsMemberScore [guildWars=" + guildWars + ", member=" + member + ", might=" + might + ", score="
				+ score + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((guildWars == null) ? 0 : guildWars.hashCode());
		result = prime * result + ((member == null) ? 0 : member.hashCode());
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
		BasicMemberResult other = (BasicMemberResult) obj;
		if (guildWars == null) {
			if (other.guildWars != null) {
				return false;
			}
		} else if (!guildWars.equals(other.guildWars)) {
			return false;
		}
		if (member == null) {
			if (other.member != null) {
				return false;
			}
		} else if (!member.equals(other.member)) {
			return false;
		}
		return true;
	}

}
