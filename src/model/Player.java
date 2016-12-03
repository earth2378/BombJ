package model;

public abstract class Player extends Entity{
	
	// moving delay is required
	private int life,score,range,quantity,movingDelay;
	private int movingDelayCounter = 0;
	private boolean isWin;
	protected String name;
	
	public Player(int x,int y, int direction,String name){
		super(x,y,direction);
		this.life = 3;
		this.score = 0;
		this.range = 1;
		this.quantity = 1;
		this.isDestroy = false;
		this.isWin = false;
		this.name = name;
		
	}
	
	public int getLife(){
		return life;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getRange(){
		return range;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public boolean isWin(){
		return isWin;
	}
	
	public String getName(){
		return name;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	
	
	
}
