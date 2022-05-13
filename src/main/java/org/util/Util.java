package org.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {

	private Util() {};
	
	public static String readFile() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("inputFile.txt"));

		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
			br.close();
		}

		return sb.toString();

	}
}
