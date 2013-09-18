import java.io.*;
import java.net.*;


public class Client implements Runnable {

	private Socket socket;
	
	public Client(Socket s)
	{
		socket = s;
		
	}
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			while (true)
			{
				String read;
				while ((read = in.readLine()) != null)
				{
					System.out.println("Client Said: " + read);
					out.println("You Said: " + read);
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("An error occured.");
			e.printStackTrace();
		}
		
	}

}
