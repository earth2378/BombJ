package lib;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import lab5_lib.lib.ConfigurableOption;

public class GameBackground implements IRenderableObject {

	private Image bgImage = null;
	private int currentX = 0;
	private int imageWidth;

	public GameBackground() {
		bgImage = DrawingUtility.bg;
		if (bgImage != null)
			imageWidth = (int) bgImage.getWidth();
		else
			imageWidth = 0;

	}

	public void updateBackground() {
		currentX++;
		if (currentX >= imageWidth)
			currentX = 0;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return Integer.MIN_VALUE;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (bgImage == null)
			return;
		int currentDrawingX = 0;
		int currentDrawingY = 0;

		while (currentDrawingY < ConfigurableOption.screenHeight) {
			WritableImage croppedImage = new WritableImage(bgImage.getPixelReader(), currentX, 0, imageWidth - currentX,
					(int) bgImage.getHeight());
			gc.drawImage(croppedImage, currentDrawingX, currentDrawingY);
			currentDrawingY += bgImage.getHeight();
		}
		currentDrawingX += imageWidth - currentX;
		currentDrawingY = 0;

		while (currentDrawingX < ConfigurableOption.screenWidth) {
			while (currentDrawingY < ConfigurableOption.screenHeight) {
				gc.drawImage(bgImage, currentDrawingX, currentDrawingY);
				currentDrawingY += bgImage.getHeight();
			}
			currentDrawingX += imageWidth;
			currentDrawingY = 0;
		}
	}

}
