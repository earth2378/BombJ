package ui;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class GameScreen extends BorderPane{
	private Canvas canvas ;
	protected static GraphicsContext gc;
	
	public GameScreen(){
		this.setPrefSize(780, 630);
		this.setPadding(new Insets(0));
		canvas = new Canvas(800,650);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		Image bg = new Image(ClassLoader.getSystemClassLoader().getResource("img/bg2.jpg").toString());
		gc.drawImage(bg, 0, 0);
		BottomPane bottomPane = new BottomPane();
		this.setBottom(bottomPane);
		
		
		
		
	}
	public static void setBackground(int type){
		gc.clearRect(0, 0, 800, 650);
		if(type == 1){
			Image bg = new Image(ClassLoader.getSystemClassLoader().getResource("img/bg2.jpg").toString());
			gc.drawImage(bg, 0, 0);
		}else if (type == 2){
			gc.setFill(Color.AQUA);
			gc.fillRect(0, 0, 800, 650);
		}
		
	}
	
	
}
