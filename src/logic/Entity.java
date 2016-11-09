package logic;

import logic.Field;

public abstract class Entity {
	public static final int WEST = 0;
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	protected int x;
	protected int y;
	protected int direction;
	protected boolean isDestroyed;
	protected boolean isDestroyedInNextState;
	protected Field field;
	protected int life;
	
	public Entity(Field field, int x ,int y ,int direction,int movingDelay){
		this.isDestroyed = false;
		this.x = x;
		this.y = y;
		field.addEntity(this);
		this.field = field;
		this.direction = direction;
	}
	
	public abstract boolean isMove();
	public abstract void update();

	protected abstract void calculateNextState();
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

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	
}

	