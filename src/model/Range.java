package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;

public class Range extends Item{

	public Range(int x, int y, int direction) {
		super(x, y, direction);
	}

	@Override
	public void useItem(Player p) {
		if(life > 0){
			p.setRange(p.getRange() + 1);
			life--;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.range, x, y);
		
	}
	
}
