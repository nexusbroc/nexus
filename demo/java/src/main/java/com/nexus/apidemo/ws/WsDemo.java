package com.nexus.apidemo.ws;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.nexus.apidemo.rest.RestServiceImpl;
import com.nexus.apidemo.common.Consts;

public class WsDemo {
	private static String uri = "wss://" + Consts.HOST + "/api/realtime?X-API-TOKEN=";

	public static void main(String[] args) throws Exception {
		RestServiceImpl service = new RestServiceImpl();
		String token = service.getToken("xiulian_jerry", "xiaowei01");

		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		URI r = URI.create(uri + token);
		Session session = container.connectToServer(WsDemoEndpoint.class, r);
		// Quote推送
		session.getBasicRemote().sendText("subscribe:apiQuote:65537");
		// 交易结果推送
		session.getBasicRemote().sendText("subscribe:apiOrder");

		Thread.sleep(Long.MAX_VALUE);
	}

}