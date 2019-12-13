package com.mdgriffin.adventOfCode2019.day5;

public class OpParams {
	public Mode arg1Mode;
	public Mode arg2Mode;
	public Mode arg3Mode;
	public Operation operation;

	private OpParams() {
	}

	public static OpParams getByInstruction(int instruction) {
		String instructionAsString = Integer.toString(instruction);
		String operationCode = "";
		String params = "";

		if (instructionAsString.length() > 1) {
			operationCode = instructionAsString.substring(instructionAsString.length() - 2);
			params = instructionAsString.substring(0, instructionAsString.length() - 2);
		} else {
			operationCode = instructionAsString;
		}

		OpParams op = new OpParams();
		op.operation = Operation.getOperationByOpCode(Integer.parseInt(operationCode));

		if (params.length() > 0) {
			op.arg1Mode = params.length() > 0 ? Mode.getByCode(params.charAt(params.length() - 1) - '0')
					: Mode.POSITION;
			op.arg2Mode = params.length() > 1 ? Mode.getByCode(params.charAt(params.length() - 2) - '0')
					: Mode.POSITION;
			op.arg3Mode = params.length() > 2 ? Mode.getByCode(params.charAt(params.length() - 3) - '0')
					: Mode.POSITION;
		} else {
			op.arg1Mode = Mode.POSITION;
			op.arg2Mode = Mode.POSITION;
			op.arg3Mode = Mode.POSITION;
		}

		return op;
	}

}