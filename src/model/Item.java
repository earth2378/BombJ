package model;


import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends Entity{
	
	protected int life;
	private boolean isCollect;
	
	public Item(int x, int y, int direction) {
		super(x, y, direction);
		life = 1;
		isCollect = false;
		z = 1;
	}

	public boolean isCollect(){
		return isCollect;
	}
	
	@Override
	public boolean isDestroy() {
		return isDestroy;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public void render(GraphicsContext gc) {
		
	}
	
	public void useItem(Player p) {
		
	}

}
