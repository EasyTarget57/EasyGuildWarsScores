package easy.guild.wars.scores;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import easy.guild.wars.domainmodel.AbstractResult;
import easy.guild.wars.domainmodel.BasicMemberResult;
import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Guild;

public class EnhancedResult extends AbstractResult<EnhancedMemberResult> {

	private static final double TARGET_RATIO_MAX_MIGHT = 0.95;
	private static final double TARGET_RATIO_MIN_MIGHT = 0.9;

	private EnhancedResult(Guild guild, Date date, int rank) {
		super(guild, date, rank, EnhancedMemberResult.class);
	}

	public int getGuildTargetPoints() {
		return getMemberResults().stream().collect(Collectors.summingInt(r -> r.getTargetAvgMight()));
	}

	public double getPercentPoints() {
		return getGuildPoints() * 100d / getGuildTargetPoints();
	}

	public String getHeadersString() {
		List<String> hs = Arrays.asList("Name", "Might", "Points", "Target points", "Percentage", "Points gained/lost",
				"Avg might attacked");
		return quote(listToJson(hs));
	}

	private String quote(String s) {
		return "'" + s + "'";
	}

	private String listToJson(List<?> hs) {
		return "[" + hs.stream().map(s -> "\"" + s.toString() + "\"").collect(Collectors.joining(",")) + "]";
	}

	public String getValuesString() {
		return quote("[" + getMemberResults().stream()
				.map(emr -> listToJson(Arrays.asList(emr.getMember().getName(), emr.getMight(), emr.getPoints(),
						emr.getTargetPoints(), emr.getPercentage(), emr.getPointsGainedLost(), emr.getAvgMight())))
				.collect(Collectors.joining(",")) + "]");
	}

	public static EnhancedResult enhance(BasicResult basicResult) {
		List<BasicMemberResult> basicScores = basicResult.getMemberResults();
		List<EnhancedMemberResult> enhancedMemberResults = basicScores.stream()
				.map(bs -> EnhancedMemberResult.enhance(bs)).collect(Collectors.toList());
		int minMight = Integer.MAX_VALUE;
		int maxMight = 0;
		int maxAvgMight = 0;
		for (EnhancedMemberResult es : enhancedMemberResults) {
			int baseScore = es.getBasePoints();
			int mightScore = es.getPoints() - baseScore * 5;
			int avgMight = mightScore * 700 / 5;
			es.setAvgMight(avgMight);

			minMight = Math.min(es.getMight(), minMight);
			maxMight = Math.max(es.getMight(), maxMight);
			maxAvgMight = Math.max(avgMight, maxAvgMight);
		}

		for (EnhancedMemberResult es : enhancedMemberResults) {
			int targetMight = getTargetAvgMight(es.getAvgMight(), maxAvgMight, es.getMight(), minMight, maxMight);
			es.setTargetAvgMight(targetMight);

			int targetPoints = 5 * (es.getBasePoints() + targetMight / 700);
			es.setTargetPoints(targetPoints);
		}
		EnhancedResult result = new EnhancedResult(basicResult.getGuild(), basicResult.getDate(),
				basicResult.getRank());

		enhancedMemberResults.sort((a, b) -> Double.valueOf(b.getPercentage()).compareTo(a.getPercentage()));
		result.setMemberResults(enhancedMemberResults);
		return result;
	}

	private static int getTargetAvgMight(int avgMight, int maxAvgMight, int might, int minMight, int maxMight) {
		double r = Integer.valueOf(might - minMight).doubleValue() / (maxMight - minMight);
		double targetPercent = (1 - r) * TARGET_RATIO_MIN_MIGHT + r * TARGET_RATIO_MAX_MIGHT;
		return (int) Math.round(targetPercent * maxAvgMight);
	}
}
