import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ChatClient {
	
	private static int connectionPort;
	private static String connectionAdress;
	
	private ChatClientView view;
	private ServerConnection connection;

	public ChatClient()
	{		
		view = new ChatClientView(); 
		connection = new ServerConnection(connectionAdress, connectionPort);
		
		start();
	}
	
	private void start()
	{
		view.setVisible(true);
		setWindowClosing();
		view.setTitle(connectionAdress + ":" + connectionPort);
		view.addEnterListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String message = view.getChatMessage();
				view.clearChatMessageField();
				if(!message.isEmpty())
					connection.sendMessage(message);
			}
		});
		
		Thread t = new Thread(connection);
		t.start();
		while(true)
		{
			if(connection.hasNewMessage()){
				view.addToMessageBoard(connection.getNewMessage());
			}
			
		}
		
	}
	
	private static void handleArgs(String[] args)
	{
		int argsLength = args.length;
		
		switch (argsLength){
		case 0:
			connectionPort = 2000;
			connectionAdress = "localhost";
			break;
		case 1:
			connectionPort = 2000;
			connectionAdress =  args[0];
			break;
		case 2:
			connectionPort = Integer.parseInt(args[1]);
			connectionAdress = args[0];
		}
	}
	
	/*
	 * Ser till att allting avslutas korrekt när fönstret stängs. 
	 */
	private void setWindowClosing()
	{
		view.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				connection.stop();
			}
		});
	}
	
	public static void main(String[] args) 
	{ 
		handleArgs(args);
		new ChatClient(); 
	}

}
