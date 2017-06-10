package easy.guild.wars.domainmodel;

import java.util.Date;

public class BasicResult extends AbstractResult<BasicMemberResult> {

	public BasicResult(Guild guild, Date date, int rank) {
		super(guild, date, rank, BasicMemberResult.class);
	}

}
