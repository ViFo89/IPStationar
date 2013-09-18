import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldInput;
	private JTextArea textAreaScreen;


	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textFieldInput = new JTextField();
		contentPane.add(textFieldInput, BorderLayout.NORTH);
		textFieldInput.setColumns(10);
		
		textAreaScreen = new JTextArea();
		textAreaScreen.setEditable(false);
		contentPane.add(textAreaScreen, BorderLayout.CENTER);
		
		setVisible(true);
	}


	public void append(String message) {
		textAreaScreen.append(message);
		
	}

}
