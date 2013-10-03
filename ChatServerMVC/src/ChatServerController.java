import java.util.ArrayList;


public class ChatServerController {

	private ChatServerView _view;
	private ChatServerModel _model;
	private ArrayList<ClientConnection> _clients;
	private String _broadcastMess;
	
	public ChatServerController(ChatServerModel model, ChatServerView view)
	{
		_clients = new ArrayList<ClientConnection>();
		_view = view;
		_model = model;
	}
	
	public void handleArgs(String[] args)
	{
		int argsLength = args.length;
		
		switch (argsLength){
		case 0:
			_model.setPort(2000);
			break;
		case 1:
			_model.setPort(Integer.parseInt(args[0]));
			break;

		}
		
		String str = "Port: " + _model.getPort();
		
		_view.setWindowTitle(str);
	}
	
	public void checkForUpdates()
	{
		Thread t = new Thread(_model);
		t.start();
		while(true)
		{
			if(_model.hasNewClient())
			{
				_clients.add(_model.getNewClient());
				if(_model.hasMessage())
				_view.addMessage(_model.getMessage());
			}

			for(ClientConnection cc : _clients)
			{
				if(cc.hasMessage()){
					_broadcastMess = cc.getMessage();
					_view.addMessage(_broadcastMess);
					
					for(ClientConnection cc2 : _clients)
					{
						cc2.broadcast(_broadcastMess);
					}
					
				}
			}
		}
	}
	
}
