import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class NickChangeDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernameTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NickChangeDialog dialog = new NickChangeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NickChangeDialog() {
		setBounds(100, 100, 298, 265);
		getContentPane().setLayout(null);
		{
			JLabel usernameLabel = new JLabel("Enter new username:");
			usernameLabel.setBounds(19, 101, 108, 16);
			getContentPane().add(usernameLabel);
		}
		{
			usernameTextField = new JTextField();
			usernameTextField.setBounds(134, 95, 134, 28);
			getContentPane().add(usernameTextField);
			usernameTextField.setColumns(10);
		}
		
		JButton btnOk = new JButton("Ok");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				FinalJFrameClient.setUsername(usernameTextField.getText());
				dispose();
				FinalJFrameClient.sendMessage(new ChatMessage(ChatMessage.NICK,FinalJFrameClient.getUsername())) ;
			}
		});
		btnOk.setBounds(80, 165, 117, 29);
		getContentPane().add(btnOk);
		getRootPane().setDefaultButton(btnOk);
	}
}
