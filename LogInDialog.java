import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;


public class LogInDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameTextField;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			//LogInDialog dialog = new LogInDialog();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogInDialog() {
		setBounds(100, 100, 298, 265);
		getContentPane().setLayout(null);
		{
			JLabel usernameLabel = new JLabel("Enter username:");
			usernameLabel.setBounds(19, 101, 108, 16);
			getContentPane().add(usernameLabel);
		}
		{
			usernameTextField = new JTextField();
			usernameTextField.setBounds(134, 95, 134, 28);
			getContentPane().add(usernameTextField);
			usernameTextField.setColumns(10);
		}
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FinalJFrameClient.setUsername(usernameTextField.getText());
				dispose();
				FinalJFrameClient.sendMessage(new ChatMessage(ChatMessage.LOGIN,FinalJFrameClient.getUsername())) ;
			}
		});
		btnOk.setBounds(80, 165, 117, 29);
		getContentPane().add(btnOk);
		
		getRootPane().setDefaultButton(btnOk);
	}
}
