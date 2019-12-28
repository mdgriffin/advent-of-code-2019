package com.mdgriffin.adventOfCode2019.day8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class SpaceImageFormatTest {

	private static final String PROVIDED_INPUT = FileReader.readFile("/test-input/day8.txt");
	private static int IMAGE_WIDTH = 25;
	private static int IMAGE_HEIGHT = 6;
	private static SpaceImageFormat image = new SpaceImageFormat(IMAGE_WIDTH, IMAGE_HEIGHT, PROVIDED_INPUT);
	
	@Test
	public void dataLoaded() {
		assertTrue(PROVIDED_INPUT.length() > 0);
	}
	
	@Test
	public void canCreate() {
		assertEquals(100, image.layers.length);
		assertEquals(IMAGE_WIDTH * IMAGE_HEIGHT, image.layers[0].length);
	}
	
	@Test
	public void getIndexOfLayerWithFewestZeros() {
		assertEquals(6, image.getIndexOfLayerWithFewestZeros());
	}
	
	@Test
	public void getNumberOfOneDigitsMultipliedByTwoDigitsOnLayerWithLowestNumberOfZeroes() {
		assertEquals(1441, image.getNumberOfOneDigitsMultipliedByTwoDigitsOnLayerWithLowestNumberOfZeroes());
	}
	
	@Test
	@Ignore
	public void generateAndWriteImage() throws IOException {
		BufferedImage imageToWrite = image.getImage();
		
		SpaceImageFormat.writeImage(imageToWrite, "day-8.jpg⁩");
	}
	
	@Test
	public void drawImageAsText() throws IOException {
		String imageAsText = image.drawImageAsText();
		
		assertTrue(imageAsText.length() > 0);
		
		System.out.println(imageAsText);
	}
	
	@Test
	public void getImagePixels() {
		image = new SpaceImageFormat(2, 2, "0222112222120000");
		int[] pixels = image.getImagePixels();

		assertEquals(4, pixels.length);
		
		assertEquals(0, pixels[0]);
		assertEquals(1, pixels[1]);
		assertEquals(1, pixels[2]);
		assertEquals(0, pixels[3]);
	}
}
