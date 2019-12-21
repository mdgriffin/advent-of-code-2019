package com.mdgriffin.adventOfCode2019.day6;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountOrbitsTest {
	
	
	
	@Test
	public void whenCountingOrbits_withSameplInput_countCorrect() {
		String orbitDescriptor = "COM)B\n" + 
				"B)C\n" + 
				"C)D\n" + 
				"D)E\n" + 
				"E)F\n" + 
				"B)G\n" + 
				"G)H\n" + 
				"D)I\n" + 
				"E)J\n" + 
				"J)K\n" + 
				"K)L";
		
	    // L = 7
	    // K = 6
	    // J = 5
		// H = 3
		// I = 4
		// G = 2
		// F = 5
		// E = 4
		// D = 3
		// C = 2
		// B = 1
		// COM = 0
		// Total = 42
		
		// {COM=COM->null, B=B->COM, C=C->B, D=D->C, E=E->D, F=F->E, G=G->B, H=H->G, I=I->D, J=J->E, K=K->J, L=L->K}
		
			int count = CountOrbits.getNumOrbits(orbitDescriptor);
			
			assertEquals(42, count);
		
	}

}
