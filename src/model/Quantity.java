package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;

public class Quantity extends Item {

	public Quantity(int x, int y, int direction) {
		super(x, y, direction);
	}
	
	@Override
	public void useItem(Player p) {
		if(life > 0){
			p.setQuantity(p.getQuantity() + 1);
			life--;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.quantity, x, y);
	}

}
