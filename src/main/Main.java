package main;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lib.CodeUtility;
import ui.GameScreen;

public class Main extends Application{

	public static Main instance;
	private Stage primaryStage;
	private GameScreen startScreen;
	private GameWindow gameWindow;
	private Scene startScene,gameScene;
	public boolean openGame = false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TouhouBomberGirls");
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
			gameWindow = new GameWindow();
			gameScene = new Scene(gameWindow);
			this.primaryStage.setScene(gameScene);
			
			gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.A || event.getCode() == KeyCode.S 
							|| event.getCode() == KeyCode.D  || event.getCode() == KeyCode.UP  || event.getCode() == KeyCode.DOWN
							|| event.getCode() == KeyCode.RIGHT  || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.SPACE
							|| event.getCode() == KeyCode.CONTROL){
						CodeUtility.keyPressed.add(event.getCode());
					}
				}
			});
		}
	}
}
