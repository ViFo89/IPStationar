import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.io.*;
import java.net.*;

public class GUI extends JFrame 
{

	private JTextField textField;
	private JTextArea textArea;
	
	private static DataHandler dh;
	private static int port;
	private static String adress;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		GUI gui = new GUI();
		gui.handleArgs(args);
		Socket socket;
		
		try {
			socket = new Socket(adress, port);
			gui.setTitle(adress + ":" + port);
			
			dh = new DataHandler(socket, gui);
			Thread t = new Thread(dh);
			t.start();
			
		} catch (UnknownHostException e) {
			System.exit(0);
			//e.printStackTrace();
		} catch (IOException e) {
			System.exit(0);
			//e.printStackTrace();
		}
			
	}
	
	/**
	 * Create the frame.
	 */
	public GUI() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WL());
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(25);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new EnterAction() );
		panel.add(btnEnter);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setColumns(35);
		textArea.setRows(10);
		panel_1.add(textArea);
		
		setVisible(true);
	}
	
	public void updateTextArea(String str)
	{
		
		textArea.append(str+"\n");
		
	}
	
	
	private class EnterAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = textField.getText();
			textField.setText("");
			dh.setMessage(str);
		}
		
	}
	
	private class WL implements WindowListener
	{

		@Override
		public void windowActivated(WindowEvent arg0) {
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			dh.disconnect();
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			
		}

		
	}
	
	private void handleArgs(String[] args)
	{
		int argsLength = args.length;
		
		switch (argsLength){
		case 0:
			port = 2000;
			adress = "localhost";
			break;
		case 1:
			port = 2000;
			adress = args[0];
			break;
		case 2:
			port = Integer.parseInt(args[1]);
			adress = args[0];
		}
	}


}
