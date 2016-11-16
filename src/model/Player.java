package model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Player extends Entity{
	
	protected int life,score,range,quantity,moveDelay,moveDelayCounter;
	protected boolean isDestroy,isWin;
	protected String name;
	
	public Player(int x,int y, int direction,String name){
		super(x,y,direction);
		this.life = 3;
		this.score = 0;
		this.range = 1;
		this.quantity = 1;
		this.isDestroy = false;
		this.isWin = false;
		this.name = name;
		this.moveDelay = ??;
		this.moveDelayCounter =??;
		
	}

	public void move(){
		
	}
	
	
}
