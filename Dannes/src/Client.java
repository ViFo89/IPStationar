import java.io.IOException;



public class Client {
	private static GUI gui;
	private Connection connection;
	

	private Thread receiver;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		gui = new GUI();
		Client client = new Client();
		
	}

	/**
	 * Create the frame.
	 */
	public Client() throws IOException {
		
		connection = new Connection("localhost",9876);
		

		if(connection.connect()){
			receiver = new Receiver(gui, connection);
			receiver.run();
			connection.getOut().write((connection.getHost() + " " + connection.getPort()));
	
			System.out.println("connected");
			
		}else
			System.exit(1);
	}

}
