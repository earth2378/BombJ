package model;

public class Range extends Item{

	public Range(int x, int y, int direction) {
		super(x, y, direction);
	}

	@Override
	public void useItem(Player p) {
		if(life > 0){
			p.setRange(p.getRange() + 1);
			life--;
		}
	}
	
}
