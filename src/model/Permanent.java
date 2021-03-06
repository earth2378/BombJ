package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;

public class Permanent extends Block {
	private int life;

	public Permanent(int x, int y) {
		super(x, y);
		z = 1;
		setLife(Integer.MAX_VALUE);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.permanent, x, y);
	}

	public boolean isDestroy() {
		return false;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}
