package easy.guild.wars;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import easy.guild.wars.domainmodel.Guild;
import easy.guild.wars.streamable.results.EnhancedResult;
import easy.guild.wars.streamable.results.EnhancedResultContainer;

public class Main implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static final File inputFolder = new File(Main.class.getClassLoader().getResource("input").getFile());
	
	private final Parser parser = new Parser();
	private final HtmlGenerator htmlGenerator = new HtmlGenerator();

	private final EnhancedResultContainer resultContainer = new EnhancedResultContainer();

	private final Guild guild;

	private final int rank;

	public Main(Guild guild, int rank) {
		this.guild = guild;
		this.rank = rank;
	}

	public static void main(String[] argsArray) {
		List<String> args = new LinkedList<>(Arrays.asList(argsArray));
		String guildName = args.remove(0);
		int rank = Integer.parseInt(args.remove(0));
		List<File> argFiles = args.stream().map(s -> new File(s)).collect(Collectors.toList());
		List<File> inputFiles = argFiles.stream().filter(f -> f.exists()).sorted().collect(Collectors.toList());
		Guild guild = new Guild();
		guild.name = (guildName);
		Main main = new Main(guild, rank);
		main.run();
	}

	@Override
	public void run() {
		List<File> inputFiles = collectInputFiles();
		
		LOGGER.info("Checking " + inputFiles);
		inputFiles.stream().forEach(this::tryParseFile);
		LOGGER.info("Successfully parsed {} files to EnhancedResults", resultContainer.size());

		try {
			htmlGenerator.writeHtml(resultContainer);
			LOGGER.info("Successfully written out the website.");
		} catch (IOException e) {
			LOGGER.error("IOException generating HTML.", e);
			throw new UncheckedIOException(e);
		}
	}

	private List<File> collectInputFiles() {
		List<File> result = new LinkedList<>();
		collectInputFiles(inputFolder, result);
		return result;
	}

	private void collectInputFiles(File f, List<File> result) {
		if(f.isDirectory()){
			for(File fc : f.listFiles()){
				collectInputFiles(fc,result);
			}
		} else if(f.exists())
			result.add(f);
	}

	private void tryParseFile(File f) {
		if (!f.exists()) {
			LOGGER.warn("{} does not exist.", f);
			return;
		}
		if (f.isDirectory()) {
			Arrays.stream(f.listFiles()).forEach(this::tryParseFile);
		} else {
			try {
				EnhancedResult result = parser.parseAndEnhance(guild, new Date(), rank, f);
				resultContainer.add(result);
			} catch (Exception e) {
				LOGGER.error("Error parsing " + f.getAbsolutePath(), e);
			}
		}

	}
}
