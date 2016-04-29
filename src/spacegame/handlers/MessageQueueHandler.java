package spacegame.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import spacegame.util.LoopTask;
import spacegame.wrappers.InfoMessage;

public class MessageQueueHandler extends LoopTask {

	private ArrayList<InfoMessage> messages;
	private Timer msgTimer;
	private String msgToDisplay;

	public MessageQueueHandler() {
		super("Message Handler");
		messages = new ArrayList<InfoMessage>();
	}

	@SuppressWarnings("unused")
	@Override
	public int loop() {
		if (true)
			return 50000;
		if ((msgTimer == null || !msgTimer.isRunning()) && !messages.isEmpty()) {
			msgTimer = new Timer(messages.get(0).getDisplayTime(),
					new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (haveMessageToDisplay()) {
								messages.remove(0);
								msgToDisplay = messages.get(1).toString();
							}
							msgToDisplay = null;
							msgTimer = null;
						}
					});
			msgTimer.start();
		}
		return 15;
	}

	public String getMessageToDisplay() {
		if (msgToDisplay != null)
			return msgToDisplay;
		return "";
	}

	public void queueMessage(String msg, int millis) {
		messages.add(new InfoMessage(msg, millis));
	}

	public boolean haveMessageToDisplay() {
		return !messages.isEmpty() && msgToDisplay != null;
	}

}
