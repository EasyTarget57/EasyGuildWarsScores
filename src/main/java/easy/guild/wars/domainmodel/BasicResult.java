package easy.guild.wars.domainmodel;

import java.util.Date;
import java.util.Optional;

import easy.guild.wars.streamable.util.StreamableMap;

public class BasicResult extends StreamableMap<BasicMemberResult> {
	public Guild guild;
	public Date date;
	public int rank;

	public Optional<BasicMemberResult> memberByName(String name) {
		return Optional.ofNullable(get(name));
	}
}
