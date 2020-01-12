package com.mdgriffin.adventOfCode2019.day9;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class SensorBoostTest {

	private static long[] PROVIDED_INPUT = FileReader.readCSVFile("/test-input/day9.csv");

	private static long[] EXAMPLE_INPUT_1 = new long[] { 109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006,
			101, 0, 99 };

	private static long[] EXAMPLE_INPUT_2 = new long[] { 1102, 34915192, 34915192, 7, 4, 7, 99, 0 };

	private static long[] EXAMPLE_INPUT_3 = new long[] { 104, 1125899906842624l, 99 };

	@Test
	public void runBoosters_withProvidedInput() {
		List<Long> output = SensorBoost.runBoosters(PROVIDED_INPUT, Arrays.asList(1l));

		assertEquals(1, output.size());
		assertEquals(3518157894l, (long) output.get(0));
	}

	@Test
	public void runBoosters_withExampleInput4() {
		List<Long> output = SensorBoost.runBoosters(new long[] { 109, -1, 4, 1, 99 }, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(-1l, (long) output.get(0));

		output = SensorBoost.runBoosters(new long[] { 109, -1, 104, 1, 99 }, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(1l, (long) output.get(0));

		output = SensorBoost.runBoosters(new long[] { 109, -1, 204, 1, 99 }, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(109l, (long) output.get(0));

		output = SensorBoost.runBoosters(new long[] { 109, 1, 9, 2, 204, -6, 99 }, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(204l, (long) output.get(0));

		output = SensorBoost.runBoosters(new long[] { 109, 1, 109, 9, 204, -6, 99 }, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(204l, (long) output.get(0));

		output = SensorBoost.runBoosters(new long[] { 109, 1, 209, -1, 204, -106, 99 }, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(204l, (long) output.get(0));

		output = SensorBoost.runBoosters(new long[] { 109, 1, 3, 3, 204, 2, 99 }, Arrays.asList(106l));

		assertEquals(1, output.size());
		assertEquals(106l, (long) output.get(0));

		output = SensorBoost.runBoosters(new long[] { 109, 1, 203, 2, 204, 2, 99 }, Arrays.asList(106l));

		assertEquals(1, output.size());
		assertEquals(106l, (long) output.get(0));
	}

	@Test
	public void runBoosters_withExampleInput1() {
		List<Long> output = SensorBoost.runBoosters(EXAMPLE_INPUT_1, Arrays.asList());

		assertEquals(EXAMPLE_INPUT_1.length, output.size());
	}

	@Test
	public void runBoosters_withExampleInput2() {
		List<Long> output = SensorBoost.runBoosters(EXAMPLE_INPUT_2, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(1219070632396864l, (long) output.get(0));
	}

	@Test
	public void runBoosters_withExampleInput3() {
		List<Long> output = SensorBoost.runBoosters(EXAMPLE_INPUT_3, Arrays.asList());

		assertEquals(1, output.size());
		assertEquals(1125899906842624l, (long) output.get(0));
	}
}
