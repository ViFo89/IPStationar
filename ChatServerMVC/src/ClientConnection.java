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
			
			out.println("V�lkommen! Du har f�ttt ID: " + _id + "\n");
			
			while(_run)
			{
				if(in.ready()){	//Kollar om det finns n�tt att h�mta fr�n BufferedReader (in)
					synchronized(_message){	//L�ser _message s� inte flera tr�dar arbetar p� den samtidigt
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
	 * Returnerar och nollst�ller in-meddelandet.
	 */
	public synchronized String getMessage(){ 
		synchronized(_message){	//L�ser _message s� inte flera tr�dar arbetar p� den samtidigt
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
