package com.mdgriffin.adventOfCode2019.day3;

public class XY {
	public int x;
	public int y;

	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof XY)) {
			return false;
		}

		XY otherXY = (XY) o;

		return this.x == otherXY.x && this.y == otherXY.y;
	}

	@Override
	public int hashCode() {
		return 17 * x * y;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
}