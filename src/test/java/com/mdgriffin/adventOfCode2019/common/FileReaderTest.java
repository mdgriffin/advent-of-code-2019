package com.mdgriffin.adventOfCode2019.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class FileReaderTest {

	private static final String FILE_PATH = "/test-input/common-test.txt";

	@Test
	public void shouldReadFilesToString() throws IOException {
		String result = FileReader.readFile(FILE_PATH);

		assertTrue(result.length() > 0);
		assertTrue(result.contains("209"));
	}

	@Test
	public void readCSVFile() {
		long[] csv = FileReader.readCSVFile(FILE_PATH);

		assertEquals(9, csv.length);
		assertEquals(1, csv[0]);
	}

	@Test
	public void convertToLongs() {
		String[] inputs = new String[] { "1", "2", "3", "4" };

		long[] converted = FileReader.convertToLongs(inputs);

		assertEquals(4, inputs.length);
		assertEquals(1, converted[0]);
	}

}
