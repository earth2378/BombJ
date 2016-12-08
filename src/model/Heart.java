package model;

import javafx.scene.canvas.GraphicsContext;
import lib.AudioUtility;
import lib.RenderableHolder;

public class Heart extends Item {
	
	public Heart(int x, int y, int direction) {
		super(x, y, direction);
	}
	
	@Override
	public void useItem(Player p) {
		if(!isDestroy){
			p.setLife(p.getLife() + life);
			isDestroy = true;
			AudioUtility.playSound("collect");
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.heart, x, y);
	}
	
}
