package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import lib.RenderableHolder;
import model.Enemy;
import model.Player1;
import model.Player2;

public class GameWindow extends StackPane {
	
	private Canvas canvas;
	private GraphicsContext gc;
	
	public GameWindow() {
		
		canvas = new Canvas(600,600);
		gc = canvas.getGraphicsContext2D();
		Player1 p1 = new Player1(0, 0, 3, "Player1");
		Player2 p2 = new Player2(540, 540, 1, "Player2");
		RenderableHolder.getInstance().addAndSort(p1);
		RenderableHolder.getInstance().addAndSort(p2);
		Enemy m1 = new Enemy(0, 540, 1);
		Enemy m2 = new Enemy(540, 0, 3);
		getChildren().add(canvas);
		
		
	}
}
