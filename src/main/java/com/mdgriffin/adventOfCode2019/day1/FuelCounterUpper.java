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

	public static long calculateRequireFuelRecurse(long moduleMass) {
		long result = moduleMass / 3 - 2;

		if (result > 0) {
			result += calculateRequireFuelRecurse(result);
		} else if (result < 0) {
			return 0;
		}
		return result;
	}

	public static long calculateRequiredFuelRecurseForAllModules(List<Long> moduleMasses) {
		return moduleMasses.stream().map(moduleMass -> calculateRequireFuelRecurse(moduleMass))
				.mapToLong(Long::longValue).sum();
	}
}
