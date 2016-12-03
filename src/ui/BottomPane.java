package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BottomPane extends GridPane{
	private Button start,exit,instruction;
	
	public BottomPane(){
		this.setPrefSize(800, 100);
		this.setAlignment(Pos.CENTER);
		start = new Button("Let's Go!!");
		exit = new Button("Good Bye T_T");
		instruction = new Button("How to play?");
		start.setPrefSize(150, 30);
		exit.setPrefSize(150, 30);
		instruction.setPrefSize(150, 30);
		this.setHgap(50);
		this.add(start, 0, 0);
		this.add(exit, 1, 0);
		this.add(instruction, 2, 0);
		
	}
}
