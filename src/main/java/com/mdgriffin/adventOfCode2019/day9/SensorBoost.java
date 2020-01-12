package com.mdgriffin.adventOfCode2019.day9;

import java.util.LinkedList;
import java.util.List;

import com.mdgriffin.adventOfCode2019.computer.IntCodeComputer;

public class SensorBoost {

	public static List<Long> runBoosters(long[] memory, List<Long> inputs) {
		IntCodeComputer computer = new IntCodeComputer(memory);

		inputs.forEach(input -> computer.addInput(input));

		return computer.run(new LinkedList<Long>());
	}
}
