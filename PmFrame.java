import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PmFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea pmMessagesArea;
	private JTextField pmTextField;
	
	
	

	public  JTextArea getPmMessagesArea() {
		return pmMessagesArea;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PmFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pmMessagesArea = new JTextArea();
		pmMessagesArea.setBounds(46, 38, 332, 175);
		contentPane.add(pmMessagesArea);
		
		JButton sendPmButton = new JButton("Send");
		sendPmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String message = null;
				 message = pmTextField.getText();
				 
				 if (FinalJFrameClient.getList().isSelectionEmpty()) 
					 FinalJFrameClient.frame.getPmFrame().getPmMessagesArea().append("Please select a user to send a private message" + "\n");
				
				 else {
				 FinalJFrameClient.sendMessage(new ChatMessage(ChatMessage.PRIVATEMESSAGE,FinalJFrameClient.getList().getSelectedValue() +" "+ message));
				FinalJFrameClient.frame.getPmFrame().getPmMessagesArea().append(FinalJFrameClient.getUsername() + " said: " + 
													message +"\n");
				
			    pmTextField.setText("");
				 }
			}
		});
		
		
		sendPmButton.setBounds(21, 238, 67, 23);
		contentPane.add(sendPmButton);
		
		pmTextField = new JTextField();
		pmTextField.setBounds(116, 233, 259, 28);
		contentPane.add(pmTextField);
		pmTextField.setColumns(10);
		
		getRootPane().setDefaultButton(sendPmButton);
	}
}
