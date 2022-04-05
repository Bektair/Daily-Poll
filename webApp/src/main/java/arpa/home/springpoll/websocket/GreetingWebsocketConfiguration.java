package arpa.home.springpoll.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class GreetingWebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
		registry.addEndpoint("/chat").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
		//All brokers(feeds you can subscribe to) are prefixed with /topic?
		registry.enableSimpleBroker("/topic");
		//lookups must have this as a prefix to the sock endpoint
		registry.setApplicationDestinationPrefixes("/app"); 
	}
	
	
}
