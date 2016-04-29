package spacegame.units;

public class Alien extends MovingObject {

	public Alien(int x, int y) {
		super(x, y, "alien");
		setDx(1);
	}

	public void move() {
		if (getX() < 0)
			setX(400);
		setX(getX() - getDx());
	}
}
