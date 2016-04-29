package spacegame.handlers;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageHandler {

	private Image craft, alien, ammo, missile, life, back;
	private ImageIcon ii;

	public ImageHandler() {
		loadImages();
	}

	public void loadImages() {
		try {
			ii = new ImageIcon(this.getClass().getResource("/res/craft.png"));
		} catch (Exception e) {
			ii = new ImageIcon("res/craft.png");
		}
		craft = ii.getImage();
		try {
			ii = new ImageIcon(this.getClass().getResource("/res/ammo.png"));
		} catch (Exception e) {
			ii = new ImageIcon("res/ammo.png");
		}
		ammo = ii.getImage();
		try {
			ii = new ImageIcon(this.getClass().getResource("/res/alien.png"));
		} catch (Exception e) {
			ii = new ImageIcon("res/alien.png");
		}
		alien = ii.getImage();
		try {
			ii = new ImageIcon(this.getClass().getResource("/res/life.png"));
		} catch (Exception e) {
			ii = new ImageIcon("res/life.png");
		}
		life = ii.getImage();
		try {
			ii = new ImageIcon(this.getClass().getResource(
					"/res/projectile.png"));
		} catch (Exception e) {
			ii = new ImageIcon("res/projectile.png");
		}
		missile = ii.getImage();
		try {
			ii = new ImageIcon(this.getClass().getResource(
					"/res/Background.png"));
		} catch (Exception e) {
			ii = new ImageIcon("res/Background.png");
		}
		back = ii.getImage();
	}

	public Image getImage(String a) {
		Image iToReturn = null;

		if (a.equalsIgnoreCase("craft"))
			iToReturn = craft;
		else if (a.equalsIgnoreCase("ammo"))
			iToReturn = ammo;
		else if (a.equalsIgnoreCase("life"))
			iToReturn = life;
		else if (a.equalsIgnoreCase("alien"))
			iToReturn = alien;
		else if (a.equalsIgnoreCase("missile"))
			iToReturn = missile;
		else if (a.equalsIgnoreCase("back"))
			iToReturn = back;
		return iToReturn;
	}

}
