package com.mdgriffin.adventOfCode2019.day6;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class CountOrbitsTest {
	
	private static final String PROVIDED_INPUT = FileReader.readFile("/test-input/day6.txt");
	
	@Test
	public void whenCountingOrbits_withSampleInput_countCorrect() {
		String orbitDescriptor = "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L";
		assertEquals(42, CountOrbits.getNumOrbits(orbitDescriptor));
		
	}
	
	@Test
	public void whenCountingOrbits_withProvidedInput_correctSolution() {
		assertEquals(268504, CountOrbits.getNumOrbits(PROVIDED_INPUT));
	}

}
