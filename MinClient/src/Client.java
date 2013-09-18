import java.io.IOException;
import java.net.Socket;


public class Client {

	private final static int PORT = 9876;
	private final static String HOST = "localhost";
	
	public Client()
	{
		try 
		{
			
			Socket s = new Socket(HOST, PORT);
			
			System.out.println("You connected to " + HOST);
			
			Listner lis = new Listner(s);
			
			Thread t = new Thread(lis);
			t.start();
			
		} 
		catch (Exception noServer)
		{
			System.out.println("The server might not be up at this time.");
			System.out.println("Please try again later.");
		}
	}
	
}
