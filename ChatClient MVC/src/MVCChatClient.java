
public class MVCChatClient {

	public static void main(String[] args) {
		
		ChatClientModel model = new ChatClientModel();
		ChatClientView view = new ChatClientView();
		ChatClientController controller = new ChatClientController(model, view);
		
		controller.handleArgs(args);
		
		view.setVisible(true);
		
		controller.checkForUpdates();
	}

}
