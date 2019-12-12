package com.mdgriffin.adventOfCode2019.day5;

public enum Operation {
	SUM(1), MULTIPLY(2), STORE(3), EXIT(99), DO_NOTHING(0);

	int opCode;

	Operation(int opCode) {
		this.opCode = opCode;
	}

	public static Operation getOperationByOpCode(int opCode) {
		Operation operation;

		switch (opCode) {
		case 1:
			operation = SUM;
			break;
		case 2:
			operation = MULTIPLY;
			break;
		case 3:
			operation = STORE;
			break;
		case 99:
			operation = EXIT;
			break;
		default:
			operation = DO_NOTHING;
		}

		return operation;
	}
}
