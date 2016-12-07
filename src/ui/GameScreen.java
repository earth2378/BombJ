package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameScreen extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		root.setPrefSize(800, 650);
		root.setPadding(new Insets(0));
		Canvas canvas = new Canvas(800,600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.setTop(canvas);
		Image bg = new Image(ClassLoader.getSystemClassLoader().getResource("img/bg2.jpg").toString());
		gc.drawImage(bg, 0, 0);
		BottomPane bottomPane = new BottomPane(gc);
		root.setBottom(bottomPane);
		Scene s = new Scene(root);
		stage.setScene(s);
		stage.show();
		
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
}