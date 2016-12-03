package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.GameScreen;

public class Main extends Application{

	public static Main instance;
	private Stage primaryStage;
	private GameScreen startScreen;
	private Scene startScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Boom_J");
		this.primaryStage.setResizable(false);
		startScreen = new GameScreen();
		startScene = new Scene(startScreen);
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
