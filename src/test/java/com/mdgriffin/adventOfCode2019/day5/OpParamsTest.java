package com.mdgriffin.adventOfCode2019.day5;

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

	}

}
