package com.mdgriffin.adventOfCode2019.computer;

public enum Operation {
	SUM(1, 4), MULTIPLY(2, 4), STORE(3, 2), OUTPUT(4, 2), JUMP_IF_TRUE(5, 3), JUMP_IF_FALSE(6, 3), LESS_THAN(7, 4),
	EQUALS(8, 4), ADJUST_RELATIVE_BASE(9, 2), EXIT(99, 1), DO_NOTHING(0, 4);

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
		case 5:
			operation = JUMP_IF_TRUE;
			break;
		case 6:
			operation = JUMP_IF_FALSE;
			break;
		case 7:
			operation = LESS_THAN;
			break;
		case 8:
			operation = EQUALS;
			break;
		case 9:
			operation = ADJUST_RELATIVE_BASE;
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
