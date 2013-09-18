import java.io.IOException;
import java.net.*;


public class ClientApp 
{

	public ClientApp()
	{
		try {
			Socket socket = new Socket("localhost", 9876);
			
			DataHandler dh = new DataHandler(socket);
			Thread t = new Thread(dh);
			t.start();
			
			//System.out.println("Här1");
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			//System.out.println("Här2");
			//dh.setMessage("Jag Heter Victor");
			
			/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dh.setMessage("Vad heter du?");
			*/
			
		} catch (UnknownHostException e) {
			System.out.println("UHE");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOE");
			//e.printStackTrace();
		}
		
	}
	
}
