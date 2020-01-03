package com.mdgriffin.adventOfCode2019.computer;

import java.util.LinkedList;

public class IntCodeComputer {

	private int[] memory;
	private int instructionPointer = 0;
	public boolean isRunning = true;
	private LinkedList<Integer> inputs = new LinkedList<Integer>();
	private LinkedList<Integer> outputs = new LinkedList<Integer>();

	public IntCodeComputer(int[] memory) {
		this.memory = memory.clone();
	}

	public LinkedList<Integer> run(LinkedList<Integer> inputs) {
		while (isRunning && instructionPointer < memory.length) {
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
				this.isRunning = false;
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

		this.isRunning = false;

		return outputs;
	}

	// TODO: Merge with run method by providing a predicate
	public LinkedList<Integer> runToNextOutput() {
		while (outputs.size() == 0 && isRunning && instructionPointer < memory.length) {
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
				outputs.push(getArgValue(op.arg1Mode, memory, args[0]));
				break;
			case EXIT:
				this.isRunning = false;
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

	public void addInput(int input) {
		this.inputs.addLast(input);
	}

	private static int getArgValue(Mode mode, int[] memory, int value) {
		if (mode == Mode.IMMEDIATE) {
			return value;
		}

		return memory[value];
	}

}
