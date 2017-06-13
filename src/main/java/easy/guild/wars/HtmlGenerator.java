package easy.guild.wars;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

import easy.guild.wars.streamable.EasyGuildWarsDataMap;
import easy.guild.wars.streamable.GsonProvider;
import easy.guild.wars.streamable.results.EnhancedResultContainer;
import easy.guild.wars.streamable.ui.GridColumnDefinitions;
import easy.guild.wars.streamable.ui.ViewDefinitionFactory;

public class HtmlGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlGenerator.class);

	private final File templateFolder;
	private final List<File> templateFilesToProcess;
	private final File outputFolder;

	public HtmlGenerator() {
		LOGGER.info("Initializing {}", getClass().getSimpleName());
		ClassLoader classLoader = getClass().getClassLoader();
		templateFolder = new File(classLoader.getResource("template").getFile());
		LOGGER.info("Found template folder");
		templateFilesToProcess = ImmutableList.of(new File(templateFolder, "easyguildwarsdata.js"));
		outputFolder = new File(classLoader.getResource("output").getFile());
		LOGGER.info("Found output folder location");
		if (!outputFolder.exists()) {
			outputFolder.mkdirs();
			LOGGER.info("{} created", outputFolder.getAbsolutePath());
		}
	}

	public void writeHtml(EnhancedResultContainer container) throws IOException {
		for (File file : outputFolder.listFiles()) {
			file.delete();
		}
		LOGGER.info("Preparing to write HTML for {} EnhancedResults.", container.size());
		EasyGuildWarsDataMap egwd = new EasyGuildWarsDataMap(GridColumnDefinitions.instance(),
				ViewDefinitionFactory.getInstance(), container);
		LOGGER.debug("Greated EasyGuildWarsDataMap.", container.size());
		LOGGER.debug(egwd.toJson().toString());
		clearFolder(outputFolder);
		LOGGER.debug("cleared output folder");
		List<File> templateFiles = listFiles(templateFolder);
		for (File tf : templateFiles) {
			LOGGER.debug("Handling {}", tf.getAbsolutePath());
			File processedFile = new File(outputFolder, tf.getName());
			if (!processedFile.exists()) {
				processedFile.createNewFile();
				LOGGER.debug("Created {}", processedFile.getAbsolutePath());
			}
			if (templateFilesToProcess.contains(tf)) {
				LOGGER.info("Found template file " + tf.getAbsolutePath());
				List<String> lines = Files.readAllLines(tf.toPath());
				String replacement = GsonProvider.GSON.toJson(egwd.toJson());
				lines = lines.stream().map((Function<String, String>) l -> l.replace("$egwd", replacement))
						.collect(Collectors.toList());

				File of = new File(outputFolder, tf.getName());
				Files.deleteIfExists(of.toPath());
				Files.write(of.toPath(), lines);
				LOGGER.info("Successfully wrote template file.");
			} else {
				File of = new File(outputFolder, tf.getName());
				Files.deleteIfExists(of.toPath());
				Files.copy(tf.toPath(), of.toPath());
			}
		}
	}
	
	private void clearFolder(File f) throws IOException{
		clear(f);
		f.mkdirs();
	}

	private void clear(File f) throws IOException {
		if(f.isDirectory()){
			for(File c : f.listFiles()){
				clear(c);
			}
		}
		if(f.exists())
			f.delete();

	}

	private void deleteRecursively(File f) {
		if (f.isDirectory()) {
			Arrays.stream(f.listFiles()).forEach(this::deleteRecursively);
		}
		f.delete();
	}

	private List<File> listFiles(File templateFolder) {
		return Arrays.asList(templateFolder.listFiles());
	}
}
