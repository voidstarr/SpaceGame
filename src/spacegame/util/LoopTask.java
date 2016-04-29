package spacegame.util;

public abstract class LoopTask implements Runnable {
	Thread taskThread;
	boolean isAlive = false;

	boolean stop = false;
	boolean pause = false;

	public LoopTask(String taskName, boolean start) {
		this.taskThread = new Thread(this);
		this.taskThread.setName(taskName);
		if (start)
			this.taskThread.start();
	}

	public boolean isPaused() {
		return pause;
	}

	public LoopTask(String taskName) {
		this(taskName, true);
	}

	public void pause() {
		pause = true;
	}

	public void resume() {
		pause = false;
	}

	public abstract int loop();

	public void onStart() {
	}

	public void onFinish() {
	}

	public final void run() {
		this.isAlive = true;
		onStart();
		while (!this.stop) {
			int sleep = -1;
			try {
				if (!pause)
					sleep = loop();
				else
					sleep = 50;
			} catch (ThreadDeath td) {
				break;
			} catch (Exception e) {
				System.out.println("Uncaught exception in: " + getName());
				e.printStackTrace();
			}

			if (sleep <= -1)
				break;
			try {
				Utilities.sleep(sleep);
			} catch (ThreadDeath td) {
				break;
			}
		}
		try {
			onFinish();
		} catch (ThreadDeath td) {
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		this.isAlive = false;
		System.out.println(getName() + " has stopped.");
	}

	public String getName() {
		return this.taskThread.getName();
	}

	public void start() {
		if (!this.taskThread.isAlive())
			this.taskThread.start();
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	public void terminate() {
		this.stop = true;
	}
}
