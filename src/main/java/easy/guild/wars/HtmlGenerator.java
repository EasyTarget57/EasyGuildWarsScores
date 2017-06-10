package easy.guild.wars;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

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
}
