
public class MVCChatServer {

	
	public static void main(String[] args) {
		
		ChatServerView view = new ChatServerView();
		ChatServerModel model = new ChatServerModel();
		ChatServerController controller = new ChatServerController(model, view);
		
		controller.handleArgs(args);
		
		view.setVisible(true);
		
		controller.checkForUpdates();
	}

}
