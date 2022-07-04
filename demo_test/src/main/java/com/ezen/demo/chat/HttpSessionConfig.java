package com.ezen.demo.chat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class HttpSessionConfig extends Configurator {
	
	
	//설정자 class
	//Configurator에 HttpSession을 설정하는 것을 여기에서 하는 것
	@Override
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		//Create HttpSession Object
		HttpSession session = (HttpSession) request.getHttpSession();
		//context도 필요하면 불어와서 쓸 수 있으나 현재는 사용X
		ServletContext context = session.getServletContext();
		//getUserProperties는 Map형태
		
		//config를 이용하여 HttpSession을 등록해준 것 
		config.getUserProperties().put("session", session); // key를 "session"으로 해서 session 저장
		config.getUserProperties().put("context", context);
	}
	
}
