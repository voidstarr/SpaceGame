package spacegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import spacegame.Game.GameState;
import spacegame.units.Alien;
import spacegame.units.Drop;
import spacegame.units.Missile;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	private Timer timer;
	private int B_WIDTH;
	private int B_HEIGHT;

	/* END SCREEN VARS */
	int msgX, msgY, msg1X, msg1Y, msg2X, msg2Y;
	String msg = "Game Over.";
	String won = "You Won!";
	String lost = "You Lost!";
	String msg1;
	String msg2 = "Press \"R\" to restart.";

	/* END END SCREEN VARS */

	public Board(boolean isApplet) {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setSize(400, 300);
		timer = new Timer(5, this);
		timer.start();
	}

	public Board(boolean isApplet, boolean isLoggedIn) {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setSize(400, 300);
		timer = new Timer(5, this);
		timer.start();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Game.getIH().getImage("back"), 0, 0, null);
		// g.setColor(Color.WHITE);

		if (!Game.isUpToDate()) {
			g.setColor(Color.WHITE);
			g.drawString("", 20, 100);
		}

		/*
		 * if (Game.getMQH().haveMessageToDisplay()) { Font store = getFont();
		 * g.setFont(new Font("Helvetica", Font.BOLD, 14));
		 * g.drawString(Game.getMQH().getMessageToDisplay(), 10, 260);
		 * g.setFont(store); }
		 */

		if (!Game.isLoggedIn()) {
			g.drawString("Notice: You are not eligable to post", 150, 100);
			g.drawString("highscores! Please sign in.", 150, 115);
		}

		if (Game.state == GameState.inGame) {

			if (Game.getMOH().getCraft().isVisible())
				g2d.drawImage(Game.getMOH().getCraft().getImage(), Game
						.getMOH().getCraft().getX(), Game.getMOH().getCraft()
						.getY(), this);

			for (int i = 0; i < Game.getMOH().getCraft().getMissiles().size(); i++) {
				Missile m = (Missile) Game.getMOH().getCraft().getMissiles()
						.get(i);
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < Game.getMOH().getAliens().size(); i++) {
				Alien a = (Alien) Game.getMOH().getAliens().get(i);
				if (a.isVisible())
					g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}

			for (int i = 0; i < Game.getMOH().getDrops().size(); i++) {
				Drop d = (Drop) Game.getMOH().getDrops().get(i);
				if (d.isVisible())
					g2d.drawImage(d.getImage(), d.getX(), d.getY(), this);
			}

			g2d.setColor(Color.WHITE);
			/* FIRST */
			g2d.drawString("Aliens left: " + Game.getMOH().getAliens().size(),
					5, 15);
			g2d.drawString("Stage :" + Game.getStage(), 5, 28);
			g2d.drawString("Lives: " + Game.getLives(), 5, 41);
			g2d.drawString("Ammo: " + Game.getMOH().getCraft().getAmmo(), 5, 54);
			/*
			 * END FIRST START SECOND
			 */
			g2d.drawString("Points: " + Game.getPoints(), 82, 15);
			/* END SECOND */

			if (Game.isPaused()) {
				Font f = new Font("Helvetica", Font.BOLD, 24);
				g2d.setFont(f);
				FontMetrics metr = this.getFontMetrics(getFont());
				g2d.drawString("Paused.", B_HEIGHT / 2,
						(B_WIDTH - metr.stringWidth("Paused.")) / 2);
			}

		} else if (Game.getState() == GameState.endGameWin
				|| Game.getState() == GameState.endGameLoss) {
			g2d.setColor(Color.white);
			g2d.drawString("Stats:", 5, 15);
			g2d.drawString("Total Aliens Killed :" + Game.getAliensKilled(),
					15, 28);
			g2d.drawString("Total Ammo Used :" + Game.getAmmoUsed(), 15, 41);
			g2d.drawString("Total Wins :" + Game.getWins(), 15, 54);
			g2d.drawString("Total Losses :" + Game.getLosses(), 15, 67);
			g2d.drawString("Your Points: " + Game.getPoints(), 15, 80);

			setFontVars();
			g2d.setFont(new Font("Helvetica", Font.BOLD, 14));
			g2d.drawString(msg, msgX, msgY);
			g2d.drawString(msg1, msg1X, msg1Y);
			g2d.drawString(msg2, msg2X, msg2Y);
		}
		setFontVars();
		g2d.setFont(new Font("Segoe Print", Font.PLAIN, 10));
		g2d.drawString("PJNoMore.info", 320, 270);
		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}

	public void setFontVars() {
		if (Game.getState() == GameState.endGameWin)
			msg1 = won;
		else
			msg1 = lost;
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		msgX = (B_WIDTH - metr.stringWidth(msg)) / 2;
		msg2X = (B_WIDTH - metr.stringWidth(msg2)) / 2;
		msg1X = (B_WIDTH - metr.stringWidth(msg1)) / 2;

		msg1Y = B_HEIGHT / 2;
		msgY = msg1Y - 20;
		msg2Y = msg1Y + 20;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			if (Game.getState() == GameState.inGame && !Game.isPaused())
				Game.getMOH().getCraft().keyReleased(e);

			int key = e.getKeyCode();

			if (!(Game.getState() == GameState.inGame) && key == KeyEvent.VK_R) {
				Game.newGame();
			}
			if (key == KeyEvent.VK_P) {
				if (Game.getState() == GameState.inGame && !Game.isPaused())
					Game.pause();
				else
					Game.unPause();
			}
			if (key == KeyEvent.VK_ESCAPE) {

			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (Game.getState() == GameState.inGame && !Game.isPaused())
				Game.getMOH().getCraft().keyPressed(e);
		}
	}
}
