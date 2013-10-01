import java.io.*;
import java.net.Socket;


public class ClientConnection implements Runnable {

	private Socket _socket;
	private int _id;
	//private volatile String _broadcastMessage;
	private volatile boolean _run;
	private volatile String _message2;
	private PrintWriter out;
	
	public ClientConnection(Socket socket, int id)
	{
		_run = true;
		_id = id;
		_socket = socket;
		_message2 = "";
		//_broadcastMessage = "";
	}
	
	@Override
	public void run()
	{
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			out = new PrintWriter(_socket.getOutputStream());
			
			out.print("Välkommen! Du har fått ID: " + _id + "\n");
			out.flush();
			
			while(_run)
			{
				/*if(!_broadcastMessage.isEmpty())
				{
					System.out.println("Broadcast i "+_id + ": " +_broadcastMessage );
					out.print(_broadcastMessage);
					out.flush();
					_broadcastMessage = "";
					System.out.println("Skickat");
				}*/
				System.out.println(in.ready());
				if(in.ready()){
					_message2 = "#" + _id + ": "+ in.readLine();
					System.out.println("Tar emot: " + _message2);
				}

				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
			}
		} catch(IOException e){
			e.printStackTrace();
		}

	}
	
	public void shutDown(){ _run = false; }
	
	public String getMessage(){ 
		String temp = _message2;
		_message2 = "";
		return temp;
	}
	
	public void broadcast(String str){
		System.out.println("Broadcast i "+_id + ": " + str );
		out.print(str);
		out.flush();
	}
	
	public int getID(){ return _id; }
	
	public boolean hasMessage(){ return !_message2.isEmpty(); }

}
