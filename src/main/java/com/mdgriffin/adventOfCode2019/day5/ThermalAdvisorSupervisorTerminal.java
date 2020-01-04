package com.mdgriffin.adventOfCode2019.day5;

import java.util.LinkedList;

import com.mdgriffin.adventOfCode2019.computer.IntCodeComputer;

public class ThermalAdvisorSupervisorTerminal {

	public static LinkedList<Integer> compute(int[] memory, LinkedList<Integer> inputs) {
		IntCodeComputer computer = new IntCodeComputer(memory);
		return computer.run(inputs);
	}

}
