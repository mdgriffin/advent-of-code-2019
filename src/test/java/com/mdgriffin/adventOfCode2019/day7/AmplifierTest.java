package com.mdgriffin.adventOfCode2019.day7;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class AmplifierTest {

	private static final int[] PROVIDED_INPUT = FileReader.readCSVFile("/test-input/day7.csv");

	private static final int[] SAMPLE_INPUT_1 = new int[] { 3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0,
			0 };

	private static final int[] SAMPLE_INPUT_2 = new int[] { 3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5,
			23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0 };

	private static final int[] SAMPLE_INPUT_3 = new int[] { 3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31,
			0, 33, 1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0 };

	private static final int[] FEEDBACK_INPUT_1 = new int[] { 3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27,
			26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5 };

	private static final int[] FEEDBACK_INPUT_2 = new int[] { 3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54,
			5, 55, 1005, 55, 26, 1001, 54, -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53,
			55, 53, 4, 53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10 };

	@Test
	public void providedInputsInitialized() {
		assertEquals(515, PROVIDED_INPUT.length);
	}

	@Test
	public void getHighest_withProvidedInput() {
		assertEquals(17440, Amplifier.getHighest(PROVIDED_INPUT));
	}

	@Test
	public void getHighest_withSampleInputs() {
		assertEquals(43210, Amplifier.getHighest(SAMPLE_INPUT_1));
		assertEquals(54321, Amplifier.getHighest(SAMPLE_INPUT_2));
		assertEquals(65210, Amplifier.getHighest(SAMPLE_INPUT_3));
	}

	@Test
	public void getAmplifierOutputSignal() {
		assertEquals(43210, Amplifier.getAmplifierOutputSignal(0, SAMPLE_INPUT_1, new Integer[] { 4, 3, 2, 1, 0 }));
		assertEquals(54321, Amplifier.getAmplifierOutputSignal(0, SAMPLE_INPUT_2, new Integer[] { 0, 1, 2, 3, 4 }));
		assertEquals(65210, Amplifier.getAmplifierOutputSignal(0, SAMPLE_INPUT_3, new Integer[] { 1, 0, 4, 3, 2 }));
	}

	@Test
	public void whenGettingInputs_ordrCorrect() {
		LinkedList<Integer> inputs = Amplifier.getInputs(1, 2, 3);

		assertEquals(3, inputs.size());
		assertEquals(1, (int) inputs.removeFirst());
		assertEquals(2, (int) inputs.removeFirst());
		assertEquals(3, (int) inputs.removeFirst());
	}

	@Test
	public void generatePermutations() {
		AtomicInteger numPermutations = new AtomicInteger();

		Amplifier.doForEachPermutation(3, new Integer[] { 0, 1, 2 }, (a) -> {
			numPermutations.incrementAndGet();
		});

		assertEquals(6, numPermutations.get());
	}

	@Test
	public void getHighestIterative() {
		assertEquals(139629729, Amplifier.getHighestIterative(FEEDBACK_INPUT_1));
		assertEquals(18216, Amplifier.getHighestIterative(FEEDBACK_INPUT_2));
	}

	@Test
	public void getHighestIterative_withProvidedInput() {
		assertEquals(27561242, Amplifier.getHighestIterative(PROVIDED_INPUT));
	}

	@Test
	public void getAmplifierOutputSignalIterative() {
		assertEquals(139629729,
				Amplifier.getAmplifierOutputSignalIterative(0, FEEDBACK_INPUT_1, new Integer[] { 9, 8, 7, 6, 5 }));

		assertEquals(18216,
				Amplifier.getAmplifierOutputSignalIterative(0, FEEDBACK_INPUT_2, new Integer[] { 9, 7, 8, 5, 6 }));
	}

}
