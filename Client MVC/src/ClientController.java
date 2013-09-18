import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientController {
	
	private ClientView _view;
	private ClientModel _model;

	public ClientController(ClientModel model, ClientView view) 
	{
		_view = view;
		_model = model;
		
		String str = _model.getAdress() + ":" + _model.getPort();
		
		_view.setWindowTitle(str);
		
		//Adding Listeners To View
		_view.addEnterListener(new EnterListener());
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
	}
	
	
	//Inner classes(Listeners)
	class EnterListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Send");
		}
		
	}

}
