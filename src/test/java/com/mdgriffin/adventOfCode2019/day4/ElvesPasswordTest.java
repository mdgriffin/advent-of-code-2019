package com.mdgriffin.adventOfCode2019.day4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ElvesPasswordTest {

	@Test
	public void whenGettingNumberOfPossiblePasswords_withProvidedRange_resultCorrect() {
		int rangeMin = 387638;
		int rangeMax = 919123;
		assertEquals(466, ElvesPassword.getNumberOfPossiblePasswords(rangeMin, rangeMax));
	}

	@Test
	public void whenCheckingIfPasswordIncreasee_resultCorrect() {
		assertTrue(ElvesPassword.digitsOnlyIncrease(122345));
		assertFalse(ElvesPassword.digitsOnlyIncrease(765432));
	}

	@Test
	public void whenCheckingIfTwoConsecutiveDigitsSame_resultsCorrect() {
		assertTrue(ElvesPassword.hasTwoConsecutiveDigitsSame(1123456));
		assertTrue(ElvesPassword.hasTwoConsecutiveDigitsSame(123455));
		assertFalse(ElvesPassword.hasTwoConsecutiveDigitsSame(123456));
	}

}
