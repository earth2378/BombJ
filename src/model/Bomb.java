package model;

import javafx.scene.canvas.GraphicsContext;
import lib.IRenderableObject;
import lib.RenderableHolder;

public class Bomb extends Entity implements IRenderableObject{
	
	private Player player;
	private int range;
	private final int BOMBDELAY = 4;
	private int bombDelayCount = 0;
	
	public Bomb(int x, int y, Player player, int range) {
		super(x, y, 3);
		this.player = player;
		this.range = range;
	}

	@Override
	public void render(GraphicsContext gc) {
		if(player instanceof Player1){
			if(bombDelayCount<=3){
				gc.drawImage(RenderableHolder.bomb1[0], x, y);
			}else{
				gc.drawImage(RenderableHolder.bomb1[1], x, y);
				for(int i=0; i<range; i++){
					// will try condition later such as if(x.isDropable)
					gc.drawImage(RenderableHolder.flame1, x+i*60, y);
				}
				
				for(int i=0; i<range; i++){
					gc.drawImage(RenderableHolder.flame1, x+i*(-60), y);
				}
				
				for(int i=0; i<range; i++){
					gc.drawImage(RenderableHolder.flame1, x, y+i*60);
				}
				
				for(int i=0; i<range; i++){
					gc.drawImage(RenderableHolder.flame1, x, y+i*(-60));
				}
			}
		}else if(player instanceof Player2){
			if(bombDelayCount<=3){
				gc.drawImage(RenderableHolder.bomb2[0], x, y);
			}else{
				gc.drawImage(RenderableHolder.bomb2[1], x, y);
				for(int i=0; i<range; i++){
					// will try condition later such as if(x.isDropable)
					gc.drawImage(RenderableHolder.flame2, x+i*60, y);
				}
				
				for(int i=0; i<range; i++){
					gc.drawImage(RenderableHolder.flame2, x+i*(-60), y);
				}
				
				for(int i=0; i<range; i++){
					gc.drawImage(RenderableHolder.flame2, x, y+i*60);
				}
				
				for(int i=0; i<range; i++){
					gc.drawImage(RenderableHolder.flame2, x, y+i*(-60));
				}
			}
		}
	}
	
	
	
}
