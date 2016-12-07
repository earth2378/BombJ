package model;


import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends Entity{
	
	protected int life;
	private boolean isCollect;
	
	public Item(int x, int y, int direction) {
		super(x, y, direction);
		life = 3;
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
	
	public void setLife(int life) {
		if(life <= 0){
			isDestroy=true;
		}
		this.life = life;
	}
	
	public int getLife() {
		return life;
	}

	@Override
	public abstract void render(GraphicsContext gc);
	
	public abstract void useItem(Player p);

}
