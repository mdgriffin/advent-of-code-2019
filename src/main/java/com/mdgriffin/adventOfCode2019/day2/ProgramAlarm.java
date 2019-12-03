package com.mdgriffin.adventOfCode2019.day2;

public class ProgramAlarm {

	public static int[] compute(int[] memory) {
		opCodesLoop: for (int i = 0; i < memory.length; i += 4) {
			int currentOpCode = memory[i];
			int firstArgAddress = memory[i + 1];
			int secondArgAddress = memory[i + 2];
			int outputAddress = memory[i + 3];

			switch (Operation.getOperationByOpCode(currentOpCode)) {
			case SUM:
				memory[outputAddress] = memory[firstArgAddress] + memory[secondArgAddress];
				break;
			case MULTIPLY:
				memory[outputAddress] = memory[firstArgAddress] * memory[secondArgAddress];
				break;
			case EXIT:
				break opCodesLoop;
			default:
				break;
			}

		}

		return memory;
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
