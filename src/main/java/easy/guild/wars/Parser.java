package easy.guild.wars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import easy.guild.wars.domainmodel.Account;
import easy.guild.wars.domainmodel.BasicMemberResult;
import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Guild;
import easy.guild.wars.streamable.results.EnhancedResult;

public class Parser {
	public EnhancedResult parseAndEnhance(Guild guild, Date date, int rank, File guildWarsData) throws IOException {
		BasicResult result = new BasicResult();
		result.guild = guild;
		try (BufferedReader br = new BufferedReader(new FileReader(guildWarsData))) {
			List<String> lines = new LinkedList<>();
			String line = br.readLine();

			do {
				lines.add(line);
				line = br.readLine();
			} while (line != null);

			lines.stream().skip(1).filter(l -> !l.isEmpty()).map(l -> parseLine(l))
					.sorted((a, b) -> Integer.compare(b.points, a.points))
					.forEach(bmr -> result.put(bmr.account.name, bmr));
			return EnhancedResult.enhance(result);
		}
	}

	private BasicMemberResult parseLine(String l) {
		String[] parts = l.trim().split("\\t");
		String name = parts[0];
		int might = Integer.parseInt(parts[1]);
		int points = Integer.parseInt(parts[2]);
		Account account = new Account();
		account.name = name;

		BasicMemberResult result = new BasicMemberResult();
		result.account = account;
		result.might = might;
		result.points = points;

		return result;
	}

}
