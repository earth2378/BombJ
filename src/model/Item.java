package model;


import javafx.scene.canvas.GraphicsContext;

public class Item extends Entity{

	public Item(int x, int y, int direction) {
		super(x, y, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}


}
