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

		// SUBSCRIBE API_MARKETDEPTH
		session.getBasicRemote().sendText("{\"id\":\"1639121062583\",\"action\":\"SUBSCRIBE\",\"event\":\"API_MARKETDEPTH\",\"symbol\":\"BTCUSDT\",\"depth\":5}");

		// SUBSCRIBE API_ORDER
		session.getBasicRemote().sendText("{\"id\":\"1630055515109\",\"action\":\"SUBSCRIBE\",\"event\":\"API_ORDER\"}");

		// SUBSCRIBE API_RISK_POSITION
		session.getBasicRemote().sendText("{\"id\":\"1630055515109\",\"action\":\"SUBSCRIBE\",\"event\":\"API_RISK_POSITION\",\"symbol\":\"ALL\",\"book\":\"A\"}");


		// UNSUBSCRIBE
		//session.getBasicRemote().sendText("{\"id\":\"1639121062583\",\"action\":\"UNSUBSCRIBE\",\"event\":\"API_MARKETDEPTH\",\"symbol\":\"BTCUSDT\"}");

		Thread.sleep(Long.MAX_VALUE);
	}

}