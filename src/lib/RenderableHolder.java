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
	public static Image[] bomb1,bomb2;
	public static Image player1Up,player1Down,player1Left,player1Right,player2Up,player2Down,player2Left,player2Right,explodable,permanent,heart,range,quantity,enemyup,enemydown,enemyright,enemyleft,flame1,flame2,plain;
	
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
		
		player1Down = new Image(loader.getResource("img/p1down.gif").toString());
		player1Up = new Image(loader.getResource("img/p1up.gif").toString());
		player1Left = new Image(loader.getResource("img/p1left.gif").toString());
		player1Right = new Image(loader.getResource("img/p1right.gif").toString());
		player2Down = new Image(loader.getResource("img/p2down.gif").toString());
		player2Up = new Image(loader.getResource("img/p2up.gif").toString());
		player2Left = new Image(loader.getResource("img/p2left.gif").toString());
		player2Right = new Image(loader.getResource("img/p2right.gif").toString());
		
		bomb1 = new Image[2];
		bomb2 = new Image[2];
		
		bomb1[0] = new Image(loader.getResource("img/bomb1c1.png").toString());
		bomb1[1] = new Image(loader.getResource("img/bomb1c2.png").toString());
		bomb2[0] = new Image(loader.getResource("img/bomb2c1.png").toString());
		bomb2[1] = new Image(loader.getResource("img/bomb2c2.png").toString());
		
		flame1 = new Image(loader.getResource("img/flame1.png").toString());
		flame2 = new Image(loader.getResource("img/flame2.png").toString());
		plain = new Image(loader.getResource("img/plain.jpg").toString());
		
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
	}
	
	public void sort(){
		Collections.sort(entities, comparator);
	}
	
	
	public List<IRenderableObject> getEntities() {
		return entities;
	}
}
