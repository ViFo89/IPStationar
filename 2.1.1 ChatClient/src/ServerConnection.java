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
		//System.out.println("run");
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
	
	public synchronized void sendMessage(String message)
	{
		out.println(message);
		out.flush();
	}
	
	public String getNewMessage() 
	{
		String temp;
		synchronized(messageLock)
		{
			hasNewMessage = false;
			temp = incoming;
		}
		return temp;
	}
	
	public void stop() 
	{
		synchronized(runLock)
		{
			run = false;
		}
		System.out.println("run = false");
	}
	
	private boolean doLoop()
	{ 
		boolean temp;
		synchronized(runLock){
			temp = run;
		}
		return temp;
	}
	
	public boolean hasNewMessage() 
	{
		boolean temp;
		synchronized(messageLock)
		{
			temp = hasNewMessage;
		}
		
		return temp; 
	}
	
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
