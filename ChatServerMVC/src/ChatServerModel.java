
public class ChatServerModel implements Runnable {

	static final int STANDARD_PORT = 2000;
	static final String STANDARD_ADRESS = "localhost";
	private volatile String _viewTitle;
	private int _connectionPort;
	private String _connectionAdress;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	/* Getters & Setters*/
	public void setPort(int port) { _connectionPort = port; }
	
	public void setAdress(String adress) { _connectionAdress = adress; }
	
	public int getPort() { return _connectionPort; }
	
	public String getAdress() { return _connectionAdress; }
	
	public String getTitle() { return _viewTitle; }

}
