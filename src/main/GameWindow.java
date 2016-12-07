package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameWindow extends Canvas {
	
	public static int screen_width, screen_height;
	
	public GameWindow(int width,int height) {
		screen_height = height;
		screen_width = width;
	}
	
	public void f(){
		setHeight(screen_height);
		setWidth(screen_width);
		GraphicsContext gc = this.getGraphicsContext2D();
		setStyle("-fx-background-image: url('img/plain.jpg'); ");
	}
}
