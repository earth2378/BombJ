package lib;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lab5_lib.lib.ConfigurableOption;
import lab5_lib.renderableObject.Gun;

public class DrawingUtility{

	protected static final Font standardFont = Font.font("Tahoma", FontWeight.BOLD, 30);
	protected static final Font smallFont = Font.font("Tahoma", FontWeight.MEDIUM, 9);
	protected static final Image bg = getImage("img/bg.jpg");
	protected static final Image gun = getImage("img/gun.png");
	protected static final Image gun_inf = getImage("img/gun_inf.png");
	protected static final Image shootAnim = getImage("img/shootAnim.png");

	protected static final double transcluentWhite = 0.7;
	protected static final double opaque = 1;

	private static Image getImage(String directory) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Image tmp = new Image(loader.getResource(directory).toString());	
		return tmp;
	}

	public static Image getShootanim(){
		return shootAnim;
	}
	
	private static void drawTranscluentWhite(GraphicsContext gc, int x, int y, int radius) {
		gc.setGlobalAlpha(transcluentWhite);
		gc.setFill(Color.WHITE);
		gc.fillOval(x - radius - 2, y - radius -2 , 2* (radius+2) , 2* (radius+2));
		gc.setGlobalAlpha(opaque);
	}

	public static void drawShootableObject(GraphicsContext gc, int x, int y, int radius, String name,
			boolean isPointerOver) {

		if(name.equals("simple")){
			gc.setFill(Color.BLUE);
		}else if(name.equals("splitter")){
			gc.setFill(Color.RED);
		}else if(name.equals("small")){
			gc.setFill(Color.YELLOW);
		}
		
		gc.setLineWidth(2);
		gc.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
		gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		
		if(isPointerOver){
			drawTranscluentWhite(gc, x, y, radius);
		}
		
	}

	public static void drawItemGun(GraphicsContext gc, int x, int y, int radius, String name, boolean isPointerOver) {
		
		gc.setFill(Color.GRAY);
		gc.setLineWidth(2);
		gc.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
		gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		
		if(name.equals("gun")){
			gc.drawImage(gun, x -15, y -15);
		}else if(name.equals("gun_inf")){
			gc.drawImage(gun_inf, x -15, y -15);
		}
		
		if(isPointerOver){
			drawTranscluentWhite(gc, x, y, radius);
		}
	}

	public static void drawItemBullet(GraphicsContext gc, int x, int y, int radius, boolean isPointerOver) {
		
		gc.setFill(Color.GRAY);
		gc.setLineWidth(2);
		gc.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
		gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		
		gc.setFill(Color.BLACK);
		
		gc.fillRect(x - 20, y - 10, 20, 20);
		gc.fillOval(x - 20, y - 10, 40, 20);
		
		if(isPointerOver){
			drawTranscluentWhite(gc, x, y, radius);
		}
		
	}

	public static void drawIconGun(GraphicsContext gc, int bulletQuantity, int maxBullet, boolean isInfiniteBullet) {
		if (gun == null || (isInfiniteBullet && gun_inf == null))
			return;
		gc.setFont(DrawingUtility.smallFont);
		gc.setFill(Color.WHITE);
		if (isInfiniteBullet) {
			gc.drawImage(gun_inf, ConfigurableOption.screenWidth/2 - 15, 5);
		} else {
			gc.drawImage(gun, ConfigurableOption.screenWidth/2 - 15, 5);
			gc.fillText(bulletQuantity + "/" + maxBullet, (ConfigurableOption.screenWidth/2) , 30);
		}
	}

	public static void drawStatusBar(GraphicsContext gc, int remainingSecond, int score, Gun gun, boolean pause) {
		
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, ConfigurableOption.screenWidth, 40);
		if(gun != null){
			gun.render(gc);
		}
		
		gc.setFill(Color.WHITE);
		gc.setFont(standardFont);
		gc.fillText("TIME : " + remainingSecond , 5, 35);
		gc.fillText("SCORE : " + score, (ConfigurableOption.screenWidth / 2) + 50 , 35);

		if(pause){
			gc.fillText("PAUSE", ConfigurableOption.screenWidth/2 - 55, ConfigurableOption.screenHeight/2 + 15);
		}
		
	}

	public static GameAnimation createShootingAnimationAt(int x, int y) {
		GameAnimation anim = new GameAnimation(DrawingUtility.shootAnim, 7, 1);
		anim.centerAnimationAt(x, y);
		anim.play();
		return anim;
	}

}
