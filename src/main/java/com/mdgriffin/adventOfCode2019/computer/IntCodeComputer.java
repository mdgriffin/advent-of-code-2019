package com.mdgriffin.adventOfCode2019.computer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Predicate;

public class IntCodeComputer {

	private long[] memory;
	private long instructionPointer = 0;
	private long relativeBase = 0;
	public boolean isRunning = true;
	private LinkedList<Long> inputs = new LinkedList<Long>();
	private LinkedList<Long> outputs = new LinkedList<Long>();

	public IntCodeComputer(long[] memory) {
		this.memory = memory.clone();
	}

	private LinkedList<Long> run(Predicate<Integer> runCondition) {
		while (runCondition.test(outputs.size()) && isRunning && instructionPointer < memory.length) {
			int currentInstruction = (int) memory[(int) instructionPointer];
			OpParams op = OpParams.getByInstruction(currentInstruction);
			long[] args = new long[4];

			for (int i = 0; i < op.operation.getNumArgs() - 1; i++) {
				args[i] = readFromMemory(instructionPointer + (i + 1));
			}

			switch (op.operation) {
			case SUM:
				writeToMemory(getLiteralArgValue(op.arg3Mode, args[2]),
						getArgValue(op.arg1Mode, args[0]) + getArgValue(op.arg2Mode, args[1]));
				break;
			case MULTIPLY:
				writeToMemory(getLiteralArgValue(op.arg3Mode, args[2]),
						getArgValue(op.arg1Mode, args[0]) * getArgValue(op.arg2Mode, args[1]));
				break;
			case STORE:
				writeToMemory(getLiteralArgValue(op.arg1Mode, args[0]), inputs.removeFirst());
				break;
			case OUTPUT:
				System.out.println(getArgValue(op.arg1Mode, args[0]));
				outputs.push(getArgValue(op.arg1Mode, args[0]));
				break;
			case EXIT:
				this.isRunning = false;
				break;
			case JUMP_IF_TRUE:
				instructionPointer = getArgValue(op.arg1Mode, args[0]) != 0 ? getArgValue(op.arg2Mode, args[1])
						: instructionPointer + op.operation.getNumArgs();
				continue;
			case JUMP_IF_FALSE:
				instructionPointer = getArgValue(op.arg1Mode, args[0]) == 0 ? getArgValue(op.arg2Mode, args[1])
						: instructionPointer + op.operation.getNumArgs();
				continue;
			case LESS_THAN:
				writeToMemory(getLiteralArgValue(op.arg3Mode, args[2]),
						getArgValue(op.arg1Mode, args[0]) < getArgValue(op.arg2Mode, args[1]) ? 1 : 0);
				break;
			case EQUALS:
				writeToMemory(getLiteralArgValue(op.arg3Mode, args[2]),
						getArgValue(op.arg1Mode, args[0]) == getArgValue(op.arg2Mode, args[1]) ? 1 : 0);
				break;
			case ADJUST_RELATIVE_BASE:
				relativeBase += getArgValue(op.arg1Mode, args[0]);
				break;
			default:
				break;
			}

			instructionPointer += op.operation.getNumArgs();
		}

		return outputs;
	}

	public LinkedList<Long> run(LinkedList<Long> inputs) {
		this.inputs.addAll(inputs);

		return run(numOutputs -> {
			return true;
		});
	}

	public LinkedList<Long> runToNextOutput() {
		return run(numOutputs -> {
			return numOutputs == 0;
		});

	}

	public long[] getMemory() {
		return memory;
	}

	public void addInput(long input) {
		this.inputs.addLast(input);
	}

	private long getArgValue(Mode mode, long argument) {
		if (mode.equals(Mode.IMMEDIATE)) {
			return argument;
		} else if (mode.equals(Mode.RELATIVE)) {
			return readFromMemory(this.relativeBase + argument);
		}

		return readFromMemory((int) argument);
	}

	private long getLiteralArgValue(Mode mode, long argument) {
		if (mode.equals(Mode.RELATIVE)) {
			return this.relativeBase + argument;
		}

		return argument;
	}

	private void writeToMemory(long memoryLocation, long value) {
		if (this.memory.length <= memoryLocation + 1) {
			long[] increasedMemory = new long[(int) (memoryLocation + 1)];
			increasedMemory = Arrays.copyOf(this.memory, (int) (memoryLocation + 1));
			this.memory = increasedMemory;
		}

		this.memory[(int) memoryLocation] = value;
	}

	private long readFromMemory(long memoryLocation) {
		return this.memory.length > memoryLocation ? this.memory[(int) memoryLocation] : 0;
	}

}
