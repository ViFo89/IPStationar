import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Listner implements Runnable{
	private Socket socket;
	
	public Listner(Socket s)
	{
		socket = s;
	}
	
	@Override
	public void run()
	{
		try
		{
			Scanner chat = new Scanner(System.in);	
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			while (true)
			{
				String input = chat.nextLine();
				out.println(input);	
				out.flush();
				System.out.println("flush");
				String read;
				System.out.println("while");
				while ((read = in.readLine()) != null)
				{
					System.out.println("loop");
					out.println(read);

					
				}
				System.out.println("här");
				//System.out.println("Client Said: " + message);				
			}
		}
		catch (Exception e)
		{
			System.out.println("Fel");
			e.printStackTrace();
		}
	}
}
