import java.io.Serializable;


public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final int USERLIST = 0, LOGIN = 1, MESSAGE = 2, PRIVATEMESSAGE = 3, LOGOUT = 4, NICK = 5;
	private int type;
	private String message;
	
	public ChatMessage(int type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public int getType() {
		return type;
	}
		
	public String getMessage() {
		return message;
	}

	
	
	
}
