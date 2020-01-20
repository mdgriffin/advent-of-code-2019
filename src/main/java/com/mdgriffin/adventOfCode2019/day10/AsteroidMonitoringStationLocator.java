package com.mdgriffin.adventOfCode2019.day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AsteroidMonitoringStationLocator {

	int width;
	int height;
	MapPosition[][] map;

	public AsteroidMonitoringStationLocator(String asteroidDescriptor) {
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

	private void setNumVisibleOnAllMapPosition() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].numVisibleAsteroids = getNumVisibleAsteroidsFromSource(map[i][j]);
			}
		}
	}

	public MapPosition getBestObservationPosition() {
		MapPosition bestPosition = new MapPosition(0, 0, true);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j].isAsteroid && map[i][j].numVisibleAsteroids >= bestPosition.numVisibleAsteroids) {
					bestPosition = map[i][j];
				}
			}
		}

		return bestPosition;
	}

	public int getNumVisibleAsteroidsFromSource(MapPosition sourcePosition) {
		return getVisibleAsteroidsFromSource(sourcePosition).size();
	}

	public List<MapPosition> getVisibleAsteroidsFromSource(MapPosition sourcePosition) {
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

	public List<MapPosition> getPointsOnPath(MapPosition sourcePosition, MapPosition destPosition) {
		List<MapPosition> points = new ArrayList<MapPosition>();

		for (int i = (int) Math.min(sourcePosition.y, destPosition.y); i <= Math.max(sourcePosition.y,
				destPosition.y); i++) {
			for (int j = (int) Math.min(sourcePosition.x, destPosition.x); j <= Math.max(sourcePosition.x,
					destPosition.x); j++) {
				MapPosition currentPosition = map[i][j];

				if (!currentPosition.equals(sourcePosition) && !currentPosition.equals(destPosition)
						&& isOnPath(sourcePosition, destPosition, currentPosition)) {
					points.add(currentPosition);
				}
			}
		}

		return points;
	}

	public List<MapPosition> pulveriseAsteroids() {
		MapPosition sourcePosition = getBestObservationPosition();
		List<MapPosition> visibleAsteroids = getVisibleAsteroidsFromSource(sourcePosition);
		List<MapPosition> destroyedAsteroids = new ArrayList<MapPosition>();

		Comparator<MapPosition> angleComparator = (a, b) -> {
			double angleOfA = getAngle(sourcePosition, a);
			double angleOfB = getAngle(sourcePosition, b);

			if (angleOfA > angleOfB) {
				return 1;
			} else if (angleOfA < angleOfB) {
				return -1;
			}

			return 0;
		};

		while (visibleAsteroids.size() > 0) {
			visibleAsteroids.forEach(asteroid -> asteroid.isAsteroid = false);
			visibleAsteroids.sort(angleComparator);

			destroyedAsteroids.addAll(visibleAsteroids);

			visibleAsteroids = getVisibleAsteroidsFromSource(sourcePosition);
		}

		return destroyedAsteroids;
	}

	public static boolean isOnPath(MapPosition start, MapPosition end, MapPosition point) {
		return isOnPath(start, end, point, 6);
	}

	public static boolean isOnPath(MapPosition start, MapPosition end, MapPosition point, int precision) {
		return areEqualToPrecision(getDistance(start, point) + getDistance(end, point), getDistance(start, end),
				precision);
	}

	public static boolean areEqualToPrecision(double a, double b, int precision) {
		return Math.abs(a - b) <= Math.pow(10, -precision);
	}

	public static double getDistance(MapPosition a, MapPosition b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	protected static double getAngle(MapPosition sourcePosition, MapPosition point) {
		double xSide = 0;
		double ySide = 0;
		double hypSide = 0;
		double innerAngle = 0;
		Section section = Section.getSectionByMapPosition(sourcePosition, point);

		switch (section) {
		case TopRight:
			xSide = point.x - sourcePosition.x;
			ySide = sourcePosition.y - point.y;
			break;
		case BottomRight:
			xSide = point.x - sourcePosition.x;
			ySide = point.y - sourcePosition.y;
			break;
		case BottomLeft:
			xSide = sourcePosition.x - point.x;
			ySide = point.y - sourcePosition.y;
			break;
		case TopLeft:
			xSide = sourcePosition.x - point.x;
			ySide = sourcePosition.y - point.y;
		default:
			break;
		}

		hypSide = Math.sqrt(Math.pow(xSide, 2) + Math.pow(ySide, 2));

		if (xSide > 0 && ySide > 0 && hypSide > 0) {
			if (section.equals(Section.TopRight) || section.equals(Section.BottomLeft)) {
				innerAngle = Math.toDegrees(Math.acos(
						(Math.pow(hypSide, 2) + Math.pow(ySide, 2) - Math.pow(xSide, 2)) / (2 * hypSide * ySide)));
			} else {
				innerAngle = Math.toDegrees(Math.acos(
						(Math.pow(hypSide, 2) + Math.pow(xSide, 2) - Math.pow(ySide, 2)) / (2 * hypSide * xSide)));
			}
		}

		return section.angleOffset + innerAngle;
	}

	protected static enum Section {
		TopLeft(270), TopRight(0), BottomLeft(180), BottomRight(90);

		private int angleOffset;

		Section(int angleOffset) {
			this.angleOffset = angleOffset;
		}

		public static Section getSectionByMapPosition(MapPosition sourcePosition, MapPosition point) {

			if (point.x >= sourcePosition.x && point.y < sourcePosition.y) {
				return TopRight;
			} else if (point.x > sourcePosition.x && point.y >= sourcePosition.y) {
				return BottomRight;
			} else if (point.x <= sourcePosition.x && point.y > sourcePosition.y) {
				return BottomLeft;
			} else if (point.x < sourcePosition.x && point.y <= sourcePosition.y) {
				return TopLeft;
			} else

				return TopLeft;
		}

	}

}
