package com.mdgriffin.adventOfCode2019.day2;

import java.util.LinkedList;

import com.mdgriffin.adventOfCode2019.computer.IntCodeComputer;

public class ProgramAlarm {

	public static long[] compute(long[] memory) {
		IntCodeComputer computer = new IntCodeComputer(memory);
		computer.run(new LinkedList<Long>());

		return computer.getMemory();
	}

}
