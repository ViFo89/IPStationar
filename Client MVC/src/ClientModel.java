public class ClientModel{
	
	static final int STANDARD_PORT = 2000;
	static final String STANDARD_ADRESS = "localhost";
	private String viewTitle;
	private int connectionPort;
	private String connectionAdress;
	
	public ClientModel() 
	{
		
	}
	
	
	/* Getters & Setters */
	public void setPort(int port)
	{
		connectionPort = port;
	}
	
	public void setAdress(String adress)
	{
		connectionAdress = adress;
	}
	
	public int getPort()
	{
		return connectionPort;
	}
	
	public String getAdress()
	{
		return connectionAdress;
	}


}
