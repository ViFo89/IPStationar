import java.io.*;
import java.net.*;


public class Listen implements Runnable 
{
	
	private boolean alive;
	private Socket socket = null;
	private BufferedReader br;
	String str = "";
	
	public Listen()
	{
		byte[] ipByte = {127,0,0,1};
		
		try 
		{
			InetAddress ip = Inet4Address.getByAddress(ipByte);
			socket = new Socket(ip, 0);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		alive = true;
		
	}

	@Override
	public void run() 
	{
		while(alive);
		{
			try {
				int value=0;
				
				while((value = br.read()) != -1)
		         {
		            // converts int to character
		            char c = (char)value;
		            
		            // prints character
		            str += c;
		         }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void kill(){
		alive = false;
	}
	
	public String getText()
	{
		return str;
	}

}
