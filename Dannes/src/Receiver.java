import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JTextArea;


public class Receiver extends Thread {
	private GUI gui;
	
	private Connection connection;
	
	private boolean run = true;
	
	public Receiver(GUI gui,Connection connection){
		this.gui = gui;
		this.connection = connection;
	}
	
	public void stop_receiver(){
		run = false;
	}
	
	@Override
	public void run(){
		
		while(run){
		
			try {
				String message = connection.getIn().readLine();
				gui.append(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("error");
				e.printStackTrace();
			}
			
			
			
		}
		
	}
	
}
