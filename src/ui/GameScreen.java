package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.Main;

public class GameScreen extends BorderPane {
	
	public GameScreen(Stage stage) {
		
		setPrefSize(800,600);
		//setPadding(new Insets(0));
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Image bg = new Image(loader.getResource("img/bg2.jpg").toString());
		Canvas canvas = new Canvas(800,600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(bg, 12,12);
		GridPane bottom = new GridPane();
		bottom.setPrefSize(800, 50);
		bottom.setAlignment(Pos.CENTER);
		setStyle("-fx-background-image: url('img/bottomBG.png')");
		Button start = new Button("Let's Go!!");
		Button exit = new Button("Good Bye T_T");
		Button instruction = new Button("How to play?");
		start.setPrefSize(150, 30);
		exit.setPrefSize(150, 30);
		instruction.setPrefSize(150, 30);
		
		bottom.setHgap(100);
		bottom.add(start, 0, 0);
		bottom.add(exit, 2, 0);
		bottom.add(instruction, 1, 0);
		
		setTop(canvas);
		setBottom(bottom);
		
		instruction.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (instruction.getText() == "How to play?") {
					Image bg1 = new Image(ClassLoader.getSystemClassLoader().getResource("img/instruction.jpg").toString());
					gc.drawImage(bg1, 12, 12);
					instruction.setText("Next");
				} else if (instruction.getText() == "Next") {
					Image bg2 = new Image(ClassLoader.getSystemClassLoader().getResource("img/instruction2.jpg").toString());
					gc.drawImage(bg2, 12, 12);
					instruction.setText("I got it!!");
				} else if (instruction.getText() == "I got it!!") {
					Image bg2 = new Image(ClassLoader.getSystemClassLoader().getResource("img/bg2.jpg").toString());
					gc.drawImage(bg2, 12, 12);
					instruction.setText("How to play?");
				}
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		start.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(Main.instance.gameScene);
			}
		});
		
	}
	
}