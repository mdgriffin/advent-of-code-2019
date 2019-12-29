package com.mdgriffin.adventOfCode2019.day5;

import java.util.LinkedList;

public class ThermalAdvisorSupervisorTerminal {

	public static LinkedList<Integer> compute(int[] memory, LinkedList<Integer> inputs) {
		int instructionPointer = 0;
		LinkedList<Integer> outputs = new LinkedList<Integer>();

		while (instructionPointer < memory.length) {
			int currentInstruction = memory[instructionPointer];
			OpParams op = OpParams.getByInstruction(currentInstruction);
			int args[] = new int[4];

			for (int i = 0; i < op.operation.getNumArgs() - 1; i++) {
				args[i] = memory[instructionPointer + (i + 1)];
			}

			switch (op.operation) {
			case SUM:
				memory[args[2]] = getArgValue(op.arg1Mode, memory, args[0]) + getArgValue(op.arg2Mode, memory, args[1]);
				break;
			case MULTIPLY:
				memory[args[2]] = getArgValue(op.arg1Mode, memory, args[0]) * getArgValue(op.arg2Mode, memory, args[1]);
				break;
			case STORE:
				memory[args[0]] = inputs.removeFirst();
				break;
			case OUTPUT:
				System.out.println(getArgValue(op.arg1Mode, memory, args[0]));
				outputs.push(getArgValue(op.arg1Mode, memory, args[0]));
				break;
			case EXIT:
				instructionPointer = memory.length;
				break;
			case JUMP_IF_TRUE:
				instructionPointer = getArgValue(op.arg1Mode, memory, args[0]) != 0
						? getArgValue(op.arg2Mode, memory, args[1])
						: instructionPointer + op.operation.getNumArgs();
				continue;
			case JUMP_IF_FALSE:
				instructionPointer = getArgValue(op.arg1Mode, memory, args[0]) == 0
						? getArgValue(op.arg2Mode, memory, args[1])
						: instructionPointer + op.operation.getNumArgs();
				continue;
			case LESS_THAN:
				memory[args[2]] = getArgValue(op.arg1Mode, memory, args[0]) < getArgValue(op.arg2Mode, memory, args[1])
						? 1
						: 0;
				break;
			case EQUALS:
				memory[args[2]] = getArgValue(op.arg1Mode, memory, args[0]) == getArgValue(op.arg2Mode, memory, args[1])
						? 1
						: 0;
				break;
			default:
				break;
			}

			instructionPointer += op.operation.getNumArgs();
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
