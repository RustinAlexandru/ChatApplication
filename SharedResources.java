
import java.io.*;
import java.net.*;
import java.util.*;


public class SharedResources  {


	 
	  static ArrayList<ClientConnection> connections = new ArrayList<ClientConnection>(); 
	  
	  public synchronized void broadcast(String s) {
		  for (Iterator<ClientConnection> it = connections.iterator(); it.hasNext(); ) {
			   ClientConnection temp = (ClientConnection) it.next();
			   temp.writeMsg(s);
		  }
			  
	 }
	  

	/*
	 public synchronized void remove(String user) {
		 
		 for (int i = 0; i < connections.size(); ++i) {
		       ClientConnection temp = connections.get(i);
		             if (temp.getUsername().equals(user)) {
		                 connections.remove(i);
		                 return;
		             }
		         }
	 }
	 */
	  
		     

	  
}
