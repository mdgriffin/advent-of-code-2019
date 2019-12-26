package com.mdgriffin.adventOfCode2019.day8;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class SpaceImageFormat {
	
	public int[][] layers;
	private int width;
	private int height;
	
	public SpaceImageFormat (int width, int height, String encodedImage) {
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
	
	public int[] getImagePixels () {
		int numPixelsInImage = this.width * this.height;
		int[] imagePixels = new int[numPixelsInImage];
		boolean[] positionFilled = new boolean[numPixelsInImage];
		
		for (int i = 0; i < layers.length; i++) {
			for (int j = 0; j < layers[i].length; j++) {
				
				if (!positionFilled[j] && layers[i][j] != ImageColor.TRANSPARENT.colorCode) {
					positionFilled[j] = true;
					imagePixels[j] = layers[i][j];
				}
			}
		}
		
		return imagePixels;
	}
	
	public BufferedImage getImage () {
		BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		int[] imagePixels = getImagePixels();
		int currentPixel = 0;
		
		
	    for(int y = 0; y < this.height; y++) {
	    	for(int x = 0; x < this.width; x++) {
		        image.setRGB(x, y, ImageColor.getByColorCode(imagePixels[currentPixel++]).rgb);
		    }
		}
		
		return image;
	}
	
	public String drawImageAsText () {
		StringBuffer image = new StringBuffer();
		int[] imagePixels = getImagePixels();
		int currentPixel = 0;
		
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
		    	image.append(ImageColor.getByColorCode(imagePixels[currentPixel++]).asText);
		    }
		    image.append("\n");
		    
		}
		
		return image.toString();
	}
	
	public static void writeImage (BufferedImage image, String fileLocation) throws IOException {
		File outputfile = new File(fileLocation);
		ImageIO.write(image, "jpg", outputfile);
	}
	
	public static enum ImageColor {
		WHITE(1, Color.WHITE.getRGB(), '#'), BLACK(0, Color.BLACK.getRGB(), ' '), TRANSPARENT(2, Color.GREEN.getRGB(), ' ');
		
		private int colorCode;
		public int rgb;
		public char asText;
		
		ImageColor(int colorCode, int rgb, char textRepresentation) {
			this.colorCode = colorCode;
			this.rgb = rgb;
			this.asText = textRepresentation;
		}
		
		public static ImageColor getByColorCode (int colorCodeToMatch) {
			if (colorCodeToMatch == WHITE.colorCode) {
				return WHITE;
			} else if (colorCodeToMatch == BLACK.colorCode) {
				return BLACK;
			}
			
			return TRANSPARENT;
		}
	}

}
