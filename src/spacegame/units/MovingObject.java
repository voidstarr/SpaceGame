package spacegame.units;

import java.awt.Image;
import java.awt.Rectangle;

import spacegame.Game;

public abstract class MovingObject {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int width;
	private int height;
	private boolean visible;
	private Image image;

	public MovingObject(int x, int y, String img) {
		setImage(img);
		setWidth(getImage().getWidth(null));
		setHeight(getImage().getHeight(null));
		setVisible(true);
		this.setX(x);
		this.setY(y);
	}

	public Image getImage() {
		return image;
	}

	public abstract void move();

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public void setImage(String string) {
		this.image = Game.getIH().getImage(string);
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param d
	 *            the x to set
	 */
	public void setX(int d) {
		this.x = d;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}

}
