package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;
import logic.GameManager;
import main.GameWindow;
import main.Main;

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
	
	public void move(){
		int step = (int)(Math.random()*5 + 1);
		if(step != 1){
			if(direction == model.Entity.NORTH){
				int j = (y-60)/60;
				int i = x/60;
				if(i>=0 && i<=10){
					if(j>=0 && j<=10){
						if(GameManager.instance.myField().getField(i, j) == 0){
							setY(y-=60);
						}else{
							int turn = (int)(Math.random()*4);
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
							int turn = (int)(Math.random()*4);
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
							int turn = (int)(Math.random()*4);
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
							int turn = (int)(Math.random()*4);
							setDirection(turn);
						}
					}
				}
			}
		}
		if(step == 1){
			int turn = (int)(Math.random()*4);
			setDirection(turn);
		}
	}
	
	public void attackPlayer(Player player){
		player.setLife(0);
		player.setDestroy(true);
	}
	
}
