package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lib.IRenderableObject;
import lib.RenderableHolder;
import model.Enemy;
import model.Entity;

public class GameWindow extends Canvas {
	
	public static int screen_width, screen_height;
	
	public GameWindow(int width,int height) {
		screen_height = height;
		screen_width = width;
	}
	
	public void paintComponents(){
		setHeight(screen_height);
		setWidth(screen_width);
		GraphicsContext gc = this.getGraphicsContext2D();
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Image bg = new Image(loader.getResource("img/plain.jpg").toString());
		gc.drawImage(bg,0,0);
		
		for(IRenderableObject i : RenderableHolder.getInstance().getEntities()){
			i.render(gc);
		}
	}
}
