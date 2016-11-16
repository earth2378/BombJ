package model;

public class Heart extends Item {
	
	public Heart(int x, int y, int direction) {
		super(x, y, direction);
	}
	
	@Override
	public void useItem(Player p) {
		if(life > 0){
			p.setLife(p.getLife() + life);
			life--;
		}
	}
	
}
