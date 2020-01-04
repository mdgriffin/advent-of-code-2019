package com.mdgriffin.adventOfCode2019.day2;

import java.util.LinkedList;

import com.mdgriffin.adventOfCode2019.computer.IntCodeComputer;

public class ProgramAlarm {

	public static int[] compute(int[] memory) {
		IntCodeComputer computer = new IntCodeComputer(memory);
		computer.run(new LinkedList<Integer>());

		return computer.getMemory();
	}

}
