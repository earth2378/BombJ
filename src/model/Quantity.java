package model;

import javafx.scene.canvas.GraphicsContext;
import lib.AudioUtility;
import lib.RenderableHolder;

public class Quantity extends Item {

	public Quantity(int x, int y, int direction) {
		super(x, y, direction);
	}

	@Override
	public void useItem(Player p) {
		if (!isDestroy) {
			p.setQuantity(p.getQuantity() + 1);
			isDestroy = true;
			AudioUtility.playSound("collect");
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.quantity, x, y);
	}

}
