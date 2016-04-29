package spacegame.units;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import spacegame.Game;

public class Craft extends MovingObject {
	private ArrayList<Missile> missiles;

	public int ammo = 30;

	public Craft() {
		super(40, 60, "craft");
		missiles = new ArrayList<Missile>();
	}

	public int getAmmo() {
		return ammo;
	}

	public void move() {

		setX(getX() + getDx());
		setY(getY() + getDy());

		if (getX() < 1)
			setX(1);
		if (getY() < 1)
			setY(1);
		if (getX() > 395)
			setX(395);
		if (getY() > 295)
			setY(295);
	}

	public synchronized ArrayList<Missile> getMissiles() {
		return missiles;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		// if (key == KeyEvent.VK_SPACE) {
		// fire();
		// }

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			setDx(-1);
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			setDx(1);
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			setDy(-1);
		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			setDy(1);
		}
	}

	public void fire() {
		if (ammo < 1)
			return;
		Game.addAmmoUsed();
		ammo--;
		missiles.add(new Missile(getX() + getWidth(), getY() + getHeight() / 2));
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			fire();
		}

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			setDx(0);
		}

		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			setDx(0);
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			setDy(0);
		}

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			setDy(0);
		}
	}

	public void addAmmo(int r) {
		ammo += r;
	}
}
