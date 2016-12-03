package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;


public class BottomPane extends GridPane{
	private Button start,exit,instruction;
	

	
	public BottomPane(){
		this.setPrefSize(800, 50);
		
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-image: url('img/bottomBG.png')");
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
		
		
		
		
		//Set on action
		instruction.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(instruction.getText() == "How to play?"){
					GameScreen.setBackground(2);
					instruction.setText("I got it!");
				}else if(instruction.getText() == "I got it!"){
					GameScreen.setBackground(1);
					instruction.setText("How to play?");
				}
			}
		});
	}
}
