package org.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;

public class Util {

	private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(Util.class.getName());
	
	private static final String PATH = "src/main/resources/inputFile.txt";

	private Util() {
	};

	public static List<String> readFile() throws IOException {

		LOGGER.info("trying to reading file at: " + PATH);
		BufferedReader reader = new BufferedReader(new FileReader(PATH));

		List<String> result = new ArrayList<String>();
		String line = reader.readLine();

		while (line != null) {
			result.add(line.trim().replace(" ", ""));
			line = reader.readLine();
		}
		reader.close();
		LOGGER.info("result from file reading: " + result.toString());
		return result;

	}
}
