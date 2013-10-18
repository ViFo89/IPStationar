import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;


public class ChatServerView extends JFrame {

	private JPanel contentPane;
	private JTextArea serverLog;
	private JScrollPane scrollPane;

	public ChatServerView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		serverLog = new JTextArea();
		serverLog.setEditable(false);

		scrollPane.setViewportView(serverLog);
	}
	
	public void addMessage(String str)
	{
		str += "\n";
		serverLog.append(str);
		moveScrollToBottom();
		
	}
	
	public void moveScrollToBottom()
	{ 
		JScrollBar verticalScroll = scrollPane.getVerticalScrollBar();
		verticalScroll.setValue(verticalScroll.getMaximum());
	}

}
