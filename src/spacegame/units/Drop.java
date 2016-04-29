package spacegame.units;

public class Drop extends MovingObject {

	private String type;

	public Drop(int x, int y, String type) {
		super(x, y, type);
		this.type = type;
	}

	public void move() {
		setX((int) (getX() - .75));
		if (getX() < 10)
			setVisible(false);
	}

	public String giveBonus() {
		return type;
	}
}
