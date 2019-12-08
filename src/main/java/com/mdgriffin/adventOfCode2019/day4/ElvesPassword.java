package com.mdgriffin.adventOfCode2019.day4;

public class ElvesPassword {

	static int getNumberOfPossiblePasswords(int rangeMin, int rangeMax) {
		int numPasswords = 0;

		for (int i = rangeMin; i <= rangeMax; i++) {
			if (fulfillsAllConditions(i)) {
				numPasswords++;
			}
		}

		return numPasswords;
	}

	static boolean fulfillsAllConditions(int index) {
		return digitsOnlyIncrease(index) && hasTwoConsecutiveDigitsSame(index);
	}

	static boolean hasTwoConsecutiveDigitsSame(int index) {
		String indexAsString = Integer.toString(index);

		for (int i = 1; i < indexAsString.length(); i++) {
			int currentDigit = indexAsString.charAt(i) - '0';
			int lastDigit = indexAsString.charAt(i - 1) - '0';

			if (currentDigit == lastDigit) {
				return true;
			}

		}

		return false;
	}

	static boolean digitsOnlyIncrease(int index) {
		String indexAsString = Integer.toString(index);

		for (int i = 1; i < indexAsString.length(); i++) {
			int currentDigit = indexAsString.charAt(i) - '0';
			int lastDigit = indexAsString.charAt(i - 1) - '0';

			if (currentDigit < lastDigit) {
				return false;
			}

		}

		return true;
	}

}
