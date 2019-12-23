package com.mdgriffin.adventOfCode2019.day8;

import java.util.Arrays;

public class SpaceImageFormat {
	
	public int[][] layers;
	private int width;
	private int height;
	
	
	public SpaceImageFormat (int width,int height, String encodedImage) {
		this.width = width;
		this.height = height;
		
		processImage(encodedImage);
	}
	
	private void processImage(String encodedImage) {
		int[] imageInts = Arrays.stream(encodedImage.replace("\n", "").split(""))
			    .mapToInt(Integer::parseInt)
			    .toArray();
		int numBitsPerImage = width * height;
		layers = new int[imageInts.length / numBitsPerImage][numBitsPerImage];
		
		for (int i = 0; i < layers.length; i++) {
			int[] currentLayer = new int[numBitsPerImage];
			
			for (int j = 0; j < numBitsPerImage; j++) {
				currentLayer[j] = imageInts[(i * numBitsPerImage) + j];
				
			}
			
			layers[i] = currentLayer;
		}
	}

}
