import java.io.IOException;
import java.net.*;
import java.util.LinkedList;


public class ChatServerModel implements Runnable {

	static final int STANDARD_PORT = 2000;
	private int _connectionPort;
	private volatile LinkedList<ClientConnection> newClients;
	private int currentID;
	private volatile String _message;
	
	
	public ChatServerModel()
	{
		currentID = 0;
		_connectionPort = STANDARD_PORT;
		newClients = new LinkedList<ClientConnection>();
		_message = "";
	}
	
	
	public void run()
	{
		try {
			ServerSocket server = new ServerSocket(_connectionPort);
			while(true)
			{
				Socket clientHandle = server.accept();
				
				ClientConnection client = new ClientConnection(clientHandle, ++currentID);
				
				_message = "Client connected from " + clientHandle.getLocalAddress().getHostName() + ", ID: "+ currentID;
				newClients.add(client);
				
				new Thread(client).start();
				
			}
			
		} catch (IOException e) {
			System.out.println("Fel!");
		}
		
	}
	
	/* Getters & Setters */
	public void setPort(int port) { _connectionPort = port; }
	
	public int getPort() { return _connectionPort; }
	
	public boolean hasMessage() { return !_message.isEmpty(); }
	
	public String getMessage() 
	{ 
		String temp = _message;
		_message = "";
		return temp; 
		
	}
	
	public boolean hasNewClient() { return !newClients.isEmpty(); }
	
	public ClientConnection getNewClient() { return newClients.pop(); }

	public int getNumberOfUsers() { return newClients.size(); }


}
