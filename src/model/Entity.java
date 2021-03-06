package model;

import lib.IRenderableObject;

public abstract class Entity implements IRenderableObject {
	protected int x, y, z, direction;
	protected boolean isDestroy;
	public static final int WEST = 0;
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public boolean isPass = true;

	public Entity(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		isDestroy = false;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x <= 0) {
			x = 0;
		} else if (x >= 600) {
			x = 600;
		}

		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y <= 0) {
			y = 0;
		} else if (y >= 600) {
			y = 600;
		}

		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}
}
