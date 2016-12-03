package ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class GameScreen extends BorderPane{
	
	
	public GameScreen(){
		this.setPrefSize(800, 800);
		BottomPane bottomPane = new BottomPane();
		this.setBottom(bottomPane);
		
		
		
		
	}
}
