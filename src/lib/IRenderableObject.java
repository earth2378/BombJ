package lib;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderableObject {
	public boolean isDestroy();
	public int getZ();
	public void render(GraphicsContext gc);
}
