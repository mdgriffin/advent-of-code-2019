package com.mdgriffin.adventOfCode2019.day7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.mdgriffin.adventOfCode2019.computer.IntCodeComputer;

public class Amplifier {

	public static int getHighest(int[] memory) {
		final List<Integer> outputs = new ArrayList<Integer>();
		Integer[] phaseSettings = new Integer[] { 0, 1, 2, 3, 4 };

		doForEachPermutation(phaseSettings.length, phaseSettings, (currentPhaseSettings) -> {
			outputs.add(getAmplifierOutputSignal(0, memory, currentPhaseSettings));
		});

		return outputs.stream().mapToInt(Integer::intValue).max().getAsInt();
	}

	public static int getHighestFromFeedbackLoop() {
		Integer[] phaseSettings = new Integer[] { 5, 6, 7, 8, 9 };

		// keep calling each

		return 1;
	}

	public static void doForEachPermutation(int k, Integer[] arr, Consumer<Integer[]> consumer) {
		if (k == 1) {
			consumer.accept(arr);
		} else {
			for (int i = 0; i < k; i++) {
				doForEachPermutation(k - 1, arr, consumer);

				if (k % 2 == 0) {
					int temp = arr[i];
					arr[i] = arr[k - 1];
					arr[k - 1] = temp;
				} else {
					int temp = arr[0];
					arr[0] = arr[k - 1];
					arr[k - 1] = temp;

				}
			}
		}
	}

	public static int getAmplifierOutputSignal(int initialInput, int[] memory, Integer[] phaseSettings) {
		int output = initialInput;

		List<IntCodeComputer> amplifiers = getAmplifiers(memory, 5);

		for (int i = 0; i < amplifiers.size(); i++) {
			output = amplifiers.get(i).run(getInputs(phaseSettings[i], output)).pop();
		}

		return output;
	}

	private static List<IntCodeComputer> getAmplifiers(int[] memory, int numComputers) {
		List<IntCodeComputer> intCodeComputers = new ArrayList<IntCodeComputer>();

		for (int i = 0; i < numComputers; i++) {
			intCodeComputers.add(new IntCodeComputer(memory));
		}

		return intCodeComputers;

	}

	public static LinkedList<Integer> getInputs(int... inputsToAdd) {
		LinkedList<Integer> inputs = new LinkedList<Integer>();

		for (int input : inputsToAdd) {
			inputs.addLast(input);
		}

		return inputs;
	}

}
