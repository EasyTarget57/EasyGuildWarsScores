package easy.guild.wars.streamable.results;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import easy.guild.wars.domainmodel.BasicMemberResult;
import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Guild;
import easy.guild.wars.streamable.Streamable;
import easy.guild.wars.streamable.util.StreamableMap;

public class EnhancedResult extends StreamableMap<EnhancedMemberResult> implements Streamable {

	private static final double TARGET_RATIO_MAX_MIGHT = 0.95;
	private static final double TARGET_RATIO_MIN_MIGHT = 0.9;

	public Guild guild;
	public Date date;
	public int rank;

	public int guildTargetPoints() {
		int targetPoints = 0;
		for (EnhancedMemberResult result : values()) {
			targetPoints += result.targetPoints;
		}
		return targetPoints;
	}

	public int guildPoints() {
		int points = 0;
		for (EnhancedMemberResult result : values()) {
			points += result.points;
		}
		return points;
	}

	public double getPercent() {
		return guildPoints() * 100d / guildTargetPoints();
	}

	public static EnhancedResult enhance(BasicResult basicResult) {
		EnhancedResult result = new EnhancedResult();
		result.guild = basicResult.guild;

		int minMight = Integer.MAX_VALUE;
		int maxMight = 0;
		int maxAvgMight = 0;

		for (BasicMemberResult br : basicResult.values()) {
			EnhancedMemberResult es = EnhancedMemberResult.enhance(br);
			int baseScore = es.getBasePoints();
			int mightScore = es.points - baseScore * 5;
			int avgMight = mightScore * 700 / 5;
			es.avgMightAttacked = avgMight;
			result.put(es.account.name, es);

			minMight = Math.min(es.might, minMight);
			maxMight = Math.max(es.might, maxMight);
			maxAvgMight = Math.max(avgMight, maxAvgMight);
		}

		for (EnhancedMemberResult es : result.values()) {
			int targetMight = getTargetAvgMight(es.avgMightAttacked, maxAvgMight, es.might, minMight, maxMight);
			es.targetAvgMight = targetMight;

			int targetPoints = 5 * (es.getBasePoints() + targetMight / 700);
			es.targetPoints = targetPoints;
		}
		return result;
	}

	private static int getTargetAvgMight(int avgMight, int maxAvgMight, int might, int minMight, int maxMight) {
		double r = Integer.valueOf(might - minMight).doubleValue() / (maxMight - minMight);
		double targetPercent = (1 - r) * TARGET_RATIO_MIN_MIGHT + r * TARGET_RATIO_MAX_MIGHT;
		return (int) Math.round(targetPercent * maxAvgMight);
	}

	@Override
	public Object toJson() {
		Map<String, Object> map = new TreeMap<>();
		List<Object> childrenAsJson = values().stream().map(emr -> emr.toJson()).collect(Collectors.toList());

		map.put("date", date);
		map.put("guildName", guild.name);
		map.put("guildPoints", guildPoints());
		map.put("guildTargetPoints", guildTargetPoints());
		map.put("memberResults", childrenAsJson);
		map.put("percent", getPercent());
		map.put("rank", rank);
		return map;
	}
}
