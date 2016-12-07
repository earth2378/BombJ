package model;

import javafx.scene.canvas.GraphicsContext;
import lib.IRenderableObject;
import lib.RenderableHolder;

public class Bomb extends Entity implements IRenderableObject{
	
	private Player player;
	private int range;
	private int bombDelayCount = 0;
	private int afterboom = 0;
	private boolean isBoom = false;
	
	public Bomb(int x, int y, Player player, int range) {
		super(x, y, 3);
		this.player = player;
		this.range = range;
	}

	@Override
	public void render(GraphicsContext gc) {
		if(player instanceof Player1){
			if(isBoom){
				gc.drawImage(RenderableHolder.bomb1[1], x, y);
			}else{
				gc.drawImage(RenderableHolder.bomb1[0], x, y);
			}
		}else if(player instanceof Player2){
			if(isBoom){
				gc.drawImage(RenderableHolder.bomb2[1], x, y);
			}else{
				gc.drawImage(RenderableHolder.bomb2[0], x, y);
			}
		}
	}
	
	public int getRange(){
		return range;
	}
	
	public boolean isBoom(){
		return isBoom;
	}
	
	public int isAfterboom() {
		return afterboom;
	}

	public void setAfterboom(int afterboom) {
		this.afterboom = afterboom;
	}
	
	public void setBoom(boolean boom){
		isBoom = boom;
	}
	
	public void setCount(int num){
		bombDelayCount = num;
	}
	
	public int getCount(){
		return bombDelayCount;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	
	
}
