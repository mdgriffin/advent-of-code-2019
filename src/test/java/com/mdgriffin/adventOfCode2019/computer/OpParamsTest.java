package com.mdgriffin.adventOfCode2019.computer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OpParamsTest {

	@Test
	public void getOpParamsByInstruction() {
		OpParams op = OpParams.getByInstruction(1002);

		assertEquals(Mode.POSITION, op.arg1Mode);
		assertEquals(Mode.IMMEDIATE, op.arg2Mode);
		assertEquals(Mode.POSITION, op.arg3Mode);
		assertEquals(Operation.MULTIPLY, op.operation);

		op = OpParams.getByInstruction(11099);

		assertEquals(Mode.POSITION, op.arg1Mode);
		assertEquals(Mode.IMMEDIATE, op.arg2Mode);
		assertEquals(Mode.IMMEDIATE, op.arg3Mode);
		assertEquals(Operation.EXIT, op.operation);

		op = OpParams.getByInstruction(99);

		assertEquals(Mode.POSITION, op.arg1Mode);
		assertEquals(Mode.POSITION, op.arg2Mode);
		assertEquals(Mode.POSITION, op.arg3Mode);
		assertEquals(Operation.EXIT, op.operation);

		op = OpParams.getByInstruction(1);

		assertEquals(Mode.POSITION, op.arg1Mode);
		assertEquals(Mode.POSITION, op.arg2Mode);
		assertEquals(Mode.POSITION, op.arg3Mode);
		assertEquals(Operation.SUM, op.operation);

		op = OpParams.getByInstruction(104);

		assertEquals(Mode.IMMEDIATE, op.arg1Mode);
		assertEquals(Mode.POSITION, op.arg2Mode);
		assertEquals(Mode.POSITION, op.arg3Mode);
		assertEquals(Operation.OUTPUT, op.operation);

	}

}
