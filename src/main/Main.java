package main;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lib.AudioUtility;
import logic.GameManager;
import ui.GameScreen;

public class Main extends Application{
	
	public static Main instance;
	private Stage primaryStage;
	private GameScreen gameScreen;
	private Scene firstScene,secondScene;
	private Thread sound1,sound2;
	private boolean isStopSound1 = false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TouhouBomberGirls");
		this.primaryStage.setResizable(false);
		gameScreen = new GameScreen(primaryStage);
		firstScene = new Scene(gameScreen);
		primaryStage.setScene(firstScene);
		sound1 = new Thread(new Runnable() {
			@Override
			public void run(){
				AudioUtility.playSound("first");
			}
		});
		sound1.start();
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		Application.launch(args);
		
	}
	
	public void gameStart(){
			Group root = new Group();
			secondScene = new Scene(root,660,760);
			GameWindow gameWindow = new GameWindow(660, 660);
			root.getChildren().add(gameWindow);
			GameManager gameManager = new GameManager();
			gameWindow.paintComponents();
			primaryStage.setScene(secondScene);
			sound2 = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					AudioUtility.playSound("second");
				}
			});
			sound2.start();
			secondScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.A || event.getCode() == KeyCode.S 
							|| event.getCode() == KeyCode.D  || event.getCode() == KeyCode.UP  || event.getCode() == KeyCode.DOWN
							|| event.getCode() == KeyCode.RIGHT  || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.SPACE
							|| event.getCode() == KeyCode.CONTROL){
						gameManager.receiveKey(event.getCode());
					}
				}
			});
			
			secondScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					// TODO Auto-generated method stub
					if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.A || event.getCode() == KeyCode.S 
							|| event.getCode() == KeyCode.D  || event.getCode() == KeyCode.UP  || event.getCode() == KeyCode.DOWN
							|| event.getCode() == KeyCode.RIGHT  || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.SPACE
							|| event.getCode() == KeyCode.CONTROL){
						gameManager.dropKey(event.getCode());
					}
				}
			});
			
			new AnimationTimer() {
				Long start = 0l;
				@Override
				public void handle(long now) {
					if(start==0l){
						start = now;
					}
					long diff = now - start;
					if(diff>=100000000l){
						start = 0l;
						gameManager.update();
						gameWindow.paintComponents();
					}
				}
			}.start();
		
	}
	public Thread getSound1() {
		return sound1;
	}
	public Thread getSound2() {
		return sound2;
	}
	public boolean isStopSound1() {
		return isStopSound1;
	}
	public void setStopSound1(boolean isStopSound1) {
		this.isStopSound1 = isStopSound1;
	}
}
