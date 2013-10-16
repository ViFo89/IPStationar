import java.io.*;
import java.net.*;


public class Send 
{
	
	Socket socket = null;
	BufferedWriter bw;
	
	public Send(String message)
	{
		byte[] ipByte = {127,0,0,1};
		
		try 
		{
			InetAddress ip = Inet4Address.getByAddress(ipByte);
			socket = new Socket(ip, 0);
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(message);
			bw.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}


}
