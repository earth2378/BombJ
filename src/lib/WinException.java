package lib;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Player;
import model.Player1;
import model.Player2;
import javafx.scene.control.Alert.AlertType;

public class WinException extends Throwable {
	private static final long serialVersionUID = 1L;

	public static Alert alert;

	public WinException(Player player) {
		alert = new Alert(AlertType.INFORMATION);
		if (player instanceof Player2) {
			alert.setTitle("Player 2 Wins!!");
		} else if (player instanceof Player1) {
			alert.setTitle("Player 1 Wins!!");
		}
		alert.setHeaderText(null);
		if (player instanceof Player2) {
			alert.setContentText("Player 2 is the WINNER " + "\nScore : "+player.getScore());
		} else if (player instanceof Player1) {
			alert.setContentText("Player 1 is the WINNER " + "\nScore : "+player.getScore());
		}
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				Platform.exit();
			}
		});
	}

	public WinException(Player1 p1, Player2 p2) {
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Draws");
		alert.setHeaderText(null);
		alert.setContentText("Player 1 got " + p1.getScore() + "\nPlayer 2 got " + p2.getScore());
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				Platform.exit();
			}
		});
	}
}
