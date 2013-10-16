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
		
		String str = "Port: " + _model.getPort()+ " | " +_clients.size();
		
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
				_view.setWindowTitle("Port: "+ _model.getPort()+ " | " +_clients.size());
			}

			for(ClientConnection cc : _clients)
			{
				if(cc.hasMessage()){
					_broadcastMess = cc.getMessage();

					if(_broadcastMess.equals("#"+cc.getID()+": \\exit"))
					{
						_view.addMessage("#"+cc.getID() + " has disconnected");
						cc.shutDown();
						System.out.println("Före:"+_clients.size());
						_clients.remove(cc);
						System.out.println("efter:"+_clients.size());
					}else{
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
	
}
