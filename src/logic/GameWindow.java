package logic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lib.IRenderableObject;
import lib.RenderableHolder;
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
		gc.drawImage(RenderableHolder.statusbg, 0, 660);
		boolean p1dead = true;
		boolean p2dead = true;
		for(int i=0;i<RenderableHolder.getInstance().getEntities().size();i++){
			if(RenderableHolder.getInstance().getEntities().get(i) instanceof Player1){
				p1dead = false;
				gc.drawImage(RenderableHolder.head1, 5, 670);
				gc.setLineWidth(2);
				gc.setFill(Color.WHITESMOKE);
				gc.setFont(Font.font("Times New Roman", 40));
				gc.fillText(String.format("%05d",((Player1)RenderableHolder.getInstance().getEntities().get(i)).getScore()), 50, 705);
				int life = ((Player1)RenderableHolder.getInstance().getEntities().get(i)).getLife();
				int location = 5;
				for(int j = 0; j < life ; j+=2){
					gc.drawImage(RenderableHolder.hearthead, location, 715);
					location += 40;
				}
			}else if(RenderableHolder.getInstance().getEntities().get(i) instanceof Player2){
				p2dead = false;
				gc.drawImage(RenderableHolder.head2, 615, 670);
				gc.setLineWidth(2);
				gc.setFill(Color.WHITESMOKE);
				gc.setFont(Font.font("Times New Roman", 40));
				gc.fillText(String.format("%05d",((Player2)RenderableHolder.getInstance().getEntities().get(i)).getScore()), 510, 705);
				int life = ((Player2)RenderableHolder.getInstance().getEntities().get(i)).getLife();
				int location = 615;
				for(int j = 0; j < life ; j+=2){
					gc.drawImage(RenderableHolder.hearthead, location, 715);
					location -= 40;
				}
			}
		}
		if(p1dead){
			gc.drawImage(RenderableHolder.bomb1[1], 5, 680);
		}
		if(p2dead){
			gc.drawImage(RenderableHolder.bomb2[1], 595, 680);
		}
	}
}
