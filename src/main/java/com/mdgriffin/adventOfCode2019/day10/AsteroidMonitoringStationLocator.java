package com.mdgriffin.adventOfCode2019.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		return getVisibleAsteroidsFromSource(sourcePosition).size();
	}
	
	public List<MapPosition> getVisibleAsteroidsFromSource (MapPosition sourcePosition) {
		List<MapPosition> visibleAsteroids = new ArrayList<MapPosition>();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				MapPosition currentPosition = map[i][j];
				
				if (currentPosition.isAsteroid && !sourcePosition.equals(currentPosition)) {
					List<MapPosition> pointsOnPath = getPointsOnPath(sourcePosition, currentPosition);
					if (pointsOnPath.stream().allMatch(point -> !point.isAsteroid)) {
						visibleAsteroids.add(currentPosition);
					}
				}
			}
		}
		
		return visibleAsteroids;
		
	}
	
	public List<MapPosition> getPointsOnPath(MapPosition sourcePosition,  MapPosition destPosition) {
		List<MapPosition> points = new ArrayList<MapPosition>();
		
		
		for (int i = (int) Math.min(sourcePosition.y, destPosition.y); i <= Math.max(sourcePosition.y,  destPosition.y); i++) {
			for (int j = (int) Math.min(sourcePosition.x, destPosition.x); j <= Math.max(sourcePosition.x, destPosition.x); j++) {
				MapPosition currentPosition = map[i][j];
				
				if (!currentPosition.equals(sourcePosition) && !currentPosition.equals(destPosition) && isOnPath(sourcePosition, destPosition, currentPosition)) {
					points.add(currentPosition);
				}
			}
		}
		
		return points;
	}
	
	public List<MapPosition> getAsteroidsOnPath(List<MapPosition> positions, MapPosition sourcePosition,  MapPosition destPosition) {
		return positions
			.stream()
			.filter(currentPosition -> currentPosition.isAsteroid && isOnPath(sourcePosition, destPosition, currentPosition, 4))
			.collect(Collectors.toList());
		
	}
	
	public List<MapPosition> pulveriseAsteroids() {
		MapPosition sourcePosition = getBestObservationPosition();
		List<MapPosition> visibleAsteroids = getVisibleAsteroidsFromSource(sourcePosition);
		List<MapPosition> destroyedAsteroids = new ArrayList<MapPosition>();
		List<MapPosition> edgePoints = getEdgePoints(sourcePosition);
		
		while(visibleAsteroids.size() > 0) {
			int numDestroyedBefore = destroyedAsteroids.size();
			
			for (MapPosition edgePoint : edgePoints) {
				List<MapPosition> positionsInFiringLine = getAsteroidsOnPath(visibleAsteroids, sourcePosition, edgePoint);
				positionsInFiringLine.forEach(position -> position.isAsteroid = false);
				destroyedAsteroids.addAll(positionsInFiringLine);
			}
			
			if (visibleAsteroids.size() != (destroyedAsteroids.size() - numDestroyedBefore)) {
				throw new IllegalStateException("All asteroids not destroyed on rotation");
			}
			
			visibleAsteroids = getVisibleAsteroidsFromSource(sourcePosition);
		}
		
		return destroyedAsteroids;
	}
	
	public List<MapPosition> getEdgePoints(MapPosition sourcePosition) {
		MapPosition currentPosition = new MapPosition(sourcePosition.x, 0);
		int segment = 0;
		double totalAngle = 0;
		double angleIncrement = 0.0005;
		List<MapPosition> edgePoints = new ArrayList<MapPosition>();
		
		while (totalAngle < 360) {
			double innerAngle = getInnerAngle(segment, totalAngle);
			double adjacentDistance = getAdjacentDistance(segment, sourcePosition);
			double oppositeDistance = getOppositeDistance(innerAngle, adjacentDistance);
			
			MapPosition nextPosition = getNextEdgePosition(segment, oppositeDistance, currentPosition);
			edgePoints.add(getNextEdgePosition(segment, oppositeDistance, currentPosition));
				
			segment = getNextSegmentAndSetCurrentPosition(segment, sourcePosition, currentPosition, nextPosition);
			totalAngle += angleIncrement;
		}
		
		return edgePoints;
		
	}
	
	private double getInnerAngle(int segment, double totalAngle) {
		if (segment == 0) {
			return totalAngle - 0;
		} else if (segment  == 1) {
			return 90 - totalAngle;
		} else if (segment == 2) {
			return totalAngle - 90;
		} else if (segment == 3) {
			return 180 - totalAngle;
		} else if (segment == 4) {
			return totalAngle - 180;
		} else if (segment  == 5) {
			return 270 - totalAngle;
		} else if (segment == 6) {
			return totalAngle - 270;
		} else if (segment == 7) {
			return 360 - totalAngle;
		}
		
		throw new IllegalArgumentException("Segment must be in range 0 to 7");
	}

	public MapPosition getNextEdgePosition (int segment, double oppositeDistance, MapPosition currentPosition) {
		if (segment == 0 || segment  == 3) {
			return new MapPosition(currentPosition.x + oppositeDistance, currentPosition.y);
		} else if (segment  == 1 || segment == 6) {
			return new MapPosition(currentPosition.x, currentPosition.y - oppositeDistance);
		} else if (segment  == 2 || segment  == 5) {
			return new MapPosition(currentPosition.x, currentPosition.y + oppositeDistance);
		}  else if (segment  == 4 || segment == 7) {
			return new MapPosition(currentPosition.x - oppositeDistance, currentPosition.y);
		}
		
		throw new IllegalArgumentException("Segment must be in range 0 to 7");
	}
	
	public int getNextSegmentAndSetCurrentPosition (int segment, MapPosition sourcePosition, MapPosition currentPosition, MapPosition nextPosition) {
		if (segment == 0 && nextPosition.x > this.width - 1) {
			currentPosition.x = this.width - 1;
			currentPosition.y = sourcePosition.y;
			return 1;
		} else if (segment == 1 && nextPosition.y > sourcePosition.y) {
			return 2;
		} else if (segment  == 2 && nextPosition.y > this.height - 1) {
			currentPosition.x = sourcePosition.x;
			currentPosition.y = this.height - 1;
			return 3;
		} else if (segment == 3 && nextPosition.x <= sourcePosition.x) {
			return 4;
		} else if (segment == 4 && nextPosition.x < 0) {
			currentPosition.x = 0;
			currentPosition.y = sourcePosition.y;
			return 5;
		} else if (segment == 5 && nextPosition.y <= sourcePosition.y) {
			return 6;
		} else if (segment  == 6 && nextPosition.y < 0) {
			currentPosition.x = sourcePosition.x;
			currentPosition.y = 0;
			return 7;
		} else if (segment == 7 && currentPosition.x > sourcePosition.x) {
			return 0;
		}
		
		return segment;
	}
	
	public double getAdjacentDistance(int segment, MapPosition sourcePosition) {
		if (segment == 0 || segment == 7) {
			return sourcePosition.y;
		} else if (segment == 1 || segment == 2) {
			return this.width - sourcePosition.x - 1;
		} else if (segment == 3 || segment == 4) {
			return this.height - sourcePosition.y - 1;
		} else if (segment == 5 || segment == 6) {
			return sourcePosition.x;
		}
		
		throw new IllegalArgumentException("Segment must be in range 0 to 7");
	}
	
	
	public double getOppositeDistance (double innerAngle, double oppositeDistance) {
		return Math.tan(Math.toRadians(innerAngle)) * oppositeDistance;
	}
	
	public static boolean isOnPath(MapPosition a, MapPosition b, MapPosition c) {
		return isOnPath(a, b, c, 6);
	}
	
	public static boolean isOnPath(MapPosition a, MapPosition b, MapPosition c, int precision) {
		return areEqualToPrecision(cartiseanDistance(a, c) + cartiseanDistance(b, c), cartiseanDistance(a, b), precision);
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
