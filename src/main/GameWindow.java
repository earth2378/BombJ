package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lib.IRenderableObject;
import lib.RenderableHolder;
import model.Player;
import model.Player1;
import model.Player2;

public class GameWindow extends Canvas {
	
	public static int screen_width, screen_height;
	
	public GameWindow(int width,int height) {
		screen_height = height+100;
		screen_width = width;
	}
	
	public void paintComponents(){
		setHeight(screen_height);
		setWidth(screen_width);
		GraphicsContext gc = this.getGraphicsContext2D();
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Image bg = new Image(loader.getResource("img/plain.jpg").toString());
		gc.drawImage(bg,0,0);
		
		for(IRenderableObject i : RenderableHolder.getInstance().getEntities()){
			i.render(gc);
		}
	}
	public void paintStatusBar(){
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.DARKGRAY);
		gc.fillRect(0, 660, 660, 100);
		for(int i=0;i<RenderableHolder.getInstance().getEntities().size();i++){
			if(RenderableHolder.getInstance().getEntities().get(i) instanceof Player1){
				int life = ((Player1)RenderableHolder.getInstance().getEntities().get(i)).getLife();
				int location = 5;
				for(int j = 0; j < life ; j+=2){
					gc.drawImage(RenderableHolder.hearthead, location, 715);
					location += 40;
				}
			}else if(RenderableHolder.getInstance().getEntities().get(i) instanceof Player2){
				int life = ((Player2)RenderableHolder.getInstance().getEntities().get(i)).getLife();
				int location = 615;
				for(int j = 0; j < life ; j+=2){
					gc.drawImage(RenderableHolder.hearthead, location, 715);
					location -= 40;
				}
			}
		}
	}
}
