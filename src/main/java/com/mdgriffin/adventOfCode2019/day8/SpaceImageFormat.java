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
	
	public int getNumOfOccurencesInLayer(int layerIndex, int num) {
		int numOccurences = 0;
		
		
		for (int i = 0; i < layers[layerIndex].length; i++) {
			if (layers[layerIndex][i] == num) {
				numOccurences++;
			}
		}
		
		return numOccurences;
	}
	
	
	public int getIndexOfLayerWithFewestZeros () {
		int lowestNumberOfZeros = Integer.MAX_VALUE;
		int indexOfLowestNumberOfZeros = -1;
		
		for (int i = 0; i < layers.length; i++) {
			int numZeroes = getNumOfOccurencesInLayer(i, 0);
			
			if (numZeroes < lowestNumberOfZeros) {
				lowestNumberOfZeros = numZeroes;
				indexOfLowestNumberOfZeros = i;
			}
		}
		
		return indexOfLowestNumberOfZeros;
	}
	
	public int getNumberOfOneDigitsMultipliedByTwoDigitsOnLayerWithLowestNumberOfZeroes() {
		int layerWithLowestNumbeOfZeroes = getIndexOfLayerWithFewestZeros();
		int numOfOnes = getNumOfOccurencesInLayer(layerWithLowestNumbeOfZeroes,1);
		int numOfTwos= getNumOfOccurencesInLayer(layerWithLowestNumbeOfZeroes,2);
		
		return numOfOnes * numOfTwos;
	}

}
