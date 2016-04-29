package spacegame.wrappers;

public class InfoMessage {
	private String message;
	private int displayFor;

	public InfoMessage(String m, int millis) {
		message = m;
		displayFor = millis;
	}

	public int getDisplayTime() {
		return displayFor;
	}

	@Override
	public String toString() {
		return message;
	}
}