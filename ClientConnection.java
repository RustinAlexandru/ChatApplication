

import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author alexandrurustin
 */


public class ClientConnection extends Thread {
    
    private Socket socketConnection;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ChatMessage chatmsg;
    
   
  
    public ClientConnection (Socket sc) throws IOException {
    	
        this.socketConnection = sc;
        objectOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
        objectInputStream = new ObjectInputStream(socketConnection.getInputStream());
       
        
      }
    
    
    
    public Socket getSocket() {
    	return socketConnection;
    }
    
   
    
    
    
    public void writeMsg (String msg) {
       try {
    	   
		objectOutputStream.writeObject(msg);
		
       } catch (IOException e) {
	
		e.printStackTrace();
		
       }
       
    }
    
  
    public void writeList (Object o) {
    	
    	try {
    		
			objectOutputStream.writeObject(o);
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
    
    }
    

    
    
    public void run() {
    	

    	ChatProtocol protocol = new ChatProtocol(this);
    	
    	
    	boolean running = true;
    	
    			
		while (running) {
			
			
			try {
				
				chatmsg = (ChatMessage) objectInputStream.readObject();
				
			}  catch (IOException e) {
				
				e.printStackTrace();
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
			
			switch(chatmsg.getType()) {
			
			case ChatMessage.LOGIN:
				if (protocol.isLoggedIn() == false )
					protocol.login(chatmsg);
				
				break;
			
			case ChatMessage.MESSAGE:
		
				protocol.broadcast(chatmsg.getMessage());
				break;
				
			case ChatMessage.PRIVATEMESSAGE:
				String str = chatmsg.getMessage();    // split string on first occurence of whitespace, first part is user, 2nd message
				protocol.messageUser(str.substring(0, str.indexOf(' ')),str.substring(str.indexOf(' ')+1));
				break;
			
			case ChatMessage.NICK:
				protocol.changeNick(chatmsg);
				break;
				
			case ChatMessage.LOGOUT:
				protocol.broadcast("UPDATELIST");
				protocol.remove();

				
				running = false;
				try {
					

					objectInputStream.close();
					objectOutputStream.close();
					socketConnection.close();
					
				} catch (IOException e) {
					
					System.out.println("Couldn't close streams");
					
				}

				break;
				
			case ChatMessage.USERLIST:
				protocol.getOnlineUsers();
				
				break;
			}
			
			
			//close();
			
		}
    	
  
    	
    			
   	   
   	}
   	
   	

}
