package easy.guild.wars.scores;

import easy.guild.wars.domainmodel.BasicMemberResult;
import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Member;

public class EnhancedMemberResult extends BasicMemberResult {
	private int avgMight;
	private int targetAvgMight;
	private int targetPoints;

	private EnhancedMemberResult(BasicResult guildWars, Member member, int might, int score) {
		super(guildWars, member, might, score);
	}

	public int getAvgMight() {
		return avgMight;
	}

	public void setAvgMight(int avgMight) {
		this.avgMight = avgMight;
	}

	public int getTargetAvgMight() {
		return targetAvgMight;
	}

	public void setTargetAvgMight(int targetAvgMight) {
		this.targetAvgMight = targetAvgMight;
	}

	public int getTargetPoints() {
		return targetPoints;
	}

	public void setTargetPoints(int targetPoints) {
		this.targetPoints = targetPoints;
	}

	public int getPointsGainedLost() {
		return getPoints() - getTargetPoints();
	}

	public double getPercentage() {
		return getPoints() * 100d / getTargetPoints();
	}

	public static EnhancedMemberResult enhance(BasicMemberResult bs) {
		return new EnhancedMemberResult(bs.getGuildWars(), bs.getMember(), bs.getMight(), bs.getPoints());
	}
}
