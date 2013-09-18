
public class T1 extends Thread {
	
	boolean active;
	boolean alive;
	
	public T1()
	{
		active = true;
		alive = true;
	}

	@Override
	public void run()
	{
		while(alive)
		{
			while(active)
			{
				System.out.println("T1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Kills the thread
	 */
	public void terminate() {
        alive = false;
        active = false;
    }
	
	/**
	 * Pausing the thread, allowing it to start again.
	 */
	public void pause()
	{
		active = false;
	}
	
	/**
	 * If paused, this will start the thread again
	 */
	public void restart()
	{
		active = true;
	}

}
