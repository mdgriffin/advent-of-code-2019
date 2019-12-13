package com.mdgriffin.adventOfCode2019.day5;

public enum Operation {
	SUM(1, 4), MULTIPLY(2, 4), STORE(3, 2), OUTPUT(4, 2), EXIT(99, 1), DO_NOTHING(0, 1);

	private int opCode;
	private int numArgs;

	Operation(int opCode, int numArgs) {
		this.opCode = opCode;
		this.numArgs = numArgs;
	}

	public int getNumArgs() {
		return numArgs;
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
		case 4:
			operation = OUTPUT;
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
