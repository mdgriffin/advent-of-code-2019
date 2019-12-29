package com.mdgriffin.adventOfCode2019.day10;

import java.util.ArrayList;
import java.util.List;

public class AsteroidMonitoringStationLocator {
	
	int width;
	int height;
	MapPosition[][] map;
	
	public AsteroidMonitoringStationLocator (String asteroidDescriptor) {
		String[] lines = asteroidDescriptor.split("\n");
		
		height = lines.length;
		width = lines.length > 0 ? lines[0].length() : 0;

		generateAsteroidMap(lines);
		setNumVisibleOnAllMapPosition();
	}
	
	private void generateAsteroidMap(String[] lines) {
		map = new MapPosition[height][width];
		
		// TODO: Move this logic into helper method that takes a function and iterates on each element
		// TODO: Or could store as list rather than 2D array
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				boolean isAsteroid = lines[y].charAt(x) == '#';

				map[y][x] = new MapPosition(x, y, isAsteroid);
			}
		} 
	}
	
	private void setNumVisibleOnAllMapPosition () {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].numVisibleAsteroids = getNumVisibleAsteroidsFromSource(map[i][j]);
			}
		}
	}
	
	public MapPosition getBestObservationPosition () {
		MapPosition bestPosition = new MapPosition(0,0,true);
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].isAsteroid && map[i][j].numVisibleAsteroids >= bestPosition.numVisibleAsteroids) {
					bestPosition = map[i][j];
				}
			}
		}
		
		return bestPosition;
	}
	
	public int getNumVisibleAsteroidsFromSource (MapPosition sourcePosition) {
		int numVisible = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				MapPosition currentPosition = map[i][j];
				
				if (currentPosition.isAsteroid && !sourcePosition.equals(currentPosition)) {
					List<MapPosition> pointsOnPath = getPointsOnPath(sourcePosition, currentPosition);
					numVisible += (pointsOnPath.stream().allMatch(point -> !point.isAsteroid)? 1 : 0);
				}
			}
		}
		
		return numVisible;
	}
	
	public List<MapPosition> getPointsOnPath(MapPosition sourcePosition,  MapPosition destPosition) {
		List<MapPosition> points = new ArrayList<MapPosition>();
		
		// TODO: reduce to points in rectangle made by x,y coords of both points
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				MapPosition currentPosition = map[i][j];
				
				if (!currentPosition.equals(sourcePosition) && !currentPosition.equals(destPosition) && isOnPath(sourcePosition, destPosition, currentPosition)) {
					points.add(currentPosition);
				}
			}
		}
		
		return points;
	}
	
	public static boolean isOnPath(MapPosition a, MapPosition b, MapPosition c) {
		return areEqualToPrecision(cartiseanDistance(a, c) + cartiseanDistance(b, c), cartiseanDistance(a, b), 5);
	}
	
	public static boolean areEqualToPrecision(double a, double b, int precision) {
		   return Math.abs(a - b) <= Math.pow(10, -precision);
		}
	
	public static double cartiseanDistance(MapPosition a, MapPosition b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	
	public static String printNumVisibleAsteroisMap (MapPosition[][] map) {
		StringBuffer str = new StringBuffer();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				str.append(map[i][j].isAsteroid? String.format("%5d", map[i][j].numVisibleAsteroids) : "  .  ");
			}
			str.append("\n");
		}
		
		
		return str.toString();
	}
	
}
