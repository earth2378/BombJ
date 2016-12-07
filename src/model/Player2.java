package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;

public class Player2 extends Player{
	
	public Player2(int x, int y, int direction, String name) {
		super(x, y, direction, name);
	}

	@Override
	public void render(GraphicsContext gc) {
		if(direction == NORTH){
			gc.drawImage(RenderableHolder.player2Up, x, y);
		}else if(direction == EAST){
			gc.drawImage(RenderableHolder.player2Right, x, y);
		}else if(direction == WEST){
			gc.drawImage(RenderableHolder.player2Left, x, y);
		}else if(direction == SOUTH){
			gc.drawImage(RenderableHolder.player2Down, x, y);
		}
	}

}
