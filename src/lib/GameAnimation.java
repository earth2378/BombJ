package lib;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class GameAnimation implements IRenderableObject {

	private Image image = null;
	private int frameCount, frameDelay;
	private int currentFrame, frameDelayCount;
	private int x, y, frameWidth, frameHeight;
	private boolean visible = false, playing = false;

	public GameAnimation(Image shootanim, int frameCount, int frameDelay) {
		
		image = shootanim;
		this.frameCount = frameCount;
		this.frameDelay = frameDelay;
		currentFrame = 0;
		frameDelayCount = 0;
		x = 0;
		y = 0;
		if(shootanim == null){
			frameHeight = 0;
			frameWidth = 0;
		}else{
			frameHeight = 100;
			frameWidth = 100;
		}
		
	}

	protected void topLeftAnimationAt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected void centerAnimationAt(int x, int y) {
		this.x = x - frameWidth / 2;
		this.y = y - frameHeight / 2;
	}

	public void play() {
		currentFrame = 0;
		playing = true;
		visible = true;
	}

	public void stop() {
		currentFrame = 0;
		playing = false;
		visible = false;
	}

	public void updateAnimation() {
		if (!playing)
			return;
		if (frameDelayCount > 0) {
			frameDelayCount--;
			return;
		}
		frameDelayCount = frameDelay;
		currentFrame++;
		if (currentFrame == frameCount) {
			stop();
		}
	}
	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public int getZ() {
		return Integer.MAX_VALUE - 1;
	}

	@Override
	public void render(GraphicsContext gc) {
		if(visible && (image!=null)){
			WritableImage crop = new WritableImage(image.getPixelReader(), currentFrame * 100, 0, 100, 100);
			gc.drawImage(crop, x, y);
		}
	}

}
