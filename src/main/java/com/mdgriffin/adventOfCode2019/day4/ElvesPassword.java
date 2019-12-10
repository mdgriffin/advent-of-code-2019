package com.mdgriffin.adventOfCode2019.day4;

public class ElvesPassword {

	private static String[] numbersRepeatedThreeTimes = new String[] { "000", "111", "222", "333", "444", "555", "666",
			"777", "888", "999" };

	static int getNumberOfPossiblePasswords(int rangeMin, int rangeMax) {
		int numPasswords = 0;

		for (int i = rangeMin; i <= rangeMax; i++) {
			if (fulfillsAllConditions(i)) {
				numPasswords++;
			}
		}

		return numPasswords;
	}

	static int getNumberOfPossiblePasswordWithNewCondition(int rangeMin, int rangeMax) {
		int numPasswords = 0;

		for (int i = rangeMin; i <= rangeMax; i++) {
			if (fulfillsAllNewConditions(i)) {
				numPasswords++;
			}
		}

		return numPasswords;
	}

	static boolean fulfillsAllConditions(int index) {
		return digitsOnlyIncrease(index) && hasTwoConsecutiveDigitsSame(index);
	}

	static boolean fulfillsAllNewConditions(int index) {
		return digitsOnlyIncrease(index) && hasOnlyTwoConsecutiveCharactersSame(index);
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

	static boolean hasOnlyTwoConsecutiveCharactersSame(int index) {
		String indexAsString = Integer.toString(index);

		for (int i = 0; i < indexAsString.length() - 1; i++) {
			char currentChar = indexAsString.charAt(i);
			int numSameAsCurrentChar = 0;

			for (int j = i; j < indexAsString.length(); j++) {
				char nextChar = indexAsString.charAt(j);
				if (currentChar == nextChar) {
					i = j;
					numSameAsCurrentChar++;
				} else {
					break;
				}
			}

			if (numSameAsCurrentChar == 2) {
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
