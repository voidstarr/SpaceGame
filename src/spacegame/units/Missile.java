package spacegame.units;

public class Missile extends MovingObject {

	private final int BOARD_WIDTH = 390;
	public int MISSILE_SPEED = 2;

	public Missile(int x, int y) {
		super(x, y, "missile");
	}

	public void move() {
		setX(getX() + MISSILE_SPEED);
		if (getX() > BOARD_WIDTH)
			setVisible(false);
	}
}
