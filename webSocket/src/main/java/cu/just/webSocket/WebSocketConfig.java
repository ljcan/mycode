package cu.just.webSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//½«socketHandlerÓ³Éäµ½/webSocket
		registry.addHandler(socketHander(), "/hello").withSockJS();
	}
	
	@Bean
	public SocketHandler socketHander(){
		return new SocketHandler();
	}

}
