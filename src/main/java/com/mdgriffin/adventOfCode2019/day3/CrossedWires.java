package com.mdgriffin.adventOfCode2019.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrossedWires {

	private List<List<XY>> paths = new ArrayList<List<XY>>();
	private final XY source = new XY(0, 0);

	public void addToPath(String path) {
		List<XY> currentPath = new ArrayList<XY>();
		String[] points = path.split(",");
		XY currentXY = new XY(source.x, source.y);

		currentPath.add(new XY(0, 0));

		for (String point : points) {
			char direction = point.charAt(0);
			int distance = Integer.parseInt(point.substring(1));

			Direction.getByDirectionChar(direction).addPointsToPath(currentPath, currentXY, distance);
		}

		paths.add(currentPath);
	}

	public List<XY> getIntersections() {
		List<XY> intersections;

		if (paths.size() > 1) {
			intersections = new ArrayList<XY>(paths.get(0));
			intersections.retainAll(paths.get(1));
		} else {
			return new ArrayList<XY>();
		}

		return intersections;
	}

	public List<List<XY>> getPaths() {
		return paths;
	}

	public int getDistanceToClosestIntersection() {
		int closestDistance = 0;

		for (XY intersection : getIntersections()) {
			if (intersection.x != 0 && intersection.y != 0) {

				int distance = calcManhattanDistance(source, intersection);

				if (closestDistance == 0 || distance < closestDistance) {
					closestDistance = distance;
				}
			}
		}

		return closestDistance;
	}

	public int findDistanceOfIdealPath() {
		int shortest = Integer.MAX_VALUE;
		List<XY> intersections = getIntersections();
		Map<XY, Integer> distanceToIntersections = new HashMap<XY, Integer>();

		for (List<XY> path : paths) {
			int distance = 0;
			for (XY point : path) {
				if (intersections.contains(point)) {
					if (!distanceToIntersections.containsKey(point)) {
						distanceToIntersections.put(point, 0);
					}
					distanceToIntersections.put(point, distance + distanceToIntersections.get(point));
				}
				distance++;
			}
		}

		for (XY key : distanceToIntersections.keySet()) {
			if (!isSource(key) && distanceToIntersections.get(key) < shortest) {
				shortest = distanceToIntersections.get(key);
			}
		}

		return shortest;
	}

	public boolean isSource(XY point) {
		return point.equals(source);
	}

	public static int calcManhattanDistance(XY point1, XY point2) {
		return Math.abs(point2.x - point1.x) + Math.abs(point2.y - point1.y);
	}

	private enum Direction {
		UP {
			@Override
			public void addPointsToPath(List<XY> path, XY startingPoint, int distance) {
				for (int i = 1; i <= distance; i++) {
					path.add(new XY(startingPoint.x, startingPoint.y + i));
				}
				startingPoint.y += distance;
			}
		},
		DOWN {
			@Override
			public void addPointsToPath(List<XY> path, XY startingPoint, int distance) {
				for (int i = 1; i <= distance; i++) {
					path.add(new XY(startingPoint.x, startingPoint.y - i));
				}
				startingPoint.y -= distance;
			}
		},
		LEFT {
			@Override
			public void addPointsToPath(List<XY> path, XY startingPoint, int distance) {
				for (int i = 1; i <= distance; i++) {
					path.add(new XY(startingPoint.x - i, startingPoint.y));
				}
				startingPoint.x -= distance;
			}
		},
		RIGHT {
			@Override
			public void addPointsToPath(List<XY> path, XY startingPoint, int distance) {
				for (int i = 1; i <= distance; i++) {
					path.add(new XY(startingPoint.x + i, startingPoint.y));
				}
				startingPoint.x += distance;
			}
		};

		public abstract void addPointsToPath(List<XY> path, XY startingPoint, int distance);

		public static Direction getByDirectionChar(char direction) {
			if (direction == 'U') {
				return UP;
			} else if (direction == 'D') {
				return DOWN;
			} else if (direction == 'L') {
				return LEFT;
			} else {
				return RIGHT;
			}
		}
	}

}
