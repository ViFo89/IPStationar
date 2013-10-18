import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ChatServer {

	private ChatServerView view;
	private ClientConnectionHandler cch;
	
	private static int connectionPort;
	
	private ChatServer()
	{
		view = new ChatServerView();
		cch = new ClientConnectionHandler(connectionPort, view);
		
		start();
	}
	
	private void start() 
	{
		long startTime = System.currentTimeMillis();
		view.setVisible(true);
		setWindowClosing();
		
		Thread t = new Thread(cch);
		t.start();
		view.setTitle(cch.generateTitle());
		
		
		while(true)
		{
			if(System.currentTimeMillis() - startTime > 1000){
				view.setTitle(cch.generateTitle());
				startTime = System.currentTimeMillis();
			}
			
			cch.checkForNewMesseges();
			
		}
		
	}

	private void setWindowClosing() {
		view.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				cch.stop();
			}
		});
		
	}

	public static void main(String[] args)
	{
		handleArgs(args);
		new ChatServer();
	}

	private static void handleArgs(String[] args) {
		if(args.length == 0) 
			connectionPort = 2000;
		else
		{
			try{
				connectionPort = Integer.parseInt(args[0]);
			} catch(NumberFormatException e) {
				System.out.println("Måste vara ett nummer");
				System.exit(0);
			}
		}
	}
	
}
