import java.io.*;
import java.net.*;


public class DataHandler implements Runnable
{
	
	private Socket _socket;
	private volatile String message;
	private GUI _gui;
	private volatile boolean run;

	public DataHandler(Socket socket, GUI gui)
	{
		_socket = socket;
		message = null;
		_gui = gui;
		run = true;
	}
	
	@Override
	public void run()
	{
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			PrintWriter out = new PrintWriter(_socket.getOutputStream());

			while(run)
			{
				
				if(in.ready())
				{
					_gui.updateTextArea(in.readLine());
		
				}
				
				if(message != null)
				{
					System.out.println("Send");
					out.println(message);
					out.flush();
					message = null;
				}
				
			}
			
			if(in != null) in.close();
			if(out != null) out.close();
			if(_socket != null) _socket.close();
			
		}catch(IOException e){
			System.out.println("Error");
			//e.printStackTrace();
		}
			
		
	}
	
	public void setMessage(String str)
	{
		System.out.println("Set Message");
		message = str;
	}

	public void disconnect() {
		run = false;
		
	}
	
}
