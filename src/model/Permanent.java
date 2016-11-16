package model;

import javafx.scene.canvas.GraphicsContext;

public class Permanent extends Block{
	private int life;
	
	public Permanent(int x, int y, int direction) {
		super(x, y);
		// TODO Auto-generated constructor stub
		z = 1;
		setLife(Integer.MAX_VALUE);
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	public boolean isDestroy(){
		return false;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}
