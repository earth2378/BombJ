package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;

public class Enemy extends Entity{
	
	protected int moveDelayCounter = 0;
	protected final int moveDelay = 2;
	
	public Enemy(int x, int y, int direction) {
		super(x, y, direction);
		this.isDestroy = false;
	}

	@Override
	public void render(GraphicsContext gc) {
		if(direction == 0){
			gc.drawImage(RenderableHolder.enemyleft, x, y);
		}else if(direction == 1){
			gc.drawImage(RenderableHolder.enemyup, x, y);
		}else if(direction == 2){
			gc.drawImage(RenderableHolder.enemyright, x, y);
		}else if(direction == 3){
			gc.drawImage(RenderableHolder.enemydown, x, y);
		}
	}
	
	public void attackPlayer(Player player){
		player.setDestroy(true);
	}
	
	public void move(){
		
	}
	
	
}
