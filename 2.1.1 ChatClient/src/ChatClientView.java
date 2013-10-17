import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;


public class ChatClientView extends JFrame {

	//Components
		private JTextField messageText;
		private JTextArea chatConversation;
		private JButton btnEnter;
		private JScrollPane scrollPane_1;
		
		
		public ChatClientView() 
		{
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.SOUTH);
			
			messageText = new JTextField();
			panel.add(messageText);
			messageText.setColumns(25);
			
			btnEnter = new JButton("Enter");
			
			panel.add(btnEnter);
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.CENTER);
			
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panel_1.add(scrollPane_1);
			
			chatConversation = new JTextArea();
			scrollPane_1.setViewportView(chatConversation);
			chatConversation.setEditable(false);
			chatConversation.setColumns(35);
			chatConversation.setRows(10);
			
		}
		
		private void moveScrollToBottom()
		{ 
			JScrollBar verticalScroll = scrollPane_1.getVerticalScrollBar();
			verticalScroll.setValue(verticalScroll.getMaximum());
		}
				
		public void addEnterListener(ActionListener enl) { 
			btnEnter.addActionListener(enl); 
			messageText.addActionListener(enl);
		}
		
		public String getChatMessage() { return messageText.getText(); }
		
		public void addToMessageBoard(String str) 
		{ 
			chatConversation.append(str+"\n");
			moveScrollToBottom();
		}
		
		public void clearChatMessageField() { messageText.setText(""); }
	
}
