package com.mdgriffin.adventOfCode2019.day7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class AmplifierTest {

	private static final int[] providedInputs = FileReader.readCSVFile("/test-input/day7.csv");

	@Test
	public void providedInputsInitialized() {
		assertEquals(515, providedInputs.length);
	}

}
