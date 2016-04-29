package spacegame;

import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JApplet;
import javax.swing.JFrame;

import spacegame.handlers.CollisionDetector;
import spacegame.handlers.ImageHandler;
import spacegame.handlers.MovingObjectHandler;
import spacegame.misc.Constants;
import spacegame.misc.HighScores;
import spacegame.wrappers.HighScore;

@SuppressWarnings("serial")
public class Game extends JApplet { // TODO redo versioning
	private static ImageHandler imageHandler = new ImageHandler();
	private static MovingObjectHandler movingObjectHandler;
	private static CollisionDetector collisionDetector;

	private static int stage = 1, totAliensKilled, totAmmoUsed, points = 0,
			totLosses, totWins, lives = 3;

	private static final double CURRENT_VERSION = getCurrentVersion();

	private static boolean paused,
			isUpToDate = CURRENT_VERSION == Constants.VERSION, isRunning;

	public enum GameState {
		inGame, endGameLoss, endGameWin, menu
	}

	public static JFrame gameWindow;
	public static Board board;
	private static boolean isApplet, isLoggedIn;

	public static void main(String[] args) {
		isApplet = false;
		setRunning(true);
		board = new Board(isApplet);
		gameWindow = new JFrame("PJ's Space v" + Constants.VERSION);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setSize(400, 300);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setResizable(false);
		gameWindow.add(board);
		gameWindow.setVisible(true);
		newGame();
	}

	@Override
	public void start() {
		setRunning(true);
		movingObjectHandler = new MovingObjectHandler();
		collisionDetector = new CollisionDetector();
		newGame();
//		if (Integer.parseInt(getParameter("id")) < 0) {
//			setLoggedIn(false);
//		} else {
			setLoggedIn(true);
		//	HighScores.init(Integer.parseInt(getParameter("id")));
		//}
	}

	@Override
	public void init() {
		setSize(400, 300);
		isApplet = true;
		board = new Board(isApplet, isLoggedIn());
		add(board);
		setFocusable(false);
	}

	@Override
	public void stop() {
	}

	private synchronized static double getCurrentVersion() {
		try {
			URL url = new URL("http://pjnomore.info/m/getver.php?id=2");
			URLConnection ucon = url.openConnection();
			Scanner bs = new Scanner(ucon.getInputStream());
			return bs.nextDouble();
		} catch (Exception e) {
		}
		return 0;
	}

	volatile static GameState state = GameState.inGame;

	public synchronized static MovingObjectHandler getMOH() {
		return movingObjectHandler;
	}

	public synchronized static CollisionDetector getCD() {
		return collisionDetector;
	}

	public synchronized static ImageHandler getIH() {
		return imageHandler;
	}

	public static void newGame() {
		setState(GameState.inGame);
		stage = 1;
		lives = 3;
		points = 0;
		getMOH().reloadObjects();
	}

	public static void endGame(boolean win) {
//		if (isLoggedIn())
//			HighScores.sendHighScore(new HighScore(getScore(),
//					getAliensKilled(), getAmmoUsed(), getStage()));
		if (!win) {
			addLoss();
			setState(GameState.endGameLoss);
		} else {
			addWin();
			setState(GameState.endGameWin);
		}

	}

	public static int getScore() {
		return points;
	}

	public static void giveBonus(String type) {
		if (type.equals("ammo")) {
			int r = new Random().nextInt(20) + 1;
			getMOH().getCraft().addAmmo(r);
		} else {
			lives++;
		}
		addPoints(300);
	}

	public static void pause() {
		paused = true;
		getMOH().stopAllObjects();
	}

	public static void unPause() {
		paused = false;
		getMOH().resumeAllObjects();
	}

	public static int getStage() {
		return stage;
	}

	public static void addPoints(int i) {
		points += i;
	}

	public static void nextStage() {
		stage++;
	}

	public static GameState getState() {
		return state;
	}

	public static void setState(GameState s) {
		state = s;
	}

	public static void takeLife() {
		lives--;
	}

	public static int getLives() {
		return lives;
	}

	public static boolean isPaused() {
		return paused;
	}

	public static void addWin() {
		totWins++;
	}

	public static void addLoss() {
		totLosses++;
	}

	public static void addAlienKilled() {
		totAliensKilled++;
	}

	public static int getPoints() {
		return points;
	}

	public static int getAliensKilled() {
		return totAliensKilled;
	}

	public static int getWins() {
		return totWins;
	}

	public static int getAmmoUsed() {
		return totAmmoUsed;
	}

	public static int getLosses() {
		return totLosses;
	}

	public static void addAmmoUsed() {
		totAmmoUsed++;
	}

	/**
	 * @return the isLoggedIn
	 */
	public static boolean isLoggedIn() {
		return isLoggedIn;
	}

	private void setLoggedIn(boolean b) {
		isLoggedIn = b;

	}

	/**
	 * @return the isUpToDate
	 */
	public static boolean isUpToDate() {
		return isUpToDate;
	}

	/**
	 * @param isRunning
	 *            the isRunning to set
	 */
	public static void setRunning(boolean isRunning) {
		Game.isRunning = isRunning;
	}

	/**
	 * @return the isRunning
	 */
	public static boolean isRunning() {
		return isRunning;
	}

}