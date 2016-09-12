import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FinalJFrameClient extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField messageTextField;
	private  		JTextArea messagesArea;
	private         JTextArea userNameTextArea;
	private static JButton listButton;
	private  		PmFrame pmFrame = null;
	private static JList<String> list;
	private static DefaultListModel<String> listModel;
	private static JButton logoutButton;
	private static JButton btnLogin;
	private static JButton pmButton;
					static FinalJFrameClient frame;
	private static String username;
	private static InetAddress host;
    private static final int PORT = 1234;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static Socket socket = null;
    private  MyThread serverHandlingThread ;
    private Boolean running = true;

	
	public static void setUsername(String user) {
		username = user;
	}

	public static String getUsername() {
		return username;
	}
	
	public JTextArea getMessagesArea() {
		return messagesArea;
	}
	
	public static DefaultListModel<String> getListModel() {
		return listModel;
	}

	public static JList<String> getList() {
		return list;
	}

	public PmFrame getPmFrame() {
		return pmFrame;
	}

	public static void sendMessage(ChatMessage msg) {
		 
		try {
			
			             objectOutputStream.writeObject(msg);
			             
			}
			 
			    catch(IOException e) {
			    	
			             e.printStackTrace();
			 
			 }

	}
	
	public class MyThread extends Thread {

		public MyThread() {

		}

		public void run() {
	   		
	   		try {
	   			
	   			while(!Thread.currentThread().isInterrupted()) {
	   	    	
	   		    	
	   	    	while(running)  {
				
	   	        String receivedMsg = null;
	   	    	receivedMsg = (String) objectInputStream.readObject();
	   	    	
	   	    	// receiver of pm does the following
	   	    	switch(receivedMsg) {
	   	    	
	   	    	case "PM":
	   	    	
	   	    		
	   	    		 if (pmFrame == null) {
	   	    			 
	   	    			pmFrame = new PmFrame();
	   	   	    		pmFrame.setLocationRelativeTo(frame);
	   	   	    		pmFrame.setVisible(true);
	   	   	    		
	   	    		 }
	   	    			receivedMsg = null;
	   	    			receivedMsg = (String) objectInputStream.readObject();
	   	    			
	   		   	    	pmFrame.getPmMessagesArea().append(receivedMsg + "\n");
	   		   	    	
	   		   	 break;
	   	    	
	   	    	
	   	    
	   	    	case "NICKINUSE": 
	   	    	
	   	    		btnLogin.doClick(60);
	   	    	
	   	    	break;
	   	    	
	   	    	case "UPDATELIST":
	   	    	
	   	    		
	   	    		 listButton.doClick();
	   	    		
	   	    		
	   	    	break;
	   	    	
	   	    	case "LOGINSUCCESS":
	   	    		
	   	    		btnLogin.setEnabled(false);
	   	    		logoutButton.setEnabled(true);
	   	    		messageTextField.setEditable(true);
		   		    listButton.doClick();
		   		    userNameTextArea.append(username);
		   		    frame.setTitle(username );
	   	    		
	   	    	break;
	   	    	
	   	    	case "LIST":
	   	    		
	   	    		 Object[] nicksList = (Object[]) objectInputStream.readObject();
	   	    	
	   	    		 listModel.removeAllElements();
	   	    		 for (int i = 0; i < nicksList.length; i++) listModel.addElement((String) nicksList[i]);
	   	    		 
	   	    		 
	   	    	break; 
	   	    		
				default: messagesArea.append(receivedMsg + "\n");
				
	   	    	}	
	           
	  		  }
	   	      
	   	      }
	   	      
	   	   } catch (IOException e) {
	   		   
	   			e.printStackTrace();
	   			
	   		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
	   	   
	   	}		
		
		
		public void cancel() {
			interrupt();
		}

	}
	 
	 
	public  MyThread getMythread() {
			return serverHandlingThread;
	}	
	 
	 
	public static JButton getBtnLogin() {
		return btnLogin;
	}



	public static JButton getLogoutButton() {
		return logoutButton;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
        try {
       	 
            host = InetAddress.getLocalHost();
            
          }
         
          catch(UnknownHostException uhEx)
          {
        	  
        	System.out.println("\nHost ID not found!\n");
            System.exit(1);
            
          }
        
        
        try {
        	
        	socket = new Socket(host,PORT);
        	objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        	objectInputStream = new ObjectInputStream(socket.getInputStream());
        	
        }
        
        catch(IOException ioe) {
        	
        	ioe.printStackTrace();
        	
        }
        
        
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					 frame = new FinalJFrameClient();
					 frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.serverHandlingThread = frame.new MyThread();
					frame.serverHandlingThread.start();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public FinalJFrameClient() throws IOException, ClassNotFoundException {
		
		
		setMinimumSize(new Dimension(600, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		messagesArea= new JTextArea();
		messagesArea.setBounds(107, 65, 484, 387);
		contentPane.add(messagesArea);
		
		messageTextField = new JTextField();
		messageTextField.setBounds(107, 488, 484, 41);
		contentPane.add(messageTextField);
		messageTextField.setColumns(10);
		messageTextField.setEditable(false);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String message = null;
				 message = messageTextField.getText();
				sendMessage(new ChatMessage(ChatMessage.MESSAGE,username + " said: " + message));
				
				
				messageTextField.setText("");
				
			}
		});
		
		
		sendButton.setBounds(23, 495, 78, 28);
		contentPane.add(sendButton);
		
		btnLogin = new JButton("LogIn");
		btnLogin.setBounds(12, 24, 78, 29);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LogInDialog loginwindow = new LogInDialog();
		    	loginwindow.setLocationRelativeTo(frame);
		    	loginwindow.setVisible(true);
					
			}
		});
		
		contentPane.add(btnLogin);
		
		
		listButton = new JButton("List");
		listButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!socket.isClosed())
				sendMessage(new ChatMessage(ChatMessage.USERLIST,""));
					
			}
		});
		
		listButton.setBounds(476, 543, 117, 29);
		listButton.setVisible(false);
		contentPane.add(listButton);
	  
		
		logoutButton = new JButton("Logout");
		logoutButton.setEnabled(false);
		logoutButton.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent e) {
				
			
				sendMessage(new ChatMessage(ChatMessage.LOGOUT,""));
				logoutButton.setEnabled(false);
				listModel.removeElement(username);
				frame.setEnabled(false);
				running = false;
				serverHandlingThread.cancel();
				
				try {
					socket.close();
				} catch (IOException e2) {
					
					e2.printStackTrace();
				}
				
				
				
				try {
					
					objectOutputStream.close();
					
				} catch (IOException e1) {
					
					System.out.println("Couldn't close stream");
					
				}
			
				
				
			}
		});
		logoutButton.setBounds(12, 65, 78, 29);
		contentPane.add(logoutButton);
		
		JButton nickChangeBtn = new JButton("Nick");
		nickChangeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NickChangeDialog nickDialog = new NickChangeDialog();
				nickDialog.setLocationRelativeTo(frame);
		    	nickDialog.setVisible(true);
		    	
			}
		});
		nickChangeBtn.setBounds(12, 103, 78, 29);
		contentPane.add(nickChangeBtn);

		listModel = new DefaultListModel<String>();
  		list = new JList<String>(listModel);
  		list.addMouseListener(new MouseAdapter() {
  			@Override
  			public void mouseClicked(MouseEvent evt) {
  				
  				JList list = (JList)evt.getSource();
  			    if (evt.getClickCount() == 2) {
  			        int index = list.locationToIndex(evt.getPoint());
  			        pmButton.doClick();
  			    }
  				
  			}
  		});
  		list.setBackground(new Color(173, 255, 47));
  		list.setBounds(12, 179, 72, 293);
  		list.setVisibleRowCount(1);
  		contentPane.add(list);
  		
  		
  		
  		pmButton = new JButton("PM");
  		pmButton.setVisible(false);
  		pmButton.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				
  				if (pmFrame == null) {
  					
  				pmFrame = new PmFrame();
   	    		pmFrame.setLocationRelativeTo(frame);
   	    		pmFrame.setVisible(true);
   	    		
   	    
  				
  				}
  				
  				
  				
  				//pmFrame.setVisible(true);
  				
  			}
  		});
  		pmButton.setBounds(23, 543, 60, 29);
  		contentPane.add(pmButton);
  		
  		
  		getRootPane().setDefaultButton(sendButton);
  		
  		JLabel onlineUsersLabel = new JLabel("Online Users");
  		onlineUsersLabel.setBounds(12, 151, 83, 16);
  		contentPane.add(onlineUsersLabel);
  		
  		JLabel UserLoggedIn = new JLabel("User logged in :");
  		UserLoggedIn.setBounds(137, 29, 106, 16);
  		contentPane.add(UserLoggedIn);
  		
  	    userNameTextArea = new JTextArea();
  		userNameTextArea.setEditable(false);
  		userNameTextArea.setBounds(255, 29, 78, 16);
  		userNameTextArea.setBorder(null);
  		userNameTextArea.setOpaque(false);
  		//userNameTextArea.setBackground(new Color(0, 0, 0, 0));
  		contentPane.add(userNameTextArea);
	}
}
