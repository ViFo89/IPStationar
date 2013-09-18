import java.io.IOException;
import java.net.*;


public class Server 
{
	
	ServerSocket serverSocket;

	public Server () 
	{
		try {
			serverSocket = new ServerSocket(9876);
			System.out.println("Waiting for clients...");
			
			while(true)
			{
				Socket socket = serverSocket.accept();
				
				System.out.println("Client connected from " + socket.getLocalAddress().getHostName());
				
				Client chat = new Client(socket);
				Thread t = new Thread(chat);
				t.start();
				
			}
			
			
		} catch (IOException e){
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		
	}

}
