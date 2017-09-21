package com.xiaocui.webSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.xiaocui.service.WebSocketService;

public class SystemWebSocketHandler implements WebSocketHandler{
	
	private static final ArrayList<WebSocketSession> users;
	
	@Autowired
	private WebSocketService webSocketService;
	
    static {
        users = new ArrayList<>();
    }
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connect to the websocket success......");
		users.add(session);
        String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        if(userName!= null){
            //查询未读消息
//            int count = webSocketService.getUnReadNews((String) session.getAttributes().get("WEBSOCKET_USERNAME"));
//            session.sendMessage(new TextMessage(count + ""));
        }
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println("webSocket建立");
		users.add(session);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if(session.isOpen()){
            session.close();
        }
		System.out.println("websocket connection error......");
        users.remove(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("websocket connection closed......");
        users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	/**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
	
	
}
