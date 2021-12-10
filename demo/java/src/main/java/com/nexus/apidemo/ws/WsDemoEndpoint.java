package com.nexus.apidemo.ws;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;

@ClientEndpoint
public class WsDemoEndpoint {
	private static ObjectMapper objectMapper = new ObjectMapper();

	@OnOpen
	public void onOpen(Session session) {
		System.out.println(String.format("Session[%s] is open.", session));
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println(String.format("Session[%s] client onMessage: [%s]", session, message));
	}

	@OnError
	public void onError(Session session, Throwable t) {
		System.out.println(String.format("Session[%s] error", session));
		t.printStackTrace();
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println(String.format("Session[%s] is closed. ref: [%s]", session, closeReason));
	}

}