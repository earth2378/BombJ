package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import lib.CodeUtility;
import lib.IRenderableObject;
import lib.RenderableHolder;
import model.Bomb;
import model.Enemy;
import model.Player1;
import model.Player2;

public class GameManager {
	
	private Player1 p1;
	private Player2 p2;
	
	public GameManager(){
		p1 = new Player1(0,0,3,"Player1");
		p2 = new Player2(540, 540, 1, "Player2");
		RenderableHolder.getInstance().add(p1);
		RenderableHolder.getInstance().add(p2);
	}
	
	public void update(){
		Thread threadP1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				moveP1();
			}
		});
		
		Thread threadP2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				moveP2();
			}
		});
		
		threadP1.start();
		threadP2.start();
		
		if(checkWin()){
			
		}
		
		checkCollision();
		removeDestroyEntity();
	}
	
	private void removeDestroyEntity(){
		for(int i=RenderableHolder.getInstance().getEntities().size(); i>=0; i--){
			if(RenderableHolder.getInstance().getEntities().get(i).isDestroy()){
				RenderableHolder.getInstance().getEntities().remove(i);
			}
		}
	}
	
	private void checkCollision(){
		for(IRenderableObject e : RenderableHolder.getInstance().getEntities()){
			if(e instanceof Bomb){
				if(e instanceof Bomb){
					if(e.isDestroy()){
						if(p1.getX() >= ((Bomb) e).getX() - ((Bomb) e).getRange()*60){
							if(p1.getY() >= ((Bomb) e).getY() - ((Bomb) e).getRange()*60){
								p1.setLife(p1.getLife() - 1);
							}
						}
						
						if(p2.getX() >= ((Bomb) e).getX() - ((Bomb) e).getRange()*60){
							if(p2.getY() >= ((Bomb) e).getY() - ((Bomb) e).getRange()*60){
								p2.setLife(p2.getLife() - 1);
							}
						}
					}
				}
			}
			
			if(e instanceof Enemy){
				if(p1.getX() == ((Enemy)e).getX()){
					if(p1.getY() == ((Enemy)e).getY()){
						p1.setLife(p1.getLife());
					}
				}
				
				if(p2.getX() == ((Enemy)e).getX()){
					if(p2.getY() == ((Enemy)e).getY()){
						p2.setLife(p2.getLife());
					}
				}
			}
		}
	}
	
	private boolean checkWin(){
		if(p1.isWin() || p2.isWin()){
			return true;
		}else{
			return false;
		}
	}
	
	private void moveP1(){
		if(CodeUtility.keyPressed.contains(KeyCode.W)){
			p1.setDirection(model.Entity.NORTH);
			p1.setX(p1.getY() - p1.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.A)){
			p1.setDirection(model.Entity.WEST);
			p1.setX(p1.getX() - p1.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.S)){
			p1.setDirection(model.Entity.SOUTH);
			p1.setY(p1.getY() + p1.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.D)){
			p1.setDirection(model.Entity.EAST);
			p1.setX(p1.getX() + p1.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.SPACE)){
			RenderableHolder.getInstance().addAndSort(new Bomb(p1.getX(), p1.getY(), p1, p1.getRange()));
		}
	}
	
	private void moveP2(){
		if(CodeUtility.keyPressed.contains(KeyCode.UP)){
			p2.setDirection(model.Entity.NORTH);
			p2.setX(p2.getY() - p2.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.LEFT)){
			p2.setDirection(model.Entity.WEST);
			p2.setX(p2.getX() - p2.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.DOWN)){
			p2.setDirection(model.Entity.SOUTH);
			p2.setY(p2.getY() + p2.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.RIGHT)){
			p2.setDirection(model.Entity.EAST);
			p2.setX(p2.getX() + p2.getMoveSpeed());
		}else if(CodeUtility.keyPressed.contains(KeyCode.CONTROL)){
			RenderableHolder.getInstance().addAndSort(new Bomb(p2.getX(), p2.getY(), p2, p2.getRange()));
		}
	}
	
	public void receiveKey(KeyCode new_code){
		if(!CodeUtility.keyPressed.contains(new_code)){
			CodeUtility.keyPressed.add(new_code);
		}
	}
	
	public void dropKey(KeyCode new_code){
		if(CodeUtility.keyPressed.contains(new_code)){
			CodeUtility.keyPressed.remove(new_code);
		}
	}
}
