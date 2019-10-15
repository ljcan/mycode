package cu.just.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
@Component
public class SocketHandler extends AbstractWebSocketHandler{
	public Logger log=LoggerFactory.getLogger(getClass());
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connection established");
	}
	/**
	 * �����ı���Ϣ
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			log.info("Received message: "+message.getPayload());
			//ģ����ʱ
			Thread.sleep(2000);
			//�����ı���Ϣ
			session.sendMessage(new TextMessage("Server"));
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("Connection closed Status:"+status);
	}

}
