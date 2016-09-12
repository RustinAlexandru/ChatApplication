
import java.util.*;

public class ChatProtocol {

	private String nickname;
	private ClientConnection connection;
	
	private static Hashtable<String, ClientConnection> nicknames = new Hashtable<String, ClientConnection>();
	
	private static boolean addNick (String nick, ClientConnection connection) {
		
		if (nicknames.containsKey(nick)) {
			return false;
			
		} else {
			nicknames.put(nick, connection);
			return true;
		}
		
	}
	
	public ChatProtocol (ClientConnection client) {
		
		nickname = null;
		connection = client; 
		
	}
	
	public boolean isLoggedIn() {
		
		if (nickname == null) return false;
		return true;
		
	}
	
	public synchronized void login (ChatMessage cm)  { 
		
		if (addNick(cm.getMessage(),this.connection) == true) {
			
			this.nickname = cm.getMessage();
			broadcast("User " + nickname + " connected!");
			broadcast("UPDATELIST");
			this.connection.writeMsg("LOGINSUCCESS");
			
		} else {
			
		this.connection.writeMsg("Nicku e deja folosit");
		this.connection.writeMsg("NICKINUSE");

		
		}
	}
	
	public synchronized void changeNick (ChatMessage cm) {
		
		String oldNick = nickname;
		nicknames.remove(nickname, this.connection);
		this.nickname = cm.getMessage();
		nicknames.put(nickname, this.connection);
		broadcast("User " + oldNick + " changed nick to " + nickname + "!");
		broadcast("UPDATELIST");
		
		
	}
	
	public synchronized void broadcast (String s) {
		
		Collection<ClientConnection> connections = nicknames.values();
		
		
		  for (Iterator<ClientConnection> it = connections.iterator(); it.hasNext(); ) {
			   ClientConnection temp = (ClientConnection) it.next();
			   temp.writeMsg(s);
		  }
			  
	 }
	
	public synchronized void logoutBroadcast (String s) {
		
		Collection<ClientConnection> connections = nicknames.values();
		Set<String> nicks = nicknames.keySet();
		Object[] nicksList = nicks.toArray();
		   
		 
		
		  for (Iterator<ClientConnection> it = connections.iterator(); it.hasNext(); ) {
			   ClientConnection temp = (ClientConnection) it.next();
			   if (!temp.equals(connection))
			     {
				   temp.writeMsg("LIST");
				   temp.writeList(nicksList);
			     }
		  }
		
	}
	public synchronized void messageUser (String user, String msg) {
		
		ClientConnection userConnection = nicknames.get(user);
		userConnection.writeMsg("PM");
		userConnection.writeMsg(nickname + " said: " + msg);
		
	}
	
	
   public synchronized void remove() {
	   
	   
	   nicknames.remove(nickname, this.connection);
	  
   }
   
   public synchronized void getOnlineUsers() {
   
	   Set<String> connections = nicknames.keySet();
	   
	   Object[] nicksList = connections.toArray();
	   
	   this.connection.writeMsg("LIST");
	   this.connection.writeList(nicksList);
		
	   
   }
	
}
