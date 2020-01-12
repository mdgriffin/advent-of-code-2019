package com.mdgriffin.adventOfCode2019.computer;

public enum Mode {
	POSITION, IMMEDIATE, RELATIVE;

	public static Mode getByCode(int code) {
		if (code == 0) {
			return POSITION;
		} else if (code == 1) {
			return IMMEDIATE;
		} else if (code == 2) {
			return RELATIVE;
		}

		throw new IllegalArgumentException("Mode code can only be 0,1 or 2");
	}
}