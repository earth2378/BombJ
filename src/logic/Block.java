package logic;

public abstract class Block extends Entity {
	protected int types;
	public Block(){
		
	}
	
	public abstract boolean isDestroyed();
}
