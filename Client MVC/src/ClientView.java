import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ClientView extends JFrame {
	
	//Components
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnEnter;
	
	
	public ClientView(ClientModel model) 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(25);
		
		btnEnter = new JButton("Enter");
		
		panel.add(btnEnter);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setColumns(35);
		textArea.setRows(10);
		panel_1.add(textArea);
		
	}
	
	void setWindowTitle(String title)
	{
		setTitle(title);
	}
	
	void addEnterListener(ActionListener enl) {
		btnEnter.addActionListener(enl);
	}

}
