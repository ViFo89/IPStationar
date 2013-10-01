
public class ChatServerController {

	private ChatServerView _view;
	private ChatServerModel _model;
	
	public ChatServerController(ChatServerModel model, ChatServerView view)
	{
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
		
		new Thread(_model).start();
	}
}
