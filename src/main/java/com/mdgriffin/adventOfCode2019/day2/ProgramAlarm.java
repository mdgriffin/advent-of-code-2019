package com.mdgriffin.adventOfCode2019.day2;

public class ProgramAlarm {

	public static int[] compute(int[] opCodes) {
		opCodesLoop: for (int i = 0; i < opCodes.length; i += 4) {
			int currentOpCode = opCodes[i];
			int firstArgPos = opCodes[i + 1];
			int secondArgPos = opCodes[i + 2];
			int outputPos = opCodes[i + 3];

			switch (Operation.getOperationByOpCode(currentOpCode)) {
			case SUM:
				opCodes[outputPos] = opCodes[firstArgPos] + opCodes[secondArgPos];
				break;
			case MULTIPLY:
				opCodes[outputPos] = opCodes[firstArgPos] * opCodes[secondArgPos];
				break;
			case EXIT:
				break opCodesLoop;
			default:
				break;
			}

		}

		return opCodes;
	}

	private enum Operation {
		SUM(1), MULTIPLY(2), EXIT(99), DO_NOTHING(0);

		int opCode;

		Operation(int opCode) {
			this.opCode = opCode;
		}

		public static Operation getOperationByOpCode(int opCode) {
			if (opCode == 1) {
				return SUM;
			} else if (opCode == 2) {
				return MULTIPLY;
			} else if (opCode == 99) {
				return EXIT;
			}

			return DO_NOTHING;
		}
	}

}
