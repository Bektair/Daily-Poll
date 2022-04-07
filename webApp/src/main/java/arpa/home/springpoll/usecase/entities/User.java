package arpa.home.springpoll.usecase.entities;

import java.io.Serializable;

public class User implements Serializable {

	private String userName;
	private String discordId;
	
	
	public User(String userName, String discordId) {
		this.userName=userName;
		this.discordId = discordId;
	}

	public String getUserName() {
		return userName;
	}

	public String getdiscordId() {
		return discordId;
	}

	
}
