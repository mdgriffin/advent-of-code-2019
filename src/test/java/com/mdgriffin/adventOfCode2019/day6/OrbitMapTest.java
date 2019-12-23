package com.mdgriffin.adventOfCode2019.day6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class OrbitMapTest {
	
	private static final String PROVIDED_INPUT = FileReader.readFile("/test-input/day6.txt");
	
	@Test
	public void getNumOrbits_withSampleInput_countCorrect() {
		String orbitDescriptor = "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L";
		OrbitMap orbitMap = new OrbitMap(orbitDescriptor);
		
		assertEquals(42, orbitMap.getNumOrbits());
	}

	@Test
	public void getNumOrbits_withProvidedInput_correctSolution() {
		OrbitMap orbitMap = new OrbitMap(PROVIDED_INPUT);
		assertEquals(268504, orbitMap.getNumOrbits());
	}
	@Test
	public void getInterPlanetDistance_withSampleInput() {
		String orbitDescriptor = "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L\nK)YOU\nI)SAN";
		OrbitMap orbitMap = new OrbitMap(orbitDescriptor);
		
		assertEquals(4, orbitMap.getInterPlanetDistance("YOU", "SAN") - 1);
	}
	
	@Test
	public void getInterPlanetDistance_withProvidedInput() {
		OrbitMap orbitMap = new OrbitMap(PROVIDED_INPUT);
		
		assertEquals(409, orbitMap.getInterPlanetDistance("YOU", "SAN") - 1);
	}

}
