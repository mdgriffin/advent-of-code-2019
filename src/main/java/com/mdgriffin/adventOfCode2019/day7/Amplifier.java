package com.mdgriffin.adventOfCode2019.day7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.mdgriffin.adventOfCode2019.computer.IntCodeComputer;

public class Amplifier {

	public static long getHighest(long[] memory) {
		final List<Long> outputs = new ArrayList<Long>();
		Long[] phaseSettings = new Long[] { 0l, 1l, 2l, 3l, 4l };

		doForEachPermutation(phaseSettings.length, phaseSettings, (currentPhaseSettings) -> {
			outputs.add(getAmplifierOutputSignal(0, memory, currentPhaseSettings));
		});

		return outputs.stream().mapToLong(Long::longValue).max().getAsLong();
	}

	public static long getHighestIterative(long[] memory) {
		final List<Long> outputs = new ArrayList<Long>();
		Long[] phaseSettings = new Long[] { 5l, 6l, 7l, 8l, 9l };

		doForEachPermutation(phaseSettings.length, phaseSettings, (currentPhaseSettings) -> {
			outputs.add(getAmplifierOutputSignalIterative(0, memory, currentPhaseSettings));
		});

		return outputs.stream().mapToLong(Long::longValue).max().getAsLong();
	}

	public static long getAmplifierOutputSignalIterative(int initialInput, long[] memory, Long[] phaseSettings) {
		long output = initialInput;
		List<IntCodeComputer> amplifiers = getAmplifiers(memory, 5);
		boolean firstRun = true;

		while (allRunning(amplifiers)) {
			for (int i = 0; i < amplifiers.size(); i++) {
				if (firstRun) {
					amplifiers.get(i).addInput(phaseSettings[i]);
				}

				amplifiers.get(i).addInput(output);

				LinkedList<Long> outputs = amplifiers.get(i).runToNextOutput();

				if (outputs.size() > 0) {
					output = outputs.pop();
				}
			}
			firstRun = false;
		}

		return output;
	}

	private static boolean allRunning(List<IntCodeComputer> computers) {
		return computers.stream().allMatch(computer -> computer.isRunning);
	}

	public static long getAmplifierOutputSignal(int initialInput, long[] memory, Long[] longs) {
		long output = initialInput;
		List<IntCodeComputer> amplifiers = getAmplifiers(memory, 5);

		for (int i = 0; i < amplifiers.size(); i++) {
			output = amplifiers.get(i).run(getInputs(longs[i], output)).pop();
		}

		return output;
	}

	private static List<IntCodeComputer> getAmplifiers(long[] memory, int numComputers) {
		List<IntCodeComputer> intCodeComputers = new ArrayList<IntCodeComputer>();

		for (int i = 0; i < numComputers; i++) {
			intCodeComputers.add(new IntCodeComputer(memory));
		}

		return intCodeComputers;
	}

	public static LinkedList<Long> getInputs(long... inputsToAdd) {
		LinkedList<Long> inputs = new LinkedList<Long>();

		for (long input : inputsToAdd) {
			inputs.addLast(input);
		}

		return inputs;
	}

	public static void doForEachPermutation(int k, Long[] arr, Consumer<Long[]> consumer) {
		if (k == 1) {
			consumer.accept(arr);
		} else {
			for (int i = 0; i < k; i++) {
				doForEachPermutation(k - 1, arr, consumer);

				if (k % 2 == 0) {
					long temp = arr[i];
					arr[i] = arr[k - 1];
					arr[k - 1] = temp;
				} else {
					long temp = arr[0];
					arr[0] = arr[k - 1];
					arr[k - 1] = temp;

				}
			}
		}
	}

}
