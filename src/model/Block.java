package model;

public abstract class Block extends Entity {
	
	public Block(int x, int y) {
		super(x, y, Entity.SOUTH);
		isPass = false;
	}
	
}