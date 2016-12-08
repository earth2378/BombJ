package model;

import javafx.scene.canvas.GraphicsContext;
import lib.AudioUtility;
import lib.RenderableHolder;

public class Range extends Item{

	public Range(int x, int y, int direction) {
		super(x, y, direction);
	}

	@Override
	public void useItem(Player p) {
		if(!isDestroy){
			p.setRange(p.getRange() + 1);
			isDestroy = true;
			AudioUtility.playSound("collect");
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.range, x, y);
		
	}
	
}
