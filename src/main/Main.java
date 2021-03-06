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
import ui.GameWindow;

public class Main extends Application {

	public static Main instance;
	private Stage primaryStage;
	private GameScreen gameScreen;
	private Scene firstScene, secondScene;
	private Thread sound1, sound2;
	private boolean isEnd = false;

	@Override
	public void start(Stage primaryStage) throws Exception {
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TouhouBomberGirls");
		this.primaryStage.setResizable(false);
		gameScreen = new GameScreen(primaryStage);
		firstScene = new Scene(gameScreen);
		primaryStage.setScene(firstScene);
		sound1 = new Thread(new Runnable() {
			@Override
			public void run() {
				AudioUtility.playSound("first");
			}
		});
		sound1.start();
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void gameStart() {
		Group root = new Group();
		secondScene = new Scene(root, 660, 760);
		GameWindow gameWindow = new GameWindow(660, 760);
		root.getChildren().add(gameWindow);
		GameManager gameManager = new GameManager();
		gameWindow.paintComponents();
		primaryStage.setScene(secondScene);
		sound2 = new Thread(new Runnable() {
			@Override
			public void run() {
				AudioUtility.playSound("second");
			}
		});
		sound2.start();
		secondScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.A || event.getCode() == KeyCode.S
						|| event.getCode() == KeyCode.D || event.getCode() == KeyCode.UP
						|| event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.RIGHT
						|| event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.SPACE
						|| event.getCode() == KeyCode.CONTROL) {
					gameManager.receiveKey(event.getCode());
				}
			}
		});

		secondScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.A || event.getCode() == KeyCode.S
						|| event.getCode() == KeyCode.D || event.getCode() == KeyCode.UP
						|| event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.RIGHT
						|| event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.SPACE
						|| event.getCode() == KeyCode.CONTROL) {
					gameManager.dropKey(event.getCode());
				}
			}
		});

		new AnimationTimer() {
			Long start = 0l;

			@Override
			public void handle(long now) {
				if (start == 0l) {
					start = now;
				}
				long diff = now - start;
				if (diff >= 100000000l) {
					start = 0l;
					if (!isEnd) {
						gameManager.update();
						gameWindow.paintComponents();
						gameWindow.paintStatusBar();
					}
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

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isPause) {
		this.isEnd = isPause;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Scene getFirstScene() {
		return firstScene;
	}

	public void setFirstScene(Scene firstScene) {
		this.firstScene = firstScene;
	}
}
