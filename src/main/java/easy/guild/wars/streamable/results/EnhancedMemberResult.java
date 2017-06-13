package easy.guild.wars.streamable.results;

import java.util.Map;
import java.util.TreeMap;

import easy.guild.wars.domainmodel.BasicMemberResult;
import easy.guild.wars.streamable.Streamable;

public class EnhancedMemberResult extends BasicMemberResult implements Streamable {
	public int avgMightAttacked;
	public int targetAvgMight;
	public int targetPoints;

	public int getPointsGainedLost() {
		return points - targetPoints;
	}

	public double getPercent() {
		return points * 100d / targetPoints;
	}

	public static EnhancedMemberResult enhance(BasicMemberResult bs) {
		EnhancedMemberResult result = new EnhancedMemberResult();
		result.account = bs.account;
		result.might = bs.might;
		result.points = bs.points;
		return result;
	}

	@Override
	public Object toJson() {
		Map<String, Object> map = new TreeMap<>();
		map.put("name", account.name);
		map.put("might", might);
		map.put("points", points);
		map.put("target", targetPoints);
		map.put("percent", getPercent());
		map.put("gainedLost", getPointsGainedLost());
		map.put("avgAttacked", avgMightAttacked);
		return map;
	}
}
