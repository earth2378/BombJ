package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.GameScreen;

public class Main extends Application{

	public static Main instance;
	private Stage primaryStage;
	private GameScreen startScreen,gameOpen;
	private Scene startScene,openScene;
	public boolean openGame = false;
	
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
	public void gameStart(){
		if(openGame){
			gameOpen = new GameScreen();
			openScene = new Scene(gameOpen);
			this.primaryStage.setScene(openScene);
			
		}
	}
}
