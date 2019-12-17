package com.mdgriffin.adventOfCode2019.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FileReader {

	public static String readFile(String path) throws IOException {
		InputStream inputStream = FileReader.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String line = "";

		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}

	public static int[] readCSVFile(String path) throws IOException {
		return convertToInts(splitOnDelim(readFile(path), ","));
	}

	public static String[] splitOnDelim(String input, String delim) {
		return input.split(delim);
	}

	public static int[] convertToInts(String[] stringInts) {
		return Arrays.stream(stringInts).mapToInt(Integer::parseInt).toArray();
	}

}
