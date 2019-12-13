package com.mdgriffin.adventOfCode2019.day5;

import java.util.ArrayList;
import java.util.List;

public class ThermalAdvisorSupervisorTerminal {

	public static List<Integer> compute(int[] memory) {
		int insturctionPointer = 0;
		List<Integer> outputs = new ArrayList<Integer>();

		while (insturctionPointer < memory.length) {
			int currentInstruction = memory[insturctionPointer];
			OpParams op = OpParams.getByInstruction(currentInstruction);
			int firstArg = 0;
			int secondArg = 0;
			int outputAddress = 0;

			if (op.operation.getNumArgs() == 4) {
				firstArg = memory[insturctionPointer + 1];
				secondArg = memory[insturctionPointer + 2];
				outputAddress = memory[insturctionPointer + 3];
			} else if (op.operation.getNumArgs() == 2) {
				firstArg = memory[insturctionPointer + 1];
			}

			switch (op.operation) {
			case SUM:
				memory[outputAddress] = getArgValue(op.arg1Mode, memory, firstArg)
						+ getArgValue(op.arg2Mode, memory, secondArg);
				break;
			case MULTIPLY:
				memory[outputAddress] = getArgValue(op.arg1Mode, memory, firstArg)
						* getArgValue(op.arg2Mode, memory, secondArg);
				break;
			case STORE:
				// Hard coding 1 as input
				memory[firstArg] = 1;
				break;
			case OUTPUT:
				System.out.println(getArgValue(op.arg1Mode, memory, firstArg));
				outputs.add(getArgValue(op.arg1Mode, memory, firstArg));
				break;
			case EXIT:
				insturctionPointer = memory.length;
				break;
			default:
				break;
			}

			insturctionPointer += op.operation.getNumArgs();
		}

		return outputs;
	}

	private static int getArgValue(Mode mode, int[] memory, int value) {
		if (mode == Mode.IMMEDIATE) {
			return value;
		}

		return memory[value];
	}

}
