package com.mdgriffin.adventOfCode2019.day1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FuelCounterUpperTest {

	List<Long> providedValues = new ArrayList<Long>(Arrays.asList(116860L, 130144L, 79347L, 120725L, 137692L, 139037L,
			72089L, 133224L, 102168L, 100679L, 122298L, 132969L, 109196L, 85162L, 66316L, 68461L, 87608L, 108091L,
			71061L, 85477L, 97748L, 105766L, 141169L, 94553L, 98932L, 134376L, 69822L, 104858L, 102584L, 59682L, 52092L,
			105784L, 144100L, 83695L, 130436L, 105447L, 133102L, 82770L, 68684L, 103878L, 136774L, 71462L, 96828L,
			74743L, 127523L, 124145L, 148013L, 103862L, 80052L, 74095L, 130394L, 125589L, 137576L, 111299L, 69311L,
			63144L, 119014L, 136084L, 94348L, 109511L, 102493L, 117791L, 76202L, 138442L, 72724L, 104579L, 80285L,
			56847L, 145460L, 132255L, 58264L, 60460L, 98995L, 63343L, 51207L, 133619L, 126155L, 130707L, 105010L,
			104589L, 128527L, 67715L, 71823L, 82517L, 74115L, 135483L, 82230L, 127410L, 128969L, 140127L, 59133L,
			145973L, 109430L, 103608L, 113203L, 133402L, 123971L, 71761L, 114178L, 52940L));

	@Test
	public void whenCalculatingRequiredFuel_correctResult() {
		assertEquals(2, FuelCounterUpper.calculateRequiredFuel(12));
		assertEquals(2, FuelCounterUpper.calculateRequiredFuel(14));
		assertEquals(654, FuelCounterUpper.calculateRequiredFuel(1969));
		assertEquals(33583, FuelCounterUpper.calculateRequiredFuel(100756));
	}

	@Test
	public void whenCalculatingRequiredFuelForAllModules_correctSum() {
		List<Long> exampleModuleMasses = new ArrayList<Long>(Arrays.asList(12L, 14L, 1969L, 100756L));
		assertEquals(34241, FuelCounterUpper.calculateRequiredFuelForAllModules(exampleModuleMasses));
		assertEquals(3415695, FuelCounterUpper.calculateRequiredFuelForAllModules(providedValues));
	}

	@Test
	public void dfg() {
		assertEquals(2, FuelCounterUpper.calculateRequireFuelRecurse(14));
		assertEquals(966, FuelCounterUpper.calculateRequireFuelRecurse(1969));
		assertEquals(50346, FuelCounterUpper.calculateRequireFuelRecurse(100756));
	}

	@Test
	public void whenCalculatingRequiredFuelRecurseForAllModules_correctSum() {
		assertEquals(5120654, FuelCounterUpper.calculateRequiredFuelRecurseForAllModules(providedValues));
	}
}
