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
			alert.setTitle(player.getName() + " Wins!!");
		} else if (player instanceof Player1) {
			alert.setTitle(player.getName() + " Wins!!");
		}
		alert.setHeaderText(null);
		if (player instanceof Player2) {
			alert.setContentText(player.getName() + " is the WINNER " + "\nScore : "+player.getScore());
		} else if (player instanceof Player1) {
			alert.setContentText(player.getName() + " is the WINNER " + "\nScore : "+player.getScore());
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
		alert.setContentText(p1.getName() + " got " + p1.getScore() + "\n" + p2.getName() + " got " + p2.getScore());
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				Platform.exit();
			}
		});
	}
}
