//import java.io.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
    private static ServerSocket serverConnection;
    private static final int PORT = 1234;
     
  
    
    public static void main(String[] args)
    {
    	
       
         
        try
        {
        	
            serverConnection = new ServerSocket(PORT); 
            
        }
        
        catch(IOException ioEx)
        {
        	
            System.out.println("Unable to attach to port!");
            System.exit(1);
            
        }
        
        System.out.println("Server running…\n");
        
        try {
        
        do
        {
        	
        	
            Socket sc = serverConnection.accept();
            ClientConnection connection = new ClientConnection(sc);
            connection.start();
            
        }
        while (true);
        
        }
        
        catch(IOException ie) {
           
            System.out.println("Error occurs when creating the output stream or if the socket is not connected.");

        }
    }
     
    
}