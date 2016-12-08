package model;

import javafx.scene.canvas.GraphicsContext;
import lib.RenderableHolder;

public class Explodable extends Block {
	private int life;

	public Explodable(int x, int y) {
		super(x, y);
		setLife(1);
		z = 1;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.explodable, x, y);
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
		if (life == 0) {
			isDestroy = true;
		}
	}

}
