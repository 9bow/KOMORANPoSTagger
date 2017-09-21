package app;

import org.apache.commons.configuration.*;
import org.apache.commons.io.*;
import util.KOMORANWrapper;
import util.PoSTagger;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;



public class Application {
	// Config
	private static Configuration config;

	// Instances
	private static KOMORANWrapper komoran = new KOMORANWrapper();
	private static PoSTagger tagger = new PoSTagger();


	public static void main(String[] args) {
		loadConfig();

		// get filename
		String inputFilename = config.getString("input.sample");
		if (args.length >= 1 && !"".equals(args[0].trim())) {
			inputFilename = args[0].trim();
		}

		// check and mkdirs for output path
		File outPath = new File(config.getString("output.dirpath"));
		if (!outPath.exists()) {
			outPath.mkdirs();
		}

		// check and delete for output file
		File outFile = new File(config.getString("output.dirpath") + (new File(inputFilename)).getName() + config.getString("output.postfix"));
		if (outFile.exists()) {
			outFile.delete();
		}

		System.out.println("Input : "+ inputFilename);
		System.out.println("Output: "+ outFile.getPath());

		// read, analyze and write
		try {
			System.out.print("Analyzing and pos tagging file... ");
			analyzeAndTagFile(inputFilename);
			System.out.println("DONE");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void loadConfig() {
		try {
			config = new PropertiesConfiguration("config.properties");
		}
		catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	private static void analyzeAndTagFile(String inputFilename) throws Exception {
		File file = new File(inputFilename);
		String outFilename = config.getString("output.dirpath") + file.getName() + config.getString("output.postfix");
		RandomAccessFile outStream = new RandomAccessFile(outFilename, "rw");
		FileChannel outChannel = outStream.getChannel();
		LineIterator it = FileUtils.lineIterator(file, config.getString("input.charset"));


		try {
			while (it.hasNext()) {
				String line = it.nextLine().trim();

				if (!"".equals(line)) {
					outChannel.write(Charset.forName(config.getString("output.charset")).newEncoder().encode(CharBuffer.wrap(tagger.tag(komoran.analyze(line)) + config.getString("delimiter.line"))));
				}
			}
		}
		finally {
			LineIterator.closeQuietly(it);
			outStream.close();
			outChannel.close();
		}
	}

}
