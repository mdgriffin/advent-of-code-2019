package com.mdgriffin.adventOfCode2019.day10;

import com.mdgriffin.adventOfCode2019.day3.XY;

public class MapPosition extends XY {
	
	boolean isAsteroid;
	int numVisibleAsteroids;

	public MapPosition(int x, int y) {
		super(x, y);
	}
	
	public MapPosition(int x, int y, boolean isAsteroid) {
		super(x, y);
		this.isAsteroid = isAsteroid;
	}
	
	@Override
	public String toString() {
		return (isAsteroid? "#," : ".,") + super.toString();
	}
	
	// TODO: Probably not necessary
	@Override
	public int hashCode() {
		return 17 * x * y;// * numVisibleAsteroids;
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
