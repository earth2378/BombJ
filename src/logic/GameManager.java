package logic;

import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import lib.AudioUtility;
import lib.CodeUtility;
import lib.IRenderableObject;
import lib.RenderableHolder;
import lib.WinException;
import main.Main;
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

	public GameManager() {

		instance = this;

		// field
		field = new Field();

		// Enemy
		e1 = new Enemy(600, 0, model.Entity.SOUTH);
		e2 = new Enemy(0, 600, model.Entity.NORTH);
		RenderableHolder.getInstance().add(e1);
		RenderableHolder.getInstance().add(e2);

		// player
		p1 = new Player1(0, 0, model.Entity.SOUTH, "Player1");
		p2 = new Player2(600, 600, model.Entity.NORTH, "Player2");
		RenderableHolder.getInstance().add(p1);
		RenderableHolder.getInstance().add(p2);

		// Permanent
		for (int i = 60; i < 660; i = i + 120) {
			for (int j = 60; j < 660; j = j + 120) {
				RenderableHolder.getInstance().add(new Permanent(i, j));
				field.setField((i / 60), (j / 60), 1);
			}
		}

		// Explode
		for (int i = 120; i < 540; i = i + 60) {
			for (int j = 0; j < 660; j = j + 600) {
				RenderableHolder.getInstance().add(new Explodable(i, j));
				field.setField((i / 60), (j / 60), 1);
			}
		}

		for (int i = 120; i < 540; i = i + 60) {
			for (int j = 0; j < 660; j = j + 600) {
				RenderableHolder.getInstance().add(new Explodable(j, i));
				field.setField((j / 60), (i / 60), 1);
			}
		}

		for (int i = 60; i <= 540; i += 60) {
			for (int j = 120; j <= 480; j += 120) {
				RenderableHolder.getInstance().add(new Explodable(i, j));
				field.setField((i / 60), (j / 60), 1);
			}
		}

		for (int i = 120; i <= 480; i += 120) {
			for (int j = 60; j <= 540; j += 120) {
				RenderableHolder.getInstance().add(new Explodable(i, j));
				field.setField((i / 60), (j / 60), 1);
			}
		}

	}

	public void update() {
		moveP1();
		moveP2();
		moveBomb();
		flameAround();
		moveEnemy();
		checkItem();
		checkWin();
		removeDestroyEntity();
	}

	private void removeDestroyEntity() {
		List<IRenderableObject> entities = RenderableHolder.getInstance().getEntities();
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroy()) {
				if (entities.get(i) instanceof Explodable) {
					int x = (((Explodable) entities.get(i)).getX() / 60);
					int y = (((Explodable) entities.get(i)).getY() / 60);
					field.setField(x, y, 0);
				}
				entities.remove(i);
			}
		}
	}

	private void checkWin() {
		if (p1.isDestroy() || p2.isDestroy()) {
			if (p1.isDestroy() && !p2.isDestroy()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.instance.setPause(true);
						try {
							throw new WinException(p2);
						} catch (WinException e) {
						}
					}
				});
			} else if (p2.isDestroy() && !p1.isDestroy()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.instance.setPause(true);
						try {
							throw new WinException(p1);
						} catch (WinException e) {
						}
					}
				});
			} else if (p1.isDestroy() && p2.isDestroy()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Main.instance.setPause(true);
						try {
							throw new WinException(p1, p2);
						} catch (WinException e) {
						}
					}
				});
			}
		}
	}

	private void moveP1() {
		if (!p1.isDestroy()) {
			if (CodeUtility.keyPressed.contains(KeyCode.W)) {
				p1.setDirection(model.Entity.NORTH);
				int y = ((p1.getY() - 60) / 60);
				int x = (p1.getX() / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p1.setY(p1.getY() - p1.getMoveSpeed());
						}
					}
				}

			} else if (CodeUtility.keyPressed.contains(KeyCode.A)) {
				p1.setDirection(model.Entity.WEST);
				int y = ((p1.getY()) / 60);
				int x = ((p1.getX() - 60) / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p1.setX(p1.getX() - p1.getMoveSpeed());
						}
					}
				}

			} else if (CodeUtility.keyPressed.contains(KeyCode.S)) {
				p1.setDirection(model.Entity.SOUTH);
				int y = ((p1.getY() + 60) / 60);
				int x = (p1.getX() / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p1.setY(p1.getY() + p1.getMoveSpeed());
						}
					}
				}

			} else if (CodeUtility.keyPressed.contains(KeyCode.D)) {
				p1.setDirection(model.Entity.EAST);
				int y = ((p1.getY()) / 60);
				int x = ((p1.getX() + 60) / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p1.setX(p1.getX() + p1.getMoveSpeed());
						}
					}
				}

			} else if (CodeUtility.keyPressed.contains(KeyCode.SPACE)
					&& !CodeUtility.keyTriggerd.contains(KeyCode.SPACE)) {
				if (p1.getBombCount() < p1.getQuantity()) {
					RenderableHolder.getInstance().addAndSort(new Bomb(p1.getX(), p1.getY(), p1, p1.getRange()));
					field.setField(p1.getX() / 60, p1.getY() / 60, 1);
					p1.setBombCount(p1.getBombCount() + 1);
					CodeUtility.keyTriggerd.add(KeyCode.SPACE);
				}
			}
		}
	}

	private void moveP2() {
		if (!p2.isDestroy()) {
			if (CodeUtility.keyPressed.contains(KeyCode.UP)) {
				p2.setDirection(model.Entity.NORTH);
				int y = ((p2.getY() - 60) / 60);
				int x = (p2.getX() / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p2.setY(p2.getY() - p2.getMoveSpeed());
						}
					}
				}
			} else if (CodeUtility.keyPressed.contains(KeyCode.LEFT)) {
				p2.setDirection(model.Entity.WEST);
				int y = ((p2.getY()) / 60);
				int x = ((p2.getX() - 60) / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p2.setX(p2.getX() - p2.getMoveSpeed());
						}
					}
				}
			} else if (CodeUtility.keyPressed.contains(KeyCode.DOWN)) {
				p2.setDirection(model.Entity.SOUTH);
				int y = ((p2.getY() + 60) / 60);
				int x = (p2.getX() / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p2.setY(p2.getY() + p2.getMoveSpeed());
						}
					}
				}
			} else if (CodeUtility.keyPressed.contains(KeyCode.RIGHT)) {
				p2.setDirection(model.Entity.EAST);
				int y = ((p2.getY()) / 60);
				int x = ((p2.getX() + 60) / 60);
				if (x >= 0 && x <= 10) {
					if (y >= 0 && y <= 10) {
						if (field.getField(x, y) == 0) {
							p2.setX(p2.getX() + p2.getMoveSpeed());
						}
					}
				}
			} else if (CodeUtility.keyPressed.contains(KeyCode.CONTROL)) {
				if (p2.getBombCount() < p2.getQuantity()) {
					RenderableHolder.getInstance().addAndSort(new Bomb(p2.getX(), p2.getY(), p2, p2.getRange()));
					field.setField(p2.getX() / 60, p2.getY() / 60, 1);
					p2.setBombCount(p2.getBombCount() + 1);
				}
			}
		}
	}

	private synchronized void moveBomb() {
		List<IRenderableObject> entities = RenderableHolder.getInstance().getEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Bomb) {
				if (((Bomb) entities.get(i)).isAfterboom() == 1) {
					field.setField(((Bomb) entities.get(i)).getX() / 60, ((Bomb) entities.get(i)).getY() / 60, 0);
					((Bomb) entities.get(i)).setDestroy(true);
					((Bomb) entities.get(i)).getPlayer()
							.setBombCount(((Bomb) entities.get(i)).getPlayer().getBombCount() - 1);
					AudioUtility.playSound("boom");
				}

				if (((Bomb) entities.get(i)).getCount() < 12) {
					((Bomb) entities.get(i)).setCount(((Bomb) entities.get(i)).getCount() + 1);
				} else {
					((Bomb) entities.get(i)).setBoom(true);
					((Bomb) entities.get(i)).setAfterboom(((Bomb) entities.get(i)).isAfterboom() + 1);
					for (int j = 1; j < ((Bomb) entities.get(i)).getRange() + 1; j++) {
						int x1 = ((Bomb) entities.get(i)).getX() + j * 60;
						if (x1 >= 0 && x1 <= 600) {
							RenderableHolder.getInstance().add(new Flame(x1, ((Bomb) entities.get(i)).getY(),
									((Bomb) entities.get(i)).getPlayer()));
							if (field.getField(x1 / 60, ((Bomb) entities.get(i)).getY() / 60) == 1) {
								break;
							}
						}
					}

					for (int j = 1; j < ((Bomb) entities.get(i)).getRange() + 1; j++) {
						int x2 = ((Bomb) entities.get(i)).getX() - j * 60;
						if (x2 >= 0 && x2 <= 600) {
							RenderableHolder.getInstance().add(new Flame(x2, ((Bomb) entities.get(i)).getY(),
									((Bomb) entities.get(i)).getPlayer()));
							if (field.getField(x2 / 60, ((Bomb) entities.get(i)).getY() / 60) == 1) {
								break;
							}
						}
					}

					for (int j = 1; j < ((Bomb) entities.get(i)).getRange() + 1; j++) {
						int y1 = ((Bomb) entities.get(i)).getY() + j * 60;
						if (y1 >= 0 && y1 <= 600) {
							RenderableHolder.getInstance().add(new Flame(((Bomb) entities.get(i)).getX(), y1,
									((Bomb) entities.get(i)).getPlayer()));
							if (field.getField(((Bomb) entities.get(i)).getX() / 60, y1 / 60) == 1) {
								break;
							}
						}
					}

					for (int j = 1; j < ((Bomb) entities.get(i)).getRange() + 1; j++) {
						int y2 = ((Bomb) entities.get(i)).getY() - j * 60;
						if (y2 >= 0 && y2 <= 600) {
							RenderableHolder.getInstance().add(new Flame(((Bomb) entities.get(i)).getX(), y2,
									((Bomb) entities.get(i)).getPlayer()));
							if (field.getField(((Bomb) entities.get(i)).getX() / 60, y2 / 60) == 1) {
								break;
							}
						}
					}
				}
			}
		}

	}

	private void flameAround() {
		List<IRenderableObject> entities = RenderableHolder.getInstance().getEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Flame) {
				if (((Flame) entities.get(i)).getAfterburn() == 1) {
					((Flame) entities.get(i)).setDestroy(true);
					for (int j = 0; j < entities.size(); j++) {
						if (entities.get(j) instanceof Player1) {
							if (((Player1) entities.get(j)).getX() == ((Flame) entities.get(i)).getX()
									&& ((Player1) entities.get(j)).getY() == ((Flame) entities.get(i)).getY()) {
								((Player1) entities.get(j)).setLife(((Player1) entities.get(j)).getLife() - 1);
								if (((Player1) entities.get(j)).isDestroy()) {
									p2.setScore(p2.getScore() + 2000);
								}
							}
						} else if (entities.get(j) instanceof Player2) {
							if (((Player2) entities.get(j)).getX() == ((Flame) entities.get(i)).getX()
									&& ((Player2) entities.get(j)).getY() == ((Flame) entities.get(i)).getY()) {
								((Player2) entities.get(j)).setLife(((Player2) entities.get(j)).getLife() - 1);
								if (((Player2) entities.get(j)).isDestroy()) {
									p1.setScore(p1.getScore() + 2000);
								}
							}
						} else if (entities.get(j) instanceof Explodable) {
							if (((Explodable) entities.get(j)).getX() == ((Flame) entities.get(i)).getX()
									&& ((Explodable) entities.get(j)).getY() == ((Flame) entities.get(i)).getY()) {
								((Explodable) entities.get(j)).setLife(((Explodable) entities.get(j)).getLife() - 1);
								if (((Flame) entities.get(i)).getPlayer() == p1) {
									p1.setScore(p1.getScore() + 100);
								} else if (((Flame) entities.get(i)).getPlayer() == p2) {
									p2.setScore(p2.getScore() + 100);
								}
								randomItem(((Explodable) entities.get(j)).getX(),
										((Explodable) entities.get(j)).getY());
							}
						} else if (entities.get(j) instanceof Enemy) {
							if (((Enemy) entities.get(j)).getX() == ((Flame) entities.get(i)).getX()
									&& ((Enemy) entities.get(j)).getY() == ((Flame) entities.get(i)).getY()) {
								if (((Flame) entities.get(i)).getPlayer() == p1) {
									p1.setScore(p1.getScore() + 500);
								} else if (((Flame) entities.get(i)).getPlayer() == p2) {
									p2.setScore(p2.getScore() + 500);
								}
								((Enemy) entities.get(j)).setDestroy(true);
							}
						} else if (entities.get(j) instanceof Item) {
							if (((Item) entities.get(j)).getX() == ((Flame) entities.get(i)).getX()
									&& ((Item) entities.get(j)).getY() == ((Flame) entities.get(i)).getY()) {
								((Item) entities.get(j)).setLife(((Item) entities.get(j)).getLife() - 1);
							}
						}

					}

				} else {
					((Flame) entities.get(i)).setAfterburn(((Flame) entities.get(i)).getAfterburn() + 1);

				}
			}

		}
	}

	private void moveEnemy() {

		if (!e1.isDestroy()) {
			e1.move();
		}

		if (!e2.isDestroy()) {
			e2.move();
		}
		if (!e1.isDestroy()) {
			if (e1.getX() == p1.getX() && e1.getY() == p1.getY()) {
				e1.attackPlayer(p1);
			} else if (e1.getX() == p2.getX() && e1.getY() == p2.getY()) {
				e1.attackPlayer(p2);
			}
		}
		if (!e2.isDestroy()) {
			if (e2.getX() == p1.getX() && e2.getY() == p1.getY()) {
				e2.attackPlayer(p1);
			} else if (e2.getX() == p2.getX() && e2.getY() == p2.getY()) {
				e2.attackPlayer(p2);
			}
		}

	}

	private void randomItem(int x, int y) {
		Random rand = new Random();
		int drop = rand.nextInt(100);
		System.out.println(drop);
		if (drop <= 10) {
			RenderableHolder.getInstance().add(new Range(x, y, model.Entity.SOUTH));
		} else if (drop <= 20) {
			RenderableHolder.getInstance().add(new Heart(x, y, model.Entity.SOUTH));
		} else if (drop <= 30) {
			RenderableHolder.getInstance().getEntities().add(new Quantity(x, y, model.Entity.SOUTH));
		}
	}

	private void checkItem() {
		List<IRenderableObject> entities = RenderableHolder.getInstance().getEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Item) {
				if (((Item) entities.get(i)).getX() == p1.getX() && ((Item) entities.get(i)).getY() == p1.getY()) {
					((Item) entities.get(i)).useItem(p1);
				} else if (((Item) entities.get(i)).getX() == p2.getX()
						&& ((Item) entities.get(i)).getY() == p2.getY()) {
					((Item) entities.get(i)).useItem(p2);
				}
			}
		}
	}

	public void receiveKey(KeyCode new_code) {
		if (!CodeUtility.keyPressed.contains(new_code)) {
			CodeUtility.keyPressed.add(new_code);
		}
	}

	public void dropKey(KeyCode new_code) {
		if (CodeUtility.keyPressed.contains(new_code)) {
			CodeUtility.keyPressed.remove(new_code);
			CodeUtility.keyTriggerd.remove(new_code);
		}
	}

	public Field myField() {
		return field;
	}
}