package easy.guild.wars;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import easy.guild.wars.domainmodel.BasicMemberResult;
import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Guild;
import easy.guild.wars.domainmodel.Member;

public class Parser {
	public BasicResult parse(Guild guild, Date date, int rank, File guildWarsData) throws IOException {
		BasicResult result = new BasicResult(guild, date, rank);
		Files.lines(guildWarsData.toPath()).forEach(s -> System.out.println(s));
		List<BasicMemberResult> scores = Files.lines(guildWarsData.toPath()).skip(1).filter(l -> !l.isEmpty())
				.map(l -> parseLine(l, result)).sorted((a, b) -> Integer.compare(b.getPoints(), a.getPoints()))
				.collect(Collectors.toList());
		result.setMemberResults(scores);
		return result;
	}

	private BasicMemberResult parseLine(String l, BasicResult guildWarsResult) {
		String[] parts = l.trim().split("\\t");
		String name = parts[0];
		int might = Integer.parseInt(parts[1]);
		int score = Integer.parseInt(parts[2]);
		Optional<Member> optionalMember = guildWarsResult.getGuild().memberByName(name);
		Member member = optionalMember.orElse(new Member(guildWarsResult.getGuild(), name, guildWarsResult.getDate()));

		return new BasicMemberResult(guildWarsResult, member, might, score);
	}

}
