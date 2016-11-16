package model;

import javafx.scene.canvas.GraphicsContext;

public class Explodable extends Block{
	private int life;
	
	public Explodable(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		setLife(1);
		z = 1;
	}

	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

}
