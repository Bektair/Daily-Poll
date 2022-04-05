package arpa.home.springpoll.dev.proto.websocket;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

record GreetingRequest(String name) {}
record GreetingResponse(String message) {}

@Controller
public class GreetingsWebsocketController {
	
	@MessageExceptionHandler 
	@SendTo("/topic/error")
	String handleException (Exception e){
		var message = "Something went wrong processing the request" + NestedExceptionUtils.getMostSpecificCause(e);
		System.out.println(message);
		return message; 
	}
	
	
	//For messaging protocoll endpoint
	@MessageMapping("/chat")
	@SendTo("/topic/greetings") //one to many broadcast feed
	GreetingResponse greet (GreetingRequest request) throws InterruptedException {
		
		Assert.isTrue(Character.isUpperCase(request.name().charAt(0)), 
				"must be capital letter");
		Thread.sleep(1000);
		return new GreetingResponse("Hello" + request.name() + "!");
		
	}
}
