package spacegame.handlers;

import java.awt.Rectangle;
import java.util.ArrayList;

import spacegame.Game;
import spacegame.Game.GameState;
import spacegame.units.Alien;
import spacegame.units.Drop;
import spacegame.units.Missile;
import spacegame.util.LoopTask;

public class CollisionDetector extends LoopTask {

	public CollisionDetector() {
		super("Collision Thread");
	}

	@Override
	public int loop() {
		if (Game.getState() != GameState.inGame
				|| Game.getMOH().getCraft() == null || !Game.isRunning())
			return 500;
		try {
			Rectangle r3 = Game.getMOH().getCraft().getBounds();

			for (int j = 0; j < Game.getMOH().getAliens().size(); j++) {
				Alien a = (Alien) Game.getMOH().getAliens().get(j);
				Rectangle r2 = a.getBounds();
				if (r3.intersects(r2)) {
					a.setVisible(false);
					Game.takeLife();
					Game.addPoints(-200);
					if (Game.getLives() == 0) {
						Game.endGame(false);
					}
				}
			}

			for (int i = 0; i < Game.getMOH().getDrops().size(); i++) {
				Drop d = (Drop) Game.getMOH().getDrops().get(i);
				Rectangle dRec = d.getBounds();

				if (dRec.intersects(r3)) {
					Game.giveBonus(d.giveBonus());
					Game.getMOH().getDrops().get(i).setVisible(false);
				}
			}

			ArrayList<Missile> ms = Game.getMOH().getCraft().getMissiles();

			for (int i = 0; i < ms.size(); i++) {
				Missile m = (Missile) ms.get(i);

				Rectangle r1 = m.getBounds();

				for (int j = 0; j < Game.getMOH().getAliens().size(); j++) {
					Alien a = (Alien) Game.getMOH().getAliens().get(j);
					Rectangle r2 = a.getBounds();

					if (r1.intersects(r2)) {
						Game.addAlienKilled();
						Game.addPoints(100);
						m.setVisible(false);
						a.setVisible(false);
						if (Math.random() <= 0.3) {
							Game.getMOH()
									.getDrops()
									.add(new Drop(a.getX(), a.getY(), Math
											.random() <= 0.8 ? "ammo" : "life"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 5;
	}
}
