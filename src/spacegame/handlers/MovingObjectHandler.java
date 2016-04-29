package spacegame.handlers;

import java.util.ArrayList;
import java.util.Random;

import spacegame.Game;
import spacegame.Game.GameState;
import spacegame.units.Alien;
import spacegame.units.Craft;
import spacegame.units.Drop;
import spacegame.units.Missile;
import spacegame.util.LoopTask;

public class MovingObjectHandler extends LoopTask {

	private ArrayList<Alien> aliens;
	private ArrayList<Drop> drops;
	private Craft craft;

	public MovingObjectHandler() {
		super("Moving Object Handler");
		aliens = new ArrayList<Alien>();
		drops = new ArrayList<Drop>();
	}

	@Override
	public int loop() {
		if (!(Game.getState() == GameState.inGame) || Game.isPaused()
				|| getCraft() == null || !Game.isRunning())
			return 500;

		if (getAliens().size() == 0) {
			if (getCraft().ammo > 30) {
				getCraft().ammo -= 30;
				Game.addPoints(getCraft().ammo * 10);
			}
			if (Game.getStage() < 10) {
				Game.nextStage();
				reloadObjects();
			} else {
				Game.endGame(true);
				reloadObjects();
			}
		}

		for (int i = 0; i < getDrops().size(); i++) {
			Drop d = (Drop) getDrops().get(i);
			if (d.isVisible())
				d.move();
			else
				getDrops().remove(i);
		}

		for (int i = 0; i < getCraft().getMissiles().size(); i++) {
			Missile m = (Missile) getCraft().getMissiles().get(i);
			if (m.isVisible())
				m.move();
			else
				getCraft().getMissiles().remove(i);
		}

		for (int i = 0; i < getAliens().size(); i++) {
			Alien a = (Alien) getAliens().get(i);
			if (a.isVisible())
				a.move();
			else
				getAliens().remove(i);
		}

		getCraft().move();
		return 5;
	}

	public void reloadObjects() {
		craft = new Craft();
		initAliens();
		for (int i = 0; i < drops.size(); i++)
			drops.remove(i);
	}

	private void initAliens() {
		aliens = new ArrayList<Alien>();
		int randX;
		int randY;
		Random r = new Random("lollololtrollololol".hashCode());
		for (int i = 0; i < Game.getStage() * 5; i++) {
			randX = r.nextInt(2500) + 1;
			randY = r.nextInt(260) + 1;
			while (randX < 450)
				randX = r.nextInt(2500) + 1;
			getAliens().add(new Alien(randX, randY));
		}

	}

	public synchronized ArrayList<Alien> getAliens() {
		return aliens;
	}

	public synchronized ArrayList<Drop> getDrops() {
		return drops;
	}

	public synchronized Craft getCraft() {
		return craft;
	}

	public void stopAllObjects() {
		for (int i = 0; i < getCraft().getMissiles().size(); i++)
			getCraft().getMissiles().get(i).MISSILE_SPEED = 0;
		for (int i = 0; i < getAliens().size(); i++)
			getAliens().get(i).setDx(0);
	}

	public void resumeAllObjects() {
		for (int i = 0; i < getCraft().getMissiles().size(); i++)
			getCraft().getMissiles().get(i).MISSILE_SPEED = 1;
		for (int i = 0; i < getAliens().size(); i++)
			getAliens().get(i).setDx(1);
	}

}
