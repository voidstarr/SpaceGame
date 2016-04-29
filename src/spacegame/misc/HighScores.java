package spacegame.misc;

import spacegame.util.Utilities;
import spacegame.wrappers.HighScore;

public class HighScores {
	private static int userID;
	private static int MINIMUM_SCORE = 0; // getMinimumScore()
	private static int personalHighest;

	public static void init(int id) {
		userID = id;
		// setPersonalHighest();
	}

	private static void setPersonalHighest() {
		String temp = Utilities
				.getInfoFromPage(Constants.SITE + "/highscores/hsutils.php?action=permax&id=" + userID, null)
				.replaceAll("\n", "");
		if (temp != null)
			personalHighest = Integer.parseInt(temp);
	}

	public static boolean sendHighScore(HighScore hs) {
		String data = "score=" + hs.getScore() + "&kills=" + hs.getKills() + "&shots=" + hs.getShots() + "&stage="
				+ hs.getStage() + "&by=" + userID;
		if (hs.getScore() >= MINIMUM_SCORE) {
			return Utilities.sendPostToPage("http://pjnomore.info/m/highscores/addscore.php?id=2", data, "9001");
		}
		return false;
	}

	public static int getPersonalHighestScore() {
		return personalHighest;
	}

	private static int getMinimumScore() {
		String s = Utilities.getInfoFromPage(Constants.SITE + "highscores/hsutils.php?action=min", null);
		return Integer.parseInt(s.substring(4).replaceAll("\n", ""));
	}
}