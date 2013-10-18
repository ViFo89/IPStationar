import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;


public class ClientConnectionHandler implements Runnable{

	private ServerSocket socket;
	private Boolean run;
	private ArrayList<Client> clients;
	private ChatServerView view;
	private String hostName;
	private int connectionPort;
	
	public ClientConnectionHandler(int connectionPort, ChatServerView view) {
		this.connectionPort = connectionPort;
		clients = new ArrayList<Client>();
		this.view = view;
		try {
			socket = new ServerSocket(connectionPort);
			hostName = socket.getInetAddress().getLocalHost().getHostName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		run = true;
		while(doLoop())
		{
			try {
				Client temp = new Client(socket.accept());
				synchronized(clients){
					clients.add(temp);
				}
				
				new Thread(temp).start();
				
				view.addMessage("En klient har anslutit. IP-Adress: " + temp.getIPAdress() + " ID# " + temp.getID());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
	}
	
	public void checkForNewMesseges() {
		synchronized(clients)
		{
			for(Client c : clients)
			{
				
				if(c.hasNewMessage()){
					String temp = "ID# " + c.getID() + ": ";
					temp += c.getNewMessage();
					view.addMessage(temp);
					broadcastMessage(temp);
				}
			}
		}
		
	}

	public String generateTitle()
	{
		synchronized(clients){
			return hostName + ":" + connectionPort + " | " + clients.size();
		}
	}

	public void broadcastMessage(String message)
	{
		for(Client c : clients)
		{
			c.sendMessage(message);
		}
	}

	private boolean doLoop() {
		boolean temp;
		synchronized(run)
		{
			temp = run;
		}
		return temp;
	}

	public void stop() {
		synchronized(run)
		{
			run = false;
		}
	}

	private void close()
	{
		try {
			socket.close();
			for(Client c : clients)
				c.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
