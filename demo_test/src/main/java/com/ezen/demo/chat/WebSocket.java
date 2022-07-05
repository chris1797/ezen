package com.ezen.demo.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ServerEndpoint(value = "/websocket", configurator=HttpSessionConfig.class)
public class WebSocket 
{
    /* 웹소켓 세션 보관용 ArrayList */
	// 그냥 해당 프로그램에서 세션을 보관해보는 것
//    private static ArrayList<Session> sessionList = new ArrayList<Session>();
    // ArrayList<Session> 은 Session을 담고잇는 List라는 뜻
    // 이 세션을 이용해야 user의 브라우저로 내려가는 데이터를 구성할 수 있음
    // 클라들의 접속정보는 서버의 세션에 저장되게 되고 각각의 세션id를 통해 클라들을 식별하여 원하는 경로로 전송

    private static Map<String, Session> sessionMap = new HashMap<>();
    /* 웹소켓 사용자 접속시 호출됨  */
    @OnOpen
    public void handleOpen(Session session, EndpointConfig config) { //세션이라는 파라미터가 중요, session은 user를 식별하는 수단(파라미터)
    	//session id는 서버에서 부여
    	//하지만 session id만 가지고 식별하는 것은 곤란함
    	//
        if (session != null) {
            String sessionId = session.getId();
            HttpSession httpSession = (HttpSession) config.getUserProperties().get("session");
            // ㄴ 여기서 user의 ID 확인 가능
            String uid = (String) httpSession.getAttribute("uid");
            // 이제 session, HttpSession을 결합해서 자료구조를 세팅해줘야 함 (Map 형태로)
            
            sessionMap.put(uid, session); // uid를 이용해서 session을 꺼낼 수 있게 됨
            							  // 결론적으로 "smith"를 이용해서 HttpSession, session을 모두 이용
            
            
            System.out.println("client is connected. sessionId == [" + sessionId + ", " + uid + "]");
//            sessionList.add(session);
            
            Map<String, String> map = new HashMap<>();
            map.put("from", uid);
            map.put("contents", "Connected");
            
            // Map에다 담아서 JSON문자열을 내려보내 준다는 뜻
            try {
            	String jsStr = new ObjectMapper().writeValueAsString(map);
            	sendMessageToAll(jsStr);
            } catch (JsonProcessingException e) {
            	e.printStackTrace();
            }
            
            
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
//            sendMessageToAll("--> [USER-" + sessionId + "(" + uid + ")" + "] is connected. ");
            //현재 접속중인 모든 user들에게 해당 메세지 전송
        }
    }

    /* 웹소켓 이용자로부터 메시지가 전달된 경우 실행됨 */
    // 해당 Annotation이 꼭 @OnMessage여야 함
    @OnMessage
    public String handleMessage(String message, Session session) { //반드시 두 파라미터가 있어야 함
       
    	if (session != null) {
            //System.out.println("getId : " + session.getId());
            String uid = getUserBySession(session);
            System.out.println(message);
            System.out.println("message is arrived. sessionId == [" + uid + "] / message == [" + message + "]");
            
            Map<String, String> map1 = null;
            ObjectMapper mapper = new ObjectMapper();
            
			try {
				map1 = mapper.readValue(message, Map.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
            
            String sendkey = map1.get("to");
            Session sendss = sessionMap.get(sendkey);
            
            Set<String> keys = sessionMap.keySet();
            Iterator<String> itr = keys.iterator();
            
            Map<String, String> map = new HashMap<>();
            map.put("from", uid);
            map.put("contents", message);
            
            if(sendss != null) {
            	while(itr.hasNext()) {
            		String key = itr.next();
            		Session ss = sessionMap.get(key);
            		if(key == sendkey) {
            			sendss = sessionMap.get(sendkey);
            			map.put("to", sendkey);
            		}
            		if(ss == null || !ss.isOpen()) {
            			continue;
            		}
            	}
            	session.getAsyncRemote().sendText(message);
            	sendss.getAsyncRemote().sendText(message);
            } else {
            	sendMessageToAll(message);
            }
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
//            sendMessageToAll("[USER-"  + uid + "] " + message);
        }
        
        return null;
    }

    /* 웹소켓 이용자가 연결을 해제하는 경우 실행됨 */
    // 접속을 끊는 것
    @OnClose
    public void handleClose(Session session) {
        if (session != null) {
        	String uid = getUserBySession(session);
            String sessionId = session.getId();
            System.out.println("client is disconnected. sessionId == [" + sessionId + "]");
            
            /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
            //sendMessageToAll("***** [USER-" + uid + "] is disconnected. *****");
            Map<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("contents", "disconnected");
            
            // Map에다 담아서 JSON문자열을 내려보내 준다는 뜻
            try {
            	String jsStr = new ObjectMapper().writeValueAsString(map);
            	sendMessageToAll(jsStr);
            } catch (JsonProcessingException e) {
            	e.printStackTrace();
            }
        }
    }

    /* 웹소켓 에러 발생시 실행됨 */
    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
    
    
    /* 웹소켓에 접속한 모든 이용자에게 메시지 전송 */
    private boolean sendMessageToAll(String message) {
        if (sessionMap == null) {
            return false;
        }

        int sessionCount = sessionMap.size();
        if (sessionCount < 1) {
            return false;
        }

//        Session singleSession = null;

        
        // 순서가 솔팅되어있지 않기 때문에 Iterator를 쓰는 것
        // 반복이 가능한 반복자 Iterator 생성
        // 이걸로 루프를 돌려야 함
        Set<String> keys = sessionMap.keySet();
        Iterator<String> itr = keys.iterator(); 

        while(itr.hasNext()) {
        	String key = itr.next();
        	Session ss = sessionMap.get(key);
        	if(ss == null) {
        		continue;
        	}

        	if(!ss.isOpen()) {
        		continue;
        	}

        	ss.getAsyncRemote().sendText(message);

//        for (int i = 0; i < sessionCount; i++) {
//            singleSession = sessionMap.get(i);
//            if (singleSession == null) {
//                continue; //null이면 다음
//            }
//
//            if (!singleSession.isOpen()) { //열리지 않았으면,
//                continue;
//            }

            //sessionMap.get(i).getAsyncRemote().sendText(message); //sessionList.get(i) 대신 singletSession 써도 됨
            // getAsyncRemote(). : chat메세지에 연결되어 있는 chat클라이언트에게 sendText(message)한다는 뜻
            // 즉 session이 없으면 메세지를 보낼 수 없다.
        }

        return true;
    }
    
    private String getUserBySession(Session ss) {
    	Set<String> keys = sessionMap.keySet();
    	Iterator<String> itr = keys.iterator();
    	
    	while(itr.hasNext()) {
    		String key = itr.next();
    		Session _ss = sessionMap.get(key);
    		if(_ss == ss) return key;
    	}
    	return null;
    }
}