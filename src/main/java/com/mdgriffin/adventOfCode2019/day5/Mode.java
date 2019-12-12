package com.mdgriffin.adventOfCode2019.day5;

public enum Mode {
	POSITION, IMMEDIATE;

	public static Mode getByCode(int code) {
		if (code == 0) {
			return POSITION;
		} else if (code == 1) {
			return IMMEDIATE;
		}

		throw new IllegalArgumentException("Mode code can only be 0 or 1");
	}
}