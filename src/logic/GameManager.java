package logic;

import java.util.Random;

import javafx.scene.input.KeyCode;
import lib.CodeUtility;
import lib.RenderableHolder;
import model.Bomb;
import model.Enemy;
import model.Explodable;
import model.Flame;
import model.Heart;
import model.Item;
import model.Permanent;
import model.Player1;
import model.Player2;
import model.Quantity;
import model.Range;

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
		
		//Enemy
		e1 = new Enemy(600, 0, model.Entity.SOUTH);
		e2 = new Enemy(0, 600, model.Entity.NORTH);
		RenderableHolder.getInstance().add(e1);
		RenderableHolder.getInstance().add(e2);
		
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
		flameAround();
		moveEnemy();
		checkItem();
		if(checkWin()){
			
		}
		
		removeDestroyEntity();
	}
	
	private void removeDestroyEntity(){
		for(int i=RenderableHolder.getInstance().getEntities().size()-1; i>=0; i--){
			if(RenderableHolder.getInstance().getEntities().get(i).isDestroy()){
				if(RenderableHolder.getInstance().getEntities().get(i) instanceof Explodable){
					int x = (((Explodable)RenderableHolder.getInstance().getEntities().get(i)).getX()/60);
					int y = (((Explodable)RenderableHolder.getInstance().getEntities().get(i)).getY()/60);
					
					field.setField(x, y, 0);
				}
				RenderableHolder.getInstance().getEntities().remove(i);
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
				if(p1.getBombCount()<p1.getQuantity()){
					RenderableHolder.getInstance().addAndSort(new Bomb(p1.getX(), p1.getY(), p1, p1.getRange()));
					field.setField(p1.getX()/60, p1.getY()/60, 1);
					p1.setBombCount(p1.getBombCount()+1);
				}
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
				if(p2.getBombCount() < p2.getQuantity()){
					RenderableHolder.getInstance().addAndSort(new Bomb(p2.getX(), p2.getY(), p2, p2.getRange()));
					field.setField(p2.getX()/60, p2.getY()/60, 1);
					p2.setBombCount(p2.getBombCount()+1);
				}
			}
		}
	}

	private synchronized void moveBomb(){
		for(int i=0;i<RenderableHolder.getInstance().getEntities().size();i++){
			if(RenderableHolder.getInstance().getEntities().get(i) instanceof Bomb){
				if(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).isAfterboom() == 1){
					field.setField(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getX()/60, ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getY()/60, 0);
					((Bomb) RenderableHolder.getInstance().getEntities().get(i)).setDestroy(true);
					((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getPlayer().setBombCount(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getPlayer().getBombCount()-1);
				}
				
				if(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getCount() < 15){
					((Bomb) RenderableHolder.getInstance().getEntities().get(i)).setCount(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getCount() + 1);
				}else{
					((Bomb) RenderableHolder.getInstance().getEntities().get(i)).setBoom(true);
					((Bomb) RenderableHolder.getInstance().getEntities().get(i)).setAfterboom(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).isAfterboom()+1);
					for(int j=1; j<((Bomb)RenderableHolder.getInstance().getEntities().get(i)).getRange()+1;j++){
						int x1 = ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getX()+j*60;
						if(x1>=0 && x1<=600){
							RenderableHolder.getInstance().add(new Flame(x1, ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getY(), ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getPlayer()));
							if(field.getField(x1/60, ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getY()/60) == 1){
								break;
							}
						}
					}
					
					for(int j=1; j<((Bomb)RenderableHolder.getInstance().getEntities().get(i)).getRange()+1;j++){
						int x2 = ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getX()-j*60;
						if(x2>=0 && x2<=600){
							RenderableHolder.getInstance().add(new Flame(x2, ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getY(), ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getPlayer()));
							if(field.getField(x2/60, ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getY()/60) == 1){
								break;
							}
						}
					}
					
					for(int j=1; j<((Bomb)RenderableHolder.getInstance().getEntities().get(i)).getRange()+1;j++){
						int y1 = ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getY()+j*60;
						if(y1>=0 && y1<=600){
							RenderableHolder.getInstance().add(new Flame(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getX(), y1, ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getPlayer()));
							if(field.getField(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getX()/60, y1/60) == 1){
								break;
							}
						}
					}
					
					for(int j=1; j<((Bomb)RenderableHolder.getInstance().getEntities().get(i)).getRange()+1;j++){
						int y2 = ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getY()-j*60;
						if(y2>=0 && y2<=600){
							RenderableHolder.getInstance().add(new Flame(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getX(), y2, ((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getPlayer()));
							if(field.getField(((Bomb) RenderableHolder.getInstance().getEntities().get(i)).getX()/60, y2/60) == 1){
								break;
							}
						}
					}
					
				}
			}
		}
		
	}
	
	private void flameAround(){
		for(int i=0;i<RenderableHolder.getInstance().getEntities().size();i++){
			if(RenderableHolder.getInstance().getEntities().get(i) instanceof  Flame){
				if(((Flame) RenderableHolder.getInstance().getEntities().get(i)).getAfterburn() == 1){
					((Flame) RenderableHolder.getInstance().getEntities().get(i)).setDestroy(true);
					for(int j=0;j<RenderableHolder.getInstance().getEntities().size();j++){
							if(RenderableHolder.getInstance().getEntities().get(j) instanceof Player1){
								if(((Player1)RenderableHolder.getInstance().getEntities().get(j)).getX() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getX()
										&&((Player1)RenderableHolder.getInstance().getEntities().get(j)).getY() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getY()){
									((Player1)RenderableHolder.getInstance().getEntities().get(j)).setLife(((Player1)RenderableHolder.getInstance().getEntities().get(j)).getLife()-1);
									if(((Player1)RenderableHolder.getInstance().getEntities().get(j)).isDestroy()){
										p2.setScore(p2.getScore() + 2000);
									}
								}
							}else if(RenderableHolder.getInstance().getEntities().get(j) instanceof Player2){
								if(((Player2)RenderableHolder.getInstance().getEntities().get(j)).getX() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getX()
										&&((Player2)RenderableHolder.getInstance().getEntities().get(j)).getY() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getY()){
									((Player2)RenderableHolder.getInstance().getEntities().get(j)).setLife(((Player2)RenderableHolder.getInstance().getEntities().get(j)).getLife()-1);
									if(((Player2)RenderableHolder.getInstance().getEntities().get(j)).isDestroy()){
										p1.setScore(p1.getScore() + 2000);
									}
								}
							}else if(RenderableHolder.getInstance().getEntities().get(j) instanceof Explodable){
								if(((Explodable)RenderableHolder.getInstance().getEntities().get(j)).getX() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getX()
										&&((Explodable)RenderableHolder.getInstance().getEntities().get(j)).getY() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getY()){
									((Explodable)RenderableHolder.getInstance().getEntities().get(j)).setLife(((Explodable)RenderableHolder.getInstance().getEntities().get(j)).getLife()-1);
									if(((Flame) RenderableHolder.getInstance().getEntities().get(i)).getPlayer() == p1){
										p1.setScore(p1.getScore() + 100);
									}else if(((Flame) RenderableHolder.getInstance().getEntities().get(i)).getPlayer() == p2){
										p2.setScore(p2.getScore() + 100);
									}
									randomItem(((Explodable)RenderableHolder.getInstance().getEntities().get(j)).getX(), ((Explodable)RenderableHolder.getInstance().getEntities().get(j)).getY());
								}
							}else if(RenderableHolder.getInstance().getEntities().get(j) instanceof Enemy){
								if(((Enemy)RenderableHolder.getInstance().getEntities().get(j)).getX() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getX()
										&&((Enemy)RenderableHolder.getInstance().getEntities().get(j)).getY() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getY()){
									if(((Flame) RenderableHolder.getInstance().getEntities().get(i)).getPlayer() == p1){
										p1.setScore(p1.getScore() + 500);
									}else if(((Flame) RenderableHolder.getInstance().getEntities().get(i)).getPlayer() == p2){
										p2.setScore(p2.getScore() + 500);
									}
									((Enemy)RenderableHolder.getInstance().getEntities().get(j)).setDestroy(true);
								}
							}else if(RenderableHolder.getInstance().getEntities().get(j) instanceof Item){
								if(((Item)RenderableHolder.getInstance().getEntities().get(j)).getX() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getX()
										&&((Item)RenderableHolder.getInstance().getEntities().get(j)).getY() == ((Flame)RenderableHolder.getInstance().getEntities().get(i)).getY()){
									((Item)RenderableHolder.getInstance().getEntities().get(j)).setLife(((Item)RenderableHolder.getInstance().getEntities().get(j)).getLife() - 1);
								}
							}
						
					}
					
				}else {
					((Flame) RenderableHolder.getInstance().getEntities().get(i)).setAfterburn(((Flame) RenderableHolder.getInstance().getEntities().get(i)).getAfterburn()+1);
					
				}
			}
			
		}
	}
	
	private void moveEnemy(){
		
		if(!e1.isDestroy()){
			e1.move();
		}
		
		if(!e2.isDestroy()){
			e2.move();
		}
		if(!e1.isDestroy()){
			if(e1.getX()==p1.getX() && e1.getY() == p1.getY()){
				e1.attackPlayer(p1);
			}else if(e1.getX()==p2.getX() && e1.getY() == p2.getY()){
				e1.attackPlayer(p2);
			}
		}
		if(!e2.isDestroy()){
			if(e2.getX()==p1.getX() && e2.getY() == p1.getY()){
				e2.attackPlayer(p1);
			}else if(e2.getX()==p2.getX() && e2.getY() == p2.getY()){
				e2.attackPlayer(p2);
			}
		}
		
	}
	
	private void randomItem(int x,int y){
		Random rand = new Random();
		int drop = rand.nextInt(100);
		System.out.println(drop);
		if(drop<=10){
			RenderableHolder.getInstance().add(new Range(x, y,model.Entity.SOUTH));
		}else if(drop <=20){
			RenderableHolder.getInstance().add(new Heart(x, y, model.Entity.SOUTH));
		}else if(drop<=30){
			RenderableHolder.getInstance().getEntities().add(new Quantity(x, y, model.Entity.SOUTH));
		}
	}
	
	private void checkItem(){
		for(int i=0;i<RenderableHolder.getInstance().getEntities().size();i++){
			if(RenderableHolder.getInstance().getEntities().get(i) instanceof Item){
				if(((Item)RenderableHolder.getInstance().getEntities().get(i)).getX() == p1.getX()
						&& ((Item)RenderableHolder.getInstance().getEntities().get(i)).getY() == p1.getY()){
					((Item)RenderableHolder.getInstance().getEntities().get(i)).useItem(p1);
				}else if(((Item)RenderableHolder.getInstance().getEntities().get(i)).getX() == p2.getX()
						&& ((Item)RenderableHolder.getInstance().getEntities().get(i)).getY() == p2.getY()){
					((Item)RenderableHolder.getInstance().getEntities().get(i)).useItem(p2);
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
