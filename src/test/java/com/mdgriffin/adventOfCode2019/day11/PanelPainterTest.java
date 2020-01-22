package com.mdgriffin.adventOfCode2019.day11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class PanelPainterTest {
	
	private static final long[] PROVIDED_INPUT = FileReader.readCSVFile("/test-input/day11.csv");

	@Test
	public void countNumberOfPanelsPainted_withProvidedInput () {
		
		PanelPainter panelPaner = new PanelPainter(200, 200, PROVIDED_INPUT, 0);
		
		int numPanels = panelPaner.countNumberOfPanelsPainted();
		
		assertEquals(2883, numPanels);
		
	}
	
	@Test
	public void countNumberOfPanelsPainted_withProvidedInput_forPartTwo () {
		
		PanelPainter panelPaner = new PanelPainter(120, 120, PROVIDED_INPUT, 1);
		
		int numPanels = panelPaner.countNumberOfPanelsPainted();
		
		assertEquals(249, numPanels);
		
		System.out.println(panelPaner.printRegistration());
	}
	
}
