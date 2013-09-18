import java.io.IOException;
import java.net.*;


public class ServerApp 
{
	
	public ServerApp()
	{
		try {
			ServerSocket serversocket = new ServerSocket(2000);
			System.out.println("Waiting for clients...");
			
			while(true)
			{
				Socket socket = serversocket.accept();
				System.out.println("Client connected from " + socket.getLocalAddress().getHostName());
				
				ClientHandler ch = new ClientHandler(socket);
				Thread t = new Thread(ch);
				t.start();
				
			}
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

}
