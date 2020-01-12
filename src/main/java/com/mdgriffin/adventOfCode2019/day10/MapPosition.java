package com.mdgriffin.adventOfCode2019.day10;

public class MapPosition {
	
	double x;
	double y;
	boolean isAsteroid;
	int numVisibleAsteroids;

	public MapPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public MapPosition(double x, double y, boolean isAsteroid) {
		this.x = x;
		this.y = y;
		this.isAsteroid = isAsteroid;
	}
	
	@Override
	public String toString() {
		return (isAsteroid? "#," : ".,") + this.x + "," + this.y;
	}
	
	@Override
	public int hashCode() {
		return (int) (17 * x * y);// * numVisibleAsteroids;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof MapPosition)) {
			return false;
		}

		MapPosition otherMapPosition = (MapPosition) o;

		return this.x == otherMapPosition.x && this.y == otherMapPosition.y;
	}
	
}