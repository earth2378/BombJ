package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;
import logic.GameManager;

public class Enemy extends Entity{
	
	protected int moveDelayCounter = 0;
	protected final int moveDelay = 5;
	
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
	
	public void move(){
		if(moveDelayCounter==moveDelay){
			Random rand = new Random();
			int step = rand.nextInt(8)+1;
			if(step != 1){
				if(direction == model.Entity.NORTH){
					int j = (y-60)/60;
					int i = x/60;
					if(i>=0 && i<=10){
						if(j>=0 && j<=10){
							if(GameManager.instance.myField().getField(i, j) == 0){
								setY(y-=60);
							}else{
								int turn = rand.nextInt(5);
								setDirection(turn);
							}
						}
					}
				}else if(direction == model.Entity.WEST){
					int i = (x-60)/60;
					int j = y/60;
					if(i>=0 && i<=10){
						if(j>=0 && j<=10){
							if(GameManager.instance.myField().getField(i, j) == 0){
								setX(x-=60);
							}else{
								int turn = rand.nextInt(4);
								setDirection(turn);
							}
						}
					}
				}else if(direction == model.Entity.SOUTH){
					int i = x/60;
					int j = (y+60)/60;
					if(i>=0 && i<=10){
						if(j>=0 && j<=10){
							if(GameManager.instance.myField().getField(i, j) == 0){
								setY(y+=60);
							}else{
								int turn = rand.nextInt(4);
								setDirection(turn);
							}
						}
					}
				}else if(direction == model.Entity.EAST){
					int i = (x+60)/60;
					int j = y/60;
					if(i>=0 && i<=10){
						if(j>=0 && j<=10){
							if(GameManager.instance.myField().getField(i, j) == 0){
								setX(x+=60);
							}else{
								int turn = rand.nextInt(4);
								setDirection(turn);
							}
						}
					}
				}
			}
			if(step == 1){
				int turn = rand.nextInt(4);
				setDirection(turn);
			}
			moveDelayCounter=0;
		}else{
			moveDelayCounter+=1;
		}
	}
	
	public void attackPlayer(Player player){
		player.setLife(0);
		player.setDestroy(true);
	}
	
	
}
