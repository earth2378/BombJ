package model;

import javafx.scene.canvas.GraphicsContext;
import lib.IRenderableObject;

public class Flame extends Entity implements IRenderableObject {
	
	private Player player;
	
	public Flame(int x,int y,Player player){
		super(x,y,NORTH);
		this.player = player;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public boolean isDestroy(){
		return isDestroy;
	}
	
	public void setDestroy(boolean bool){
		isDestroy = bool;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
}
