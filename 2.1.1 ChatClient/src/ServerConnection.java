import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ServerConnection implements Runnable
{
	
	private Socket serverConnection;
	private BufferedReader in;
	private PrintWriter out;
	private boolean run = true;
	private String incoming;
	private boolean hasNewMessage;
	private Object runLock = new Object();
	private Object messageLock = new Object();
	
	public ServerConnection(String connectionAdress, int connectionPort)
	{
		hasNewMessage = false;
		incoming = "";
		try {
			serverConnection = new Socket(connectionAdress, connectionPort);
			in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
			out = new PrintWriter(serverConnection.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException, ServerConnection.ServerConnection");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("IOException, ServerConnection.ServerConnection");
			System.exit(0);
		}
	}

	@Override
	public void run()
	{
		while(doLoop())
		{
			try 
			{
				if(in.ready())
				{
					String temp = in.readLine();
					synchronized(messageLock)
					{
						incoming += temp;
						hasNewMessage = true;
					}
					
				}
			}
			catch(IOException e){ System.out.println("IOException: ServerConnection.run"); }
		}
		close();
	}
	
	/**
	 * Skickar ett meddelande till servern.
	 * @param meddelandet som ska skickas till servern.
	 */
	public synchronized void sendMessage(String message)
	{
		out.println(message);
		out.flush();
	}
	
	/**
	 * Returnerar meddelanden som kommit in från servern.
	 * @return Meddelandet från servern.
	 */
	public String getNewMessage() 
	{
		String temp;
		synchronized(messageLock)
		{
			hasNewMessage = false;
			temp = incoming;
			incoming = "";
		}
		return temp;
	}
	
	/**
	 * Stoppar "run-loopen".
	 */
	public void stop() 
	{
		synchronized(runLock)
		{
			run = false;
		}
	}
	
	/**
	 * Bestämmer om "run-loopen" ska köras.
	 */
	private boolean doLoop()
	{ 
		boolean temp;
		synchronized(runLock){
			temp = run;
		}
		return temp;
	}
	
	/**
	 * Berättar om det finns ett nytt meddelande att hämta.
	 */
	public boolean hasNewMessage() 
	{
		boolean temp;
		synchronized(messageLock)
		{
			temp = hasNewMessage;
		}
		
		return temp; 
	}
	
	/**
	 * Stänger alla strömmar.
	 */
	private void close()
	{
		try {
			in.close();
			out.close();
			serverConnection.close();
		} catch (IOException e) {
			System.out.println("Fel i ServerConnection.close");
		}
	}

}
