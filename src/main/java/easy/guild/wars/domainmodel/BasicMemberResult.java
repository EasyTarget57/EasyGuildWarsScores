package easy.guild.wars.domainmodel;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import easy.guild.wars.streamable.Streamable;

public class BasicMemberResult implements Streamable {
	public Account account;
	public int might;
	public int points;

	public int getBasePoints() {
		return (int) Math.floor(Math.pow(Integer.valueOf(might).doubleValue(), 0.47));
	}

	@Override
	public String toString() {
		return "BasicMemberResult [member=" + account + ", might=" + might + ", score=" + points + "]";
	}

	@Override
	public Object toJson() {
		Map<String, Object> result = ImmutableMap.of("account", account.name, "might", might, "points", points);
		return result;
	}

}
