package com.xiaocui.webSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.chainsaw.Main;

import com.xiaocui.common.JsonSerializer;
import com.xiaocui.entity.LoanInfo;
import com.xiaocui.entity.Pkg1001;

/**
 * 非框架模式
 * @author LHX
 *
 */
@ServerEndpoint("/websocket/test")  
public class MyWebSocket {
	
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
     
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    //若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static Map<String,Session> webSocketMap = new HashMap<String,Session>();
     
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
     
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
//        webSocketSet.add(this);     //加入set中
        webSocketMap.put("webSocketSet", session);
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }
     
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
//        webSocketSet.remove(this);  //从set中删除
    	webSocketMap.remove("webSocketSet");
        subOnlineCount();           //在线数减1    
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
     
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws Exception 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
//        for(MyWebSocket item: webSocketSet){             
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
        //单例
        session.getBasicRemote().sendText("This is the frist server message");
		Thread.sleep(5000);
		session.getBasicRemote().sendText("同步发送");
        session.getBasicRemote().sendText("This is the last server message");
    }
    
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
     
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);//同步发送
        this.session.getAsyncRemote().sendText(message);//异步发送
    }
 
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }
     
    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
	
}
