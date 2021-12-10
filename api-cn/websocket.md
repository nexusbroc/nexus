# Websocket接口信息(2018-12-28)

WebSocket是HTML5一种新的协议(Protocol)。它实现了客户端与服务器全双工通信，使得数据可以快速地双向传播。通过一次简单的握手就可以建立客户端和服务器连接，服务器根据业务规则可以主动推送信息给客户端。

- Websocket请求地址:  
    测试环境：wss://uat.smartcrypto.cc:444    
- Websocket请求需要进行登录认证才可以访问。
- 创建连接且登陆后才可发送相应的指令进行消息的订阅和取消订阅。
- 创建连接后需要定时向服务器发送ping,用来保持连接.频率:30分

## 接口列表

| 请求指令                                                   | 描述                                     |
|:-----------------------------------------------------------|:-----------------------------------------|
| [subscribe:API_ORDER](#订阅订单成交结果)                    | 订阅订单成交结果                         |
| [unsubscribe:API_ORDER](#订阅订单成交结果)                  | 取消订阅订单成交结果                     |
| [subscribe:API_MARKETDEPTH](#订阅聚合行情深度)   | 订阅聚合行情深度     (symbol:币对名称)   |
| [unsubscribe:API_MARKETDEPTH](#订阅聚合行情深度) | 取消订阅聚合行情深度   (symbol:币对名称) |
| [subscribe:API_RISK_POSITION](#订阅风控详情)        | 订阅风控详情     (symbol:币对名称 book:账簿名称)         |
| [unsubscribe:API_RISK_POSITION](#取消订阅风控详情)  | 取消订阅风控详情   (symbol:币对名称  book:账簿名称)       |
| [ping](#保持空闲连接)                                      | 心跳命令                                 |


## WEBSOCKET API

wss://demo.nexus.com:444/api/realtime?X-API-TOKEN=WHZWUFAxWlV0bzdMMzl5N1dmYy1z    

X-API-TOKEN:token(通过login获得）

### 订阅行情深度
***汇率请求指令***

```
{
	"id": "1630055515109",
	"action": "SUBSCRIBE",
	"event": "API_MARKETDEPTH",
	"symbol": "BTCUSDT",
	"depth": 5
}
```

| 请求指令                    | 描述             |
|:----------------------------|:-----------------|
| ID |  |
| SYMBOL | 取消订阅行情深度 |
| DEPTH | 市场深度 |

 ***汇率推送返回参数***

| NAME       | TYPE   | DESCRIBE        |
|:-----------|:-------|:----------------|
| event      | string | 事件(API_QUOTE) |
| commission | number | 手续费          |
| cp         | string | 交易对手名称    |
| symbol     | string | 交易币对名称    |
| price      | number | 价格            |
| volume     | number | 数量            |

 ***汇率推送返回参数示例***

```
 {
    "event": "API_MARKETDEPTH",
    "data": {
        "asks": [
            {
                "cp": "b2c2",
                "price": "48694.00",
                "time": "1631948616845",
                "volume": "19.0000"
            }
        ],
        "bids": [
            {
                "cp": "b2c2",
                "price": "48614.00",
                "time": "1631948616845",
                "volume": "19.0000"
            }
        ],
        "symbol": "BTCUSDT"
    }
}
```
### 订阅订单成交结果
***订单请求指令***

```
{
	"id": "1630055515109",
	"action": "SUBSCRIBE",
	"event": "API_ORDER"
}
```

| 请求指令             | 描述                 |
|:---------------------|:---------------------|
| ID |  |

 ***订单成交推送返回参数***

| NAME          | TYPE    | DESCRIBE                                                                          |
|:--------------|:--------|:----------------------------------------------------------------------------------|
| event         | string  | 事件(API_ORDER)                                                                   |
| cpOrderId     | integer | CP 订单ID                                                                         |
| symbol        | string  | 交易币对名称                                                                      |
| accountId     | integer | 账户ID                                                                            |
| orderId       | integer | 父订单                                                                            |
| side          | string  | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md)        |
| status        | string  | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md)     |
| orderType     | string  | [订单类型(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md)   |
| executeAmount | number  | 成交金额                                                                          |
| executeVolume | number  | 成交数量                                                                          |
| orderVolume   | number  | 订单数量                                                                          |
| pendingVolume | number  | 未成交数量                                                                        |
| executeTime   | integer | 成交时间                                                                          |
| orderTime     | integer | 订单时间                                                                          |
| commission    | number  | 手续费                                                                            |
| timeInForce   | string  | [过期类型(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md) |
| orderResult   | string  | [结果(OrderResult)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md)     |
| orderComment  | string  | 备注                                                                              |

  ***订单成交结果推送返回参数示例***

```
{
	"event": "API_ORDER",
	"data": [{
		"clientOrderId": "111",
		"cp": "",
		"cpOrderid": "0",
		"cpOrders": [{
			"commission": "0",
			"cp": "b2c2",
			"cpOrderid": "1513108459472911381",
			"executeAmount": "485.77",
			"executeTime": "1631950584647",
			"executeVolume": "0.01",
			"executions": [{
				"commissionCurrency": "USDT",
				"cp": "b2c2",
				"cpOrderId": "1513108459472911381",
				"executeAmount": "485.77",
				"executeId": "1513108462073399317",
				"executePrice": "48577",
				"executeTime": "1631950584647",
				"executeVolume": "0.01",
				"orderId": "1513108459447745045",
				"orderType": "MARKET",
				"side": "SELL",
				"symbol": "BTCUSDT"
			}],
			"orderComment": "[B:1513108458764582445.0",
			"orderId": "1513108459447745045",
			"orderResult": "FILLED",
			"orderTime": "1631950584476",
			"orderType": "MARKET",
			"orderVolume": "0.01",
			"pendingVolume": "0",
			"side": "SELL",
			"status": "EXECUTED",
			"symbol": "BTCUSDT",
			"timeInForce": "IOC"
		}],
		"executeAmount": "485.77",
		"executeTime": "1631950584647",
		"executeVolume": "0.01",
		"orderId": "1513108459447745045",
		"orderResult": "FILLED",
		"orderTime": "1631950584467",
		"orderType": "MARKET",
		"orderVolume": "0.01",
		"pendingVolume": "0",
		"side": "SELL",
		"status": "EXECUTED",
		"symbol": "BTCUSDT",
		"timeInForce": "IOC"
	}]
}
```
 
### 订阅风控详情
***风控请求指令***

```
{
	"id": "1630055515109",
	"action": "SUBSCRIBE",
	"event": "API_RISK_POSITION",
	"symbol": "BTCUSDT",
	"book": "A"
}
```

| 请求指令                             | 描述                               |
|:-------------------------------------|:-----------------------------------|
| SYMBOL | 取消订阅行情深度 |
| BOOK | 账簿缩写 |

 ***风控推送返回参数***

| NAME       | TYPE   | DESCRIBE                |
|:-----------|:-------|:------------------------|
| event      | string | 事件(API_RISK_POSITION) |
| book       | string | 账簿缩写                |
| cp         | string | 交易对手缩写            |
| openAmount | string | 净头寸                  |
| openVolume | string | 金额                    |
| symbol     | string | 币对名称                |


 ***风控推送返回参数示例***
 ```
{
	"event": "API_RISK_POSITION",
	"data": [{
		"amount": "-7622.909246840000",
		"book": "A",
		"broker": "HOTCOINEX",
		"dailyAmount": "485.77000000",
		"dailyVolume": "-0.0100000000",
		"grossAmount": "-8045.534413060000",
		"hedgePl": "0",
		"hedgeVolume": "0",
		"mtmAmount": "-7598.827850000000",
		"netPl": "9.63575597",
		"netVolume": "0.0100000000",
		"symbol": "BTCUSDT",
		"volume": "0.160100000000"
	}]
}
 ```
### ping
***请求指令***

```
{
	"id": "1630055936231",
	"action": "PING",
	"event": ""
}
```
