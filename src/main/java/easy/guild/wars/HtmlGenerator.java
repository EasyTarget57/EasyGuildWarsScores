package easy.guild.wars;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Guild;
import easy.guild.wars.scores.EnhancedResult;

public class HtmlGenerator {

	public void writeHtml(EnhancedResult result, File destinationFile) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("guild-wars-template.html").getFile());
		List<String> newLines = Files.lines(file.toPath())
				.map(s -> s.replace("$headersString", result.getHeadersString()))
				.map(s -> s.replace("$scoresString", result.getValuesString())).collect(Collectors.toList());

		File outputFolder = new File(classLoader.getResource("website").getFile());
		File outputFile = new File(outputFolder, result.getGuild().getName() + "-"
				+ new SimpleDateFormat("yyyyMMdd").format(result.getDate()) + "-" + result.getRank() + ".html");
		outputFile.createNewFile();

		try (PrintWriter pw = new PrintWriter(outputFile)) {
			for (String nl : newLines) {
				pw.println(nl);
			}
			pw.flush();
		}
	}

	@Test
	public void test() throws IOException {
		Parser parser = new Parser();
		Guild guild = new Guild();
		guild.setName("420-United");
		Date date = new Date();
		int rank = 4;

		ClassLoader classLoader = getClass().getClassLoader();
		File inputFile = new File(classLoader.getResource("20170608-4-420-United.txt").getFile());
		BasicResult result = parser.parse(guild, date, rank, inputFile);
		EnhancedResult enhancedResult = EnhancedResult.enhance(result);
		File outputFile = new File("20170608-4-420-United-out.html");
		writeHtml(enhancedResult, outputFile);
	}
}
