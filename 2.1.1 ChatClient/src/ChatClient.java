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
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Thread t = new Thread(connection);
		t.start();
		int i = 0;
		while(true)
		{
			//String message;
			//System.out.println((i++) +":"+ connection.hasNewMessage());
			if(connection.hasNewMessage()){
				view.addToMessageBoard(connection.getNewMessage());
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//connection.stop();
		
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
