package com.mdgriffin.adventOfCode2019.day4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ElvesPasswordTest {

	private int rangeMin = 387638;
	private int rangeMax = 919123;

	@Test
	public void whenGettingNumberOfPossiblePasswords_withProvidedRange_resultCorrect() {
		assertEquals(466, ElvesPassword.getNumberOfPossiblePasswords(rangeMin, rangeMax));
	}

	@Test
	public void whenGettingNumberOfPossiblePassword_withNewCondition_andProvidedRange_resultsCorrect() {
		assertEquals(292, ElvesPassword.getNumberOfPossiblePasswordWithNewCondition(rangeMin, rangeMax));
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

	@Test
	public void whenCheckingIfhasOnlyTwoConsecutiveCharactersSame_resultsCorrect() {
		assertFalse(ElvesPassword.hasOnlyTwoConsecutiveCharactersSame(123456));
		assertFalse(ElvesPassword.hasOnlyTwoConsecutiveCharactersSame(123444));
		assertFalse(ElvesPassword.hasOnlyTwoConsecutiveCharactersSame(111222));
		assertTrue(ElvesPassword.hasOnlyTwoConsecutiveCharactersSame(112345));
		assertTrue(ElvesPassword.hasOnlyTwoConsecutiveCharactersSame(112233));
	}

}
