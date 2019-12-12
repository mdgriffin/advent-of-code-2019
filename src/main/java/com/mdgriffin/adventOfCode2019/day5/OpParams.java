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
		String params = instructionAsString.substring(0, instructionAsString.length() - 2);
		String operationCode = instructionAsString.substring(instructionAsString.length() - 2);

		OpParams op = new OpParams();
		op.operation = Operation.getOperationByOpCode(Integer.parseInt(operationCode));
		op.arg1Mode = Mode.getByCode(params.charAt(params.length() - 1) - '0');
		op.arg2Mode = Mode.getByCode(params.charAt(params.length() - 2) - '0');
		op.arg3Mode = params.length() > 2 ? Mode.getByCode(params.charAt(params.length() - 3) - '0') : Mode.POSITION;

		return op;
	}
}