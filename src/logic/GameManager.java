package logic;

import java.util.Random;

import com.sun.xml.internal.stream.Entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import lib.CodeUtility;
import lib.IRenderableObject;
import lib.RenderableHolder;
import model.Bomb;
import model.Enemy;
import model.Explodable;
import model.Flame;
import model.Permanent;
import model.Player1;
import model.Player2;

public class GameManager {
	
	private Player1 p1;
	private Player2 p2;
	private Enemy e1;
	private Enemy e2;
	private Field field;
	public static GameManager instance;
	
	public GameManager(){
		
		instance = this;
		
		//field
		field = new Field();
		
		// player
		p1 = new Player1(0,0,model.Entity.SOUTH,"Player1");
		p2 = new Player2(600, 600,model.Entity.NORTH, "Player2");
		RenderableHolder.getInstance().add(p1);
		RenderableHolder.getInstance().add(p2);
		
		//Permanent
		for(int i=60; i<660; i=i+120 ){
			for(int j=60; j<660; j=j+120 ){
				RenderableHolder.getInstance().add(new Permanent(i,j));
				field.setField((i/60), (j/60), 1);
			}
		}
		
		//Explode
		for(int i=120; i<540; i=i+60){
			for(int j=0; j<660;j=j+600){
				RenderableHolder.getInstance().add(new Explodable(i,j));
				field.setField((i/60), (j/60), 1);
			}
		}
		
		for(int i=120; i<540; i=i+60){
			for(int j=0; j<660;j=j+600){
				RenderableHolder.getInstance().add(new Explodable(j,i));
				field.setField((j/60), (i/60), 1);
			}
		}
		
		for(int i=60; i<=540; i+=60){
			for(int j=120; j<=480; j+=120){
				RenderableHolder.getInstance().add(new Explodable(i,j));
				field.setField((i/60), (j/60), 1);
			}
		}
		
		for(int i=120; i<=480; i+=120){
			for(int j=60; j<=540; j+=120){
				RenderableHolder.getInstance().add(new Explodable(i,j));
				field.setField((i/60), (j/60), 1);
			}
		}
		
	}
	
	public void update(){
		moveP1();
		moveP2();
		moveBomb();
		
		if(checkWin()){
			
		}
		
		checkCollision();
		removeDestroyEntity();
	}
	
	private void removeDestroyEntity(){
		for(int i=RenderableHolder.getInstance().getEntities().size()-1; i>=0; i--){
			if(RenderableHolder.getInstance().getEntities().get(i).isDestroy()){
				if(RenderableHolder.getInstance().getEntities().get(i) instanceof Explodable){
					int x = (((Explodable)RenderableHolder.getInstance().getEntities().get(i)).getX());
					int y = (((Explodable)RenderableHolder.getInstance().getEntities().get(i)).getY()/60);
					
					field.setField(x, y, 0);
				}
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
						((Enemy) e).attackPlayer(p1);
					}
				}
				
				if(p2.getX() == ((Enemy)e).getX()){
					if(p2.getY() == ((Enemy)e).getY()){
						((Enemy) e).attackPlayer(p2);
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
		if(!p1.isDestroy()){
			if(CodeUtility.keyPressed.contains(KeyCode.W)){
				p1.setDirection(model.Entity.NORTH);
				int y = ((p1.getY() - 60)/60);
				int x = (p1.getX()/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p1.setY(p1.getY() - p1.getMoveSpeed());
						}
					}
				}
				
			}else if(CodeUtility.keyPressed.contains(KeyCode.A)){
				p1.setDirection(model.Entity.WEST);
				int y = ((p1.getY())/60);
				int x = ((p1.getX()-60)/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p1.setX(p1.getX() - p1.getMoveSpeed());
						}
					}
				}
				
			}else if(CodeUtility.keyPressed.contains(KeyCode.S)){
				p1.setDirection(model.Entity.SOUTH);
				int y = ((p1.getY() + 60)/60);
				int x = (p1.getX()/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p1.setY(p1.getY() + p1.getMoveSpeed());
						}
					}
				}
				
			}else if(CodeUtility.keyPressed.contains(KeyCode.D)){
				p1.setDirection(model.Entity.EAST);
				int y = ((p1.getY())/60);
				int x = ((p1.getX()+60)/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p1.setX(p1.getX() + p1.getMoveSpeed());
						}
					}
				}
				
			}else if(CodeUtility.keyPressed.contains(KeyCode.SPACE)){
				RenderableHolder.getInstance().addAndSort(new Bomb(p1.getX(), p1.getY(), p1, p1.getRange()));
				field.setField(p1.getX()/60, p1.getY()/60, 1);
			}
		}
	}
	
	private void moveP2(){
		if(!p2.isDestroy()){
			if(CodeUtility.keyPressed.contains(KeyCode.UP)){
				p2.setDirection(model.Entity.NORTH);
				int y = ((p2.getY() - 60)/60);
				int x = (p2.getX()/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p2.setY(p2.getY() - p2.getMoveSpeed());
						}
					}
				}
			}else if(CodeUtility.keyPressed.contains(KeyCode.LEFT)){
				p2.setDirection(model.Entity.WEST);
				int y = ((p2.getY())/60);
				int x = ((p2.getX()-60)/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p2.setX(p2.getX() - p2.getMoveSpeed());
						}
					}
				}
			}else if(CodeUtility.keyPressed.contains(KeyCode.DOWN)){
				p2.setDirection(model.Entity.SOUTH);
				int y = ((p2.getY() + 60)/60);
				int x = (p2.getX()/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p2.setY(p2.getY() + p2.getMoveSpeed());
						}
					}
				}
			}else if(CodeUtility.keyPressed.contains(KeyCode.RIGHT)){
				p2.setDirection(model.Entity.EAST);
				int y = ((p2.getY())/60);
				int x = ((p2.getX()+60)/60);
				if(x >=0 && x <=10){
					if(y>=0 && y<=10){
						if(field.getField(x, y) == 0){
							p2.setX(p2.getX() + p2.getMoveSpeed());
						}
					}
				}
			}else if(CodeUtility.keyPressed.contains(KeyCode.CONTROL)){
				RenderableHolder.getInstance().addAndSort(new Bomb(p2.getX(), p2.getY(), p2, p2.getRange()));
				field.setField(p2.getX()/60, p2.getY()/60, 1);
			}
		}
	}

	private void moveBomb(){
		for(IRenderableObject i : RenderableHolder.getInstance().getEntities()){
			if(i instanceof Bomb){
				if(((Bomb) i).getCount() < 3){
					((Bomb) i).setCount(((Bomb) i).getCount() + 1);
				}else{
					((Bomb) i).setBoom(true);
					for(int j=1; j<((Bomb)i).getRange()+1;j++){
						int x1 = ((Bomb) i).getX()+j*60;
						if(x1>=0 && x1<=600){
							RenderableHolder.getInstance().add(new Flame(x1, ((Bomb) i).getY(), ((Bomb) i).getPlayer()));
							if(field.getField(x1/60, ((Bomb) i).getY()/60) == 1){
								break;
							}
						}
					}
					
					for(int j=1; j<((Bomb)i).getRange()+1;j++){
						int x2 = ((Bomb) i).getX()-j*60;
						if(x2>=0 && x2<=600){
							RenderableHolder.getInstance().add(new Flame(x2, ((Bomb) i).getY(), ((Bomb) i).getPlayer()));
							if(field.getField(x2/60, ((Bomb) i).getY()/60) == 1){
								break;
							}
						}
					}
					
					for(int j=1; j<((Bomb)i).getRange()+1;j++){
						int y1 = ((Bomb) i).getY()+j*60;
						if(y1>=0 && y1<=600){
							RenderableHolder.getInstance().add(new Flame(((Bomb) i).getX(), y1, ((Bomb) i).getPlayer()));
							if(field.getField(((Bomb) i).getX()/60, y1/60) == 1){
								break;
							}
						}
					}
					
					for(int j=1; j<((Bomb)i).getRange()+1;j++){
						int y2 = ((Bomb) i).getY()-j*60;
						if(y2>=0 && y2<=600){
							RenderableHolder.getInstance().add(new Flame(((Bomb) i).getX(), y2, ((Bomb) i).getPlayer()));
							if(field.getField(((Bomb) i).getX()/60, y2/60) == 1){
								break;
							}
						}
					}
				}
			}
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
	
	public Field myField(){
		return field;
	}
}
