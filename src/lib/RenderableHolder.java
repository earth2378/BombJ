package lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import lib.RenderableHolder;
import lib.IRenderableObject;

public class RenderableHolder {
	
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderableObject> entities;
	private Comparator<IRenderableObject> comparator;
	private static Image[] player1Up,player1Down,player1Left,player1Right,player2Up,player2Down,player2Left,player2Right,bomb;
	private static Image explodable,permanent,heart,range,quantity,enemyup,enemydown,enemyright,enemyleft;
	
	public RenderableHolder(){
		entities = new ArrayList<>();
		comparator = (IRenderableObject o1, IRenderableObject o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}
	
	static {
		loadResource();
	}
	
	public static void loadResource(){
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		explodable = new Image(loader.getResource("img/explodable.png").toString());
		permanent = new Image(loader.getResource("img/permanent.png").toString());
		heart = new Image(loader.getResource("img/heart.png").toString());
		range = new Image(loader.getResource("img/range.png").toString());
		quantity = new Image(loader.getResource("img/quantity.png").toString());
		enemyup = new Image(loader.getResource("img/enemyup.png").toString());
		enemydown = new Image(loader.getResource("img/enemydown.png").toString());
		enemyright = new Image(loader.getResource("img/enemyright.png").toString());
		enemyleft = new Image(loader.getResource("img/enemyleft.png").toString());
		
		player1Up = new Image[4];
		player1Down = new Image[4];
		player1Left = new Image[4];
		player1Right = new Image[4];
		player2Up = new Image[4];
		player2Down = new Image[4];
		player2Left = new Image[4];
		player2Right = new Image[4];
		
		player1Down[0] = new Image(loader.getResource("img/player1down1.png").toString());
		player1Down[1] = new Image(loader.getResource("img/player1down2.png").toString());
		player1Down[2] = new Image(loader.getResource("img/player1down3.png").toString());
		player1Down[3] = new Image(loader.getResource("img/player1down4.png").toString());
		player1Up[0] = new Image(loader.getResource("img/player1up1.png").toString());
		player1Up[1] = new Image(loader.getResource("img/player1up2.png").toString());
		player1Up[2] = new Image(loader.getResource("img/player1up3.png").toString());
		player1Up[3] = new Image(loader.getResource("img/player1up4.png").toString());
		player1Left[0] = new Image(loader.getResource("img/player1left1.png").toString());
		player1Left[1] = new Image(loader.getResource("img/player1left2.png").toString());
		player1Left[2] = new Image(loader.getResource("img/player1left3.png").toString());
		player1Left[3] = new Image(loader.getResource("img/player1left4.png").toString());
		player1Right[0] = new Image(loader.getResource("img/player1right1.png").toString());
		player1Right[1] = new Image(loader.getResource("img/player1right2.png").toString());
		player1Right[2] = new Image(loader.getResource("img/player1right3.png").toString());
		player1Right[3] = new Image(loader.getResource("img/player1right4.png").toString());
		
		player2Down[0] = new Image(loader.getResource("img/player2down1.png").toString());
		player2Down[1] = new Image(loader.getResource("img/player2down2.png").toString());
		player2Down[2] = new Image(loader.getResource("img/player2down3.png").toString());
		player2Down[3] = new Image(loader.getResource("img/player2down4.png").toString());
		player2Up[0] = new Image(loader.getResource("img/player2up1.png").toString());
		player2Up[1] = new Image(loader.getResource("img/player2up2.png").toString());
		player2Up[2] = new Image(loader.getResource("img/player2up3.png").toString());
		player2Up[3] = new Image(loader.getResource("img/player2up4.png").toString());
		player2Left[0] = new Image(loader.getResource("img/player2left1.png").toString());
		player2Left[1] = new Image(loader.getResource("img/player2left2.png").toString());
		player2Left[2] = new Image(loader.getResource("img/player2left3.png").toString());
		player2Left[3] = new Image(loader.getResource("img/player2left4.png").toString());
		player2Right[0] = new Image(loader.getResource("img/player2right1.png").toString());
		player2Right[1] = new Image(loader.getResource("img/player2right2.png").toString());
		player2Right[2] = new Image(loader.getResource("img/player2right3.png").toString());
		player2Right[3] = new Image(loader.getResource("img/player2right4.png").toString());
		
	}
	
	public static RenderableHolder getInstance() {
		return instance;
	}
	
	public void addAndSort(IRenderableObject entity) {
		add(entity);
		sort();
	}
	
	public void add(IRenderableObject entity) {
		entities.add(entity);
		sort();
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}
	
	
	public List<IRenderableObject> getEntities() {
		return entities;
	}
}
