import java.awt.*;
import javax.swing.*;

public class Draw extends JFrame {
	private Paper p = new Paper();

	public static void main(String[] args) {
		new Draw();
	}

	public Draw() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(p, BorderLayout.CENTER);

		setSize(640, 480);
		setVisible(true);
	}
}