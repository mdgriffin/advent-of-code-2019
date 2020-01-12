package com.mdgriffin.adventOfCode2019.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FileReader {

	public static String readFile(String path) {
		StringBuilder sb = new StringBuilder();

		try {
			InputStream inputStream = FileReader.class.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";

			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException exc) {
			System.out.println(exc.getMessage());
		}

		return sb.toString();
	}

	public static long[] readCSVFile(String path) {

		return convertToLongs(splitOnDelim(readFile(path).replaceAll("\\r?\\n", ""), ","));
	}

	public static String[] splitOnDelim(String input, String delim) {
		return input.split(delim);
	}

	public static long[] convertToLongs(String[] stringInts) {
		return Arrays.stream(stringInts).mapToLong(Long::parseLong).toArray();
	}

}
