package com.mdgriffin.adventOfCode2019.day8;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class SpaceImageFormatTest {

	private static final String PROVIDED_INPUT = FileReader.readFile("/test-input/day8.txt");
	
	@Test
	public void dataLoaded() {
		assertTrue(PROVIDED_INPUT.length() > 0);
	}

}
