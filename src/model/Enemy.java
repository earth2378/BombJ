package model;

import javafx.scene.canvas.GraphicsContext;
<<<<<<< HEAD
=======

public class Enemy extends Entity {

	public Enemy(int x, int y, int direction) {
		super(x, y, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
>>>>>>> origin/master

public class Enemy extends Entity{
	protected int moveDelay,moveDelayCounter;
		
	public Enemy(int x, int y, int direction) {
		super(x, y, direction);
		// TODO Auto-generated constructor stub
		this.isDestroy = false;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
	public void attackPlayer(Player player){
		player.setDestroy(false);
	}
	
	public void move(){
		
	}
	
	
}
