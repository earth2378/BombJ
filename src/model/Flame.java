package model;

import javafx.scene.canvas.GraphicsContext;
import lib.IRenderableObject;
import lib.RenderableHolder;

public class Flame extends Entity implements IRenderableObject {

	private Player player;
	private int afterburn = 0;

	public Flame(int x, int y, Player player) {
		super(x, y, NORTH);
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public void setDestroy(boolean bool) {
		isDestroy = bool;
	}

	public int getAfterburn() {
		return afterburn;
	}

	public void setAfterburn(int afterburn) {
		this.afterburn = afterburn;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (player instanceof Player1) {
			gc.drawImage(RenderableHolder.flame1, x, y);
		} else if (player instanceof Player2) {
			gc.drawImage(RenderableHolder.flame2, x, y);
		}

	}

}
