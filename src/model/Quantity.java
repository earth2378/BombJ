package model;

public class Quantity extends Item {

	public Quantity(int x, int y, int direction) {
		super(x, y, direction);
	}
	
	@Override
	public void useItem(Player p) {
		if(life > 0){
			p.setQuantity(p.getQuantity() + 1);
			life--;
		}
	}

}
