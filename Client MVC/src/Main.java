

//http://www.tutorialspoint.com/design_pattern/mvc_pattern.htm
public class Main {

	public static void main(String[] args) {
		
		ClientModel model = new ClientModel();
		ClientView view = new ClientView(model);
		ClientController controller = new ClientController(model, view);
		
		controller.handleArgs(args);
		
		view.setVisible(true);
	}

}
