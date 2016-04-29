package spacegame.wrappers;

public class HighScore {

	private int score, kills, shots, stage;

	/**
	 * @param score
	 * @param kills
	 * @param shots
	 * @param stage
	 * 
	 */
	public HighScore(int score, int kills, int shots, int stage) {
		this.score = score;
		this.kills = kills;
		this.shots = shots;
		this.stage = stage;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return the kills
	 */
	public int getKills() {
		return kills;
	}

	/**
	 * @return the shots
	 */
	public int getShots() {
		return shots;
	}

	/**
	 * @return the stage
	 */
	public int getStage() {
		return stage;
	}
}
