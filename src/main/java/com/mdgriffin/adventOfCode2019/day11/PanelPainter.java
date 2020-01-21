package com.mdgriffin.adventOfCode2019.day11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.mdgriffin.adventOfCode2019.computer.IntCodeComputer;
import com.mdgriffin.adventOfCode2019.day3.XY;

public class PanelPainter {
	
	private XY currentPosition;
	private int width;
	private int height;
	private int[][] map;
	
	private static final int MAP_INITIAL_VALUE = 0;
	private long[] memory;
	private int currentDirection = 0;
	private boolean[][] panelWasPainted;
	
	
	public PanelPainter(int width, int height, long[] memory) {
		this.width = width;
		this.height = height;
		this.memory = memory;
		initializeMap();
		currentPosition = new XY(this.height / 2, this.width  / 2);
	}
	
	private void initializeMap() {
		map = new int[this.height][this.width];
		panelWasPainted = new boolean[this.height][this.width];
		
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width ; x++) {
				map[y][x] = MAP_INITIAL_VALUE;
			}
		}
	}

	public int countNumberOfPanelsPainted () {
		IntCodeComputer computer = new IntCodeComputer(memory);
		
		// will provide two outputs, direction and colour
		
		// TODO: Need a 3d array initialized to 0 representing white panels
		// TODO: This will need to be updated as the program executes
		
		while (computer.isRunning) {
			computer.addInput((long) getColourOfCurrentPosition());
			LinkedList<Long> firstOutput = computer.runToNextOutput();
			if (computer.isRunning) {
				long colourToPaint = firstOutput.pop();
				
				LinkedList<Long> secondOutput = computer.runToNextOutput();
				long directionToTurn = secondOutput.pop();
				
				//if (colourToPaint != getColourOfCurrentPosition()) {
				paintPanel((int) colourToPaint);
				//}
				
				changeDirection((int) directionToTurn);
				moveForward();
			}
			
		}
		
		return getNumPanelsPained();
	}
	
	private int getNumPanelsPained() {
		// TODO Auto-generated method stub
//		Arrays.stream(panelWasPainted).mapToInt(e -> Arrays.stream(e).mapToInt(a -> a == true? 1 : 0).sum()).sum();
		int count = 0;
				
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (panelWasPainted[i][j]) {
					count++;
				}
			}
		}
		
		return count;
	}

	private int getColourOfCurrentPosition() {
		return map[currentPosition.y][currentPosition.x];
	}
	
	private void paintPanel(int colourToPaint) {
		map[currentPosition.y][currentPosition.x] = colourToPaint;
		panelWasPainted[currentPosition.y][currentPosition.x] = true;
	}

	private void moveForward() {
		Direction directionToMove = Direction.getDirectionByCode(currentDirection);
		
		switch(directionToMove) {
		case UP:
			currentPosition.y--;
			break;
		case RIGHT:
			currentPosition.x++;
			break;
		case DOWN:
			currentPosition.y++;
			break;
		case LEFT:
			currentPosition.x--;
			break;
		default:
			break;
		}
		
		if (currentPosition.x < 0 || currentPosition.x >= this.width || currentPosition.y < 0 || currentPosition.y >= this.height) {
			throw new IllegalStateException("Current position (" + currentPosition + ") is out of bounds");
		}
	}

	private void changeDirection (int direction) {
		if (direction != 0 && direction != 1) {
			throw new IllegalArgumentException("direction must be 0 or 1");
		}
		
		if (direction == 0) {
			this.currentDirection--;
			
			if (this.currentDirection < 0) {
				this.currentDirection = 3;
			}
		} else if (direction == 1) {
			this.currentDirection++;
			
			if (this.currentDirection > 3) {
				this.currentDirection = 0;
			}
		}
		
	}
	
	private static enum Direction {
		UP(0), DOWN(2), LEFT(3), RIGHT(1);
		
		private int directionCode;
		
		Direction(int directionCode) {
			this.directionCode = directionCode;
		}
		
		public static Direction getDirectionByCode (int code) {
			Optional<Direction> matchingDirection = Arrays.stream(Direction.values()).filter(dir -> dir.getDirectionCode() == code).findFirst();
			
			if (!matchingDirection.isPresent()) {
				throw new RuntimeException("No matching direction for direction code");
			}
			
			return matchingDirection.get();
		}
		
		public int getDirectionCode() {
			return directionCode;
		}
	}

}
