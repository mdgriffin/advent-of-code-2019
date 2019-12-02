package com.mdgriffin.adventOfCode2019.day1;

import java.util.List;

public class FuelCounterUpper {

	public static long calculateRequiredFuel(long moduleMass) {
		return moduleMass / 3 - 2;
	}

	public static long calculateRequiredFuelForAllModules(List<Long> moduleMasses) {
		return moduleMasses.stream().map(moduleMass -> calculateRequiredFuel(moduleMass)).mapToLong(Long::longValue)
				.sum();
	}
}
