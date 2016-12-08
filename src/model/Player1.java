package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;

public class Player1 extends Player {

	public Player1(int x, int y, int direction, String name) {
		super(x, y, direction, name);
	}

	@Override
	public void render(GraphicsContext gc) {
		if (direction == NORTH) {
			gc.drawImage(RenderableHolder.player1Up, x, y);
		} else if (direction == EAST) {
			gc.drawImage(RenderableHolder.player1Right, x, y);
		} else if (direction == WEST) {
			gc.drawImage(RenderableHolder.player1Left, x, y);
		} else if (direction == SOUTH) {
			gc.drawImage(RenderableHolder.player1Down, x, y);
		}
	}

}
