package easy.guild.wars;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import easy.guild.wars.domainmodel.BasicResult;
import easy.guild.wars.domainmodel.Guild;
import easy.guild.wars.scores.EnhancedResult;

public class TestHtmlGenerator {
	private HtmlGenerator htmlGenerator = new HtmlGenerator();

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
		htmlGenerator.writeHtml(enhancedResult, outputFile);
	}
}
