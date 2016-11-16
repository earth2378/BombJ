package model;

import javafx.scene.canvas.GraphicsContext;
import lib.IRenderableObject;

public abstract class Entity implements IRenderableObject{
	protected int x,y,z,direction,nextDirection,nextX,nextY;
	protected boolean isDestroy;
	public static final int WEST = 0;
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	
	
	public Entity(int x, int y, int direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	public void setZ(int z) {
		// TODO Auto-generated method stub
		this.z=z;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getNextDirection() {
		return nextDirection;
	}

	public void setNextDirection(int nextDirection) {
		this.nextDirection = nextDirection;
	}

	public int getNextX() {
		return nextX;
	}

	public void setNextX(int nextX) {
		this.nextX = nextX;
	}

	public int getNextY() {
		return nextY;
	}

	public void setNextY(int nextY) {
		this.nextY = nextY;
	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}
}