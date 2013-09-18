import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable 
{
	
	private Socket _socket;
	
	public ClientHandler(Socket socket)
	{
		_socket = socket;
	}

	@Override
	public void run() 
	{
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			PrintWriter out = new PrintWriter(_socket.getOutputStream(),true);
			out.println("Välkommen");
			while(true)
			{
				String message = null;
				if ((message = in.readLine()) != null) 
				{
					System.out.println(message);
					out.println("Du sa: "+ message);
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
