package arpa.home.springpoll.dev.proto.votesocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	String name;
	String action;
	
}
