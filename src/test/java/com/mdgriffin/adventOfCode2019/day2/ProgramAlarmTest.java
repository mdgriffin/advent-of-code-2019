package com.mdgriffin.adventOfCode2019.day2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProgramAlarmTest {

	@Test
	public void whenComputing_withValidInvalid_outputSequenceCorrect() {
		long[] opCodes = new long[] { 1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50 };
		long[] expected = new long[] { 3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50 };
		long[] actual = ProgramAlarm.compute(opCodes);

		assertArrayEquals(expected, actual);
	}

	@Test
	public void whenComputingFirstValue_withProvidedInput_firstValueCorrect() {
		long[] providedInput = getProvidedValues();
		long[] actual = ProgramAlarm.compute(providedInput);

		assertEquals(5482655, actual[0]);

	}

	@Test
	public void whenGettingPart2_bruteForceForAnswer() {
		int target = 19690720;
		for (int a = 0; a <= 99; a++) {
			for (int b = 0; b <= 99; b++) {
				long[] providedInput = getProvidedValues();
				providedInput[1] = a;
				providedInput[2] = b;

				long[] actual = ProgramAlarm.compute(providedInput);
				if (actual[0] == target) {
					System.out.println("noun = " + a + " , verb = " + b + " = " + target);
				}
			}
		}

		assertEquals(4967, 100 * 49 + 67);
	}

	private static long[] getProvidedValues() {
		long[] providedInput = new long[] { 1, 12, 2, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 13, 1, 19, 1, 10, 19,
				23, 1, 6, 23, 27, 1, 5, 27, 31, 1, 10, 31, 35, 2, 10, 35, 39, 1, 39, 5, 43, 2, 43, 6, 47, 2, 9, 47, 51,
				1, 51, 5, 55, 1, 5, 55, 59, 2, 10, 59, 63, 1, 5, 63, 67, 1, 67, 10, 71, 2, 6, 71, 75, 2, 6, 75, 79, 1,
				5, 79, 83, 2, 6, 83, 87, 2, 13, 87, 91, 1, 91, 6, 95, 2, 13, 95, 99, 1, 99, 5, 103, 2, 103, 10, 107, 1,
				9, 107, 111, 1, 111, 6, 115, 1, 115, 2, 119, 1, 119, 10, 0, 99, 2, 14, 0, 0 };

		return providedInput;
	}

}
