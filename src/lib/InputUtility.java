package lib;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class InputUtility {

	private static int mouseX, mouseY;
	private static boolean mouseLeftDown, mouseRightDown, mouseOnScreen;
	private static boolean mouseLeftLastDown, mouseRightLastDown;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
		
	}

	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}

	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}

	public static boolean isMouseRightDown() {
		return mouseRightDown;
	}

	public static void setMouseRightDown(boolean mouseRightDown) {
		InputUtility.mouseRightDown = mouseRightDown;
	}

	public static boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	public static void setMouseOnScreen(boolean mouseOnScreen) {
		InputUtility.mouseOnScreen = mouseOnScreen;
	}

	public static boolean isMouseLeftClicked() {
		return mouseLeftLastDown;
	}

	public static void setMouseLeftLastDown(boolean v) {
		mouseLeftLastDown = v;
	}

	public static boolean isMouseRightClicked() {
		return mouseRightLastDown;
	}

	public static void setMouseRightLastDown(boolean v) {
		mouseRightLastDown = v;
	}

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		if(pressed){
			if(!keyPressed.contains(keycode)){
				keyPressed.add(keycode);
			}
		}else{
			if(keyPressed.contains(keycode)){
				keyPressed.remove(keycode);
			}
		}
	}

	public static boolean getKeyTriggered(KeyCode keycode) {
		return keyTriggered.contains(keycode);
	}

	public static void setKeyTriggered(KeyCode keycode, boolean pressed) {
		if(pressed){
			if(!keyTriggered.contains(keycode)){
				keyTriggered.add(keycode);
			}
		}else{
			if(keyTriggered.contains(keycode)){
				keyTriggered.remove(keycode);
			}
		}
	}

	public static void postUpdate() {
		mouseLeftLastDown = false;
		mouseRightLastDown = false;
		for(int s = keyTriggered.size()-1; s>=0; s--){
			keyTriggered.remove(s);
		}
	}
}
