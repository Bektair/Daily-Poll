package arpa.home.springpoll.usecase.entities;

import java.io.Serializable;

public class Alternative implements Serializable {

	private String alternativeTxt;
	private int alternativeOrder;
	private String emoji;
	private String id;
	


	public Alternative(String alternativeTxt, 
			int alternativeOrder, String emoji, String id) {
		this.alternativeTxt=alternativeTxt;
		this.alternativeOrder=alternativeOrder;
		this.emoji=emoji;
		this.id=id;
		
	}
	
	public String getId() {
		return id;
	}


	public String getAlternativeTxt() {
		return alternativeTxt;
	}


	public int getAlternativeOrder() {
		return alternativeOrder;
	}


	public String getEmoji() {
		return emoji;
	}

}
