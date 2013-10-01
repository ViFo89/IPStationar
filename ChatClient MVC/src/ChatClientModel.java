import java.net.Socket;
import java.io.*;

public class ChatClientModel implements Runnable{
	
	static final int STANDARD_PORT = 2000;
	static final String STANDARD_ADRESS = "localhost";
	private volatile String _viewTitle;
	private int _connectionPort;
	private String _connectionAdress;
	private volatile boolean _updated;
	
	private Socket _socket;
	private volatile boolean _run;
	private volatile String _send;
	private volatile String _message;
	
	public ChatClientModel()
	{
		_updated = false;
		_run = true;
		_send = null;
		_message = "";
	}
	
	public void run()
	{
		try
		{
			_socket = new Socket(_connectionAdress, _connectionPort);
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			PrintWriter out = new PrintWriter(_socket.getOutputStream());
			
			while(_run)
			{	
				//Kollar om det finns några meddelanden att ta emot
				if(in.ready())
				{
					_message += (in.readLine());
					_message += "\n";
					_updated = true;
				}
				//Kollar om det finns några meddelanden att skicka
				if(_send != null)
				{
					out.println(_send);
					out.flush();
					_send = null;
				}
				//Kollar om servern fortfarande är online
				if(out.checkError())
				{
					_message = "Du har tappat anslutningen till servern.\nVänligen avsluta!\n";
					_updated = true;
					_run = false;
				}
				
			}//while-END
			
		} catch(IOException e) {
			System.exit(0);
		}
	}

	
	
	/* Getters & Setters */
	public void setPort(int port) { _connectionPort = port; }
	
	public void setAdress(String adress) { _connectionAdress = adress; }
	
	public int getPort() { return _connectionPort; }
	
	public String getAdress() { return _connectionAdress; }
	
	public String getTitle() { return _viewTitle; }
	
	public void sendMessage(String message)
	{ 
		if(!message.isEmpty())
			_send = message;
	}
	
	public String getMessage()
	{
		String str = _message;
		_message = "";
		_updated = false;
		return str;
	}
	
	public boolean isUpdated(){ return _updated; }

}
