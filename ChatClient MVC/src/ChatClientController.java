import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JScrollBar;


public class ChatClientController {
	
	private ChatClientView _view;
	private ChatClientModel _model;

	public ChatClientController(ChatClientModel model, ChatClientView view) 
	{
		_view = view;
		_model = model;
		
		//Adding Listeners To View
		_view.addEnterListener(new EnterListener());
		_view.addWindowListener(new DoOnCloseWindow());
	}
	
	public void handleArgs(String[] args)
	{
		int argsLength = args.length;
		
		switch (argsLength){
		case 0:
			_model.setPort(2000);
			_model.setAdress("localhost");
			break;
		case 1:
			_model.setPort(2000);
			_model.setAdress(args[0]);
			break;
		case 2:
			_model.setPort(Integer.parseInt(args[1]));
			_model.setAdress(args[0]);
		}
		
		String str = _model.getAdress() + ":" + _model.getPort();
		
		_view.setWindowTitle(str);
		
		new Thread(_model).start();
	}
	
	
	//Inner classes(Listeners)
	class EnterListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			_model.sendMessage(_view.getChatMessage());
			_view.emptyChatMessageField();
		}
		
	}
	
	class DoOnCloseWindow implements WindowListener
	{

		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowClosed(WindowEvent e) {}

		@Override
		public void windowClosing(WindowEvent e) {
			try {
				Socket socket = _model.getSocket();
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				out.println("\\exit");
				out.close();
				socket.close();
				_model.close();
				
			} catch (IOException e1) {
				System.out.println("Fel i close");
				//e1.printStackTrace();
			}
		}

		@Override
		public void windowDeactivated(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowOpened(WindowEvent e) {}
		
	}
	
	public void checkForUpdates()
	{
		while(true){
			if(_model.isUpdated())
			{
				_view.addToMessageBoard(_model.getMessage());
			}
		}
	}

}
