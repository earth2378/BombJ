package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import main.Main;

public class BottomPane extends GridPane {
	private Button start, exit, instruction;
	private GraphicsContext gc;

	public BottomPane(GraphicsContext gc) {
		this.gc = gc;
		setPrefSize(800, 50);
		setAlignment(Pos.CENTER);
		setStyle("-fx-background-image: url('img/bottomBG.png')");
		start = new Button("Let's Go!!");
		exit = new Button("Good Bye T_T");
		instruction = new Button("How to play?");
		start.setPrefSize(150, 30);
		exit.setPrefSize(150, 30);
		instruction.setPrefSize(150, 30);
		this.setHgap(100);
		this.add(start, 0, 0);
		this.add(exit, 2, 0);
		this.add(instruction, 1, 0);

		// Set on action
		instruction.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (instruction.getText() == "How to play?") {
					Image bg = new Image(ClassLoader.getSystemClassLoader().getResource("img/instruction.jpg").toString());
					gc.drawImage(bg, 0, 0);
					instruction.setText("I got it!");
				} else if (instruction.getText() == "I got it!") {
					Image bg = new Image(ClassLoader.getSystemClassLoader().getResource("img/bg2.jpg").toString());
					gc.drawImage(bg, 0, 0);
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
				Main.instance.start(stage);
			}
		});
	}
}
