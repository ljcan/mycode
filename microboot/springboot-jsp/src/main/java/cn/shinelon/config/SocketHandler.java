package cn.shinelon.config;

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
	/**
	 * 建立连接之后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connection established");
	}
	/**
	 * 处理文本消息
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			log.info("Received message: "+message.getPayload());
			//模拟延时
			Thread.sleep(2000);
			//发送文本消息
			session.sendMessage(new TextMessage("Server"));
	}
	/**
	 * 连接关闭之后
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("Connection closed Status:"+status);
	}

}
