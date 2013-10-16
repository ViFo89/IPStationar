import java.io.*;
import java.net.Socket;


public class ClientConnection implements Runnable {

	private Socket _socket;
	private int _id;
	private volatile boolean _run;
	private volatile String _message;
	private PrintWriter out;
	
	public ClientConnection(Socket socket, int id)
	{
		_run = true;
		_id = id;
		_socket = socket;
		_message = "";
	}
	
	@Override
	public void run()
	{
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			out = new PrintWriter(_socket.getOutputStream(),true);
			
			out.println("Välkommen! Du har fåttt ID: " + _id + "\n");
			
			while(_run)
			{
				if(in.ready()){	//Kollar om det finns nått att hämta från BufferedReader (in)
					synchronized(_message){	//Låser _message så inte flera trådar arbetar på den samtidigt
						_message = "#" + _id + ": "+ in.readLine();	//
					}
				}
				
			}
			out.close();
			in.close();
			_socket.close();
		} catch(IOException e){
			e.printStackTrace();
		}

	}
	
	public void shutDown(){ _run = false; }
	/**
	 * Returnerar och nollställer in-meddelandet.
	 */
	public synchronized String getMessage(){ 
		synchronized(_message){	//Låser _message så inte flera trådar arbetar på den samtidigt
			String temp = _message;
			_message = "";
			return temp;
		}
	}
	
	public synchronized void broadcast(String str){
		out.println(str);
	}
	
	public boolean isConnected() { return out.checkError(); }
	
	public int getID(){ return _id; }
	
	public boolean hasMessage(){ return !_message.isEmpty(); }

}
