import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class SendRecieve implements Runnable {

	private Socket _socket;
	private volatile boolean _run;
	private volatile String _send;
	private volatile ArrayList<String> _messageLog;
	
	public SendRecieve(Socket socket)
	{
		_socket = socket;
		_run = true;
		_send = null;
		_messageLog = new ArrayList<String>();
	}
	
	@Override
	public void run() 
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			PrintWriter out = new PrintWriter(_socket.getOutputStream());
			
			while(_run)
			{
				if(in.ready())
				{
					_messageLog.add(in.readLine());
				}
				if(_send != null)
				{
					out.println(_send);
					out.flush();
					System.out.println("Skickat: " + _send);
					_send = null;
				}
				
			}//while
			
		} catch(IOException e) {
			
		}
		
	}
	
	public void sendMessage(String message)
	{
		_send = message;
	}
	
	

}
