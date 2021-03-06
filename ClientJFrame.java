import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package tema2ex4;

/**
 *
 * @author alexandrurustin
 */

public class ClientJFrame extends javax.swing.JFrame {

	
	private static InetAddress host;
    private static final int PORT = 1234;
    private static DataInputStream networkInput;
    private static DataOutputStream networkOutput;
    private static Socket socket = null;
    private static String message;
	private static String response;
	private static String username;
	private static MyThread mythread;
	
    /**
     * Creates new form jFrame
     */
    public ClientJFrame() {
        initComponents();
      
    	
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        usernameTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesTextArea = new javax.swing.JTextArea();
        messageTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        LogIn = new javax.swing.JButton();

        usernameTextField.setText("username");
        usernameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameTextFieldMouseClicked(evt);
            }
        });
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        usernameLabel.setText("Enter username");

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(usernameLabel)
                .addGap(18, 18, 18)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");

        messagesTextArea.setColumns(20);
        messagesTextArea.setRows(5);
        jScrollPane1.setViewportView(messagesTextArea);

        messageTextField.setText("enter message");
        messageTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                messageTextClicked(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        LogIn.setText("LogIn");
        LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogInActionPerformed(evt);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(LogIn)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LogIn)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
  

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
        try {
        	message = messageTextField.getText();
        	networkOutput.writeUTF(username +" said: "+ message);
			if (message.equals("Bye!")) {
				mythread.cancel();
				sendButton.setEnabled(false);
				}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }                                          

    private void messageTextClicked(java.awt.event.MouseEvent evt) {                                    
        messageTextField.setText("");
    }                                   

    private void usernameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {                                               
        usernameTextField.setText("");
    }     
    
    private void LogInActionPerformed(java.awt.event.ActionEvent evt) {                                      
        
    	//jDialog1.setBounds(0,23,299,147);
    	jDialog1.pack();
    	jDialog1.setLocationRelativeTo(myJFrame);
    	jDialog1.setVisible(true);
    	
    	
    	
    }                                     
    
    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        	username = usernameTextField.getText();
        	jDialog1.dispose();
        	mythread.start();
        	LogIn.setEnabled(false);
    }      
    
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {                                  
    	if (socket != null)     
		{
			try
			{
				socket.close();
				mythread.cancel();
			}
			catch (IOException ioEx)
			{
				System.out.println("\nUnable to close socket!\n");
				System.exit(1);
			}
		}
		System.exit(0); 
    }  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

      
        
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
        	networkInput = new DataInputStream(socket.getInputStream());
        	networkOutput = new DataOutputStream(socket.getOutputStream());
        	  /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                 myJFrame = new ClientJFrame();
                 myJFrame.setLocationRelativeTo(null);
                 myJFrame.setVisible(true);
                 mythread = myJFrame.new MyThread();
                }
            });
            
        	
        	}
        
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        
       
		
        
      
        
        
        
      
        
    }

    // Variables declaration - do not modify                     
    static private javax.swing.JButton LogIn;
    static private javax.swing.JDialog jDialog1;
    static private javax.swing.JScrollPane jScrollPane1;
    static private javax.swing.JTextField jTextField2;
    static private javax.swing.JTextField messageTextField;
    static private javax.swing.JTextArea messagesTextArea;
    static private javax.swing.JButton okButton;
    static private javax.swing.JButton sendButton;
    static private javax.swing.JLabel usernameLabel;
    static private javax.swing.JTextField usernameTextField;
    static private ClientJFrame myJFrame;
    
    
    
    
    // End of variables declaration       
    
    public class MyThread extends Thread {
    	
    	 
   	 
   	 public MyThread() {
   		 
   	 }
   	 
   	public void run() {
   		
   		try {
   	      
   			while(!Thread.currentThread().isInterrupted()) {
   	    	  
   	    	networkOutput.writeUTF(username);
			
   	    	while(true)  {
			
   	    	response = networkInput.readUTF();
			messagesTextArea.append(response + "\n");
           
  		  	}
   	      }
   	      
   	   } catch (IOException e) {
   			//e.printStackTrace();
   		}
   	   
   	}
   	
   	public void cancel() { 
   		interrupt(); 
   	 }
   	
    }
    
}
