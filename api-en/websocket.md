# Websocket (2018-12-28)


- Websocket url: 
    test environment：wss://uat.smartcrypto.cc:444    
- Websocket request ,login required。
- when connection is created,  login required, then can subscribe and unsubscribe
- when connection is created,  required to send regular-'ping' to keep it alive. period:30 minutes.


## API LIST

| request                                                                   | description                                                    |
|:--------------------------------------------------------------------------|:---------------------------------------------------------------|
| [subscribe:API_ORDER](#subscribe-order)                     | subscribe order trading result                                 |
| [unsubscribe:API_ORDER](#subscribe-order)                   | unsubscribe order trading result                               |
| [subscribe:API_MARKETDEPTH](#subscribe-market-depth)   | subscribe aggregation quotation    (symbol:crypto pairs name)  |
| [unsubscribe:API_MARKETDEPTH](#subscribe-market-depth) | unsubscribe aggregation quotation   (symbol:crypto pairs name) |
| [subscribe:API_RISK_POSITION](#subscribe-risk-position)                     | subscribe risk    (symbol:crypto pairs name book: book abbreviation)                    |
| [unsubscribe:API_RISK_POSITION](#subscribe-risk-position)                   | unsubscribe risk   (symbol:crypto pairs name book: book abbreviation)                   |
| [ping](#ping)                                                                    | heart beat                                                     |


## WEBSOCKET API for creating connection

wss://demo.nexus.com:444/api/realtime?X-API-TOKEN=WHZWUFAxWlV0bzdMMzl5N1dmYy1z    

X-API-TOKEN:token(acquired from login）

### subscribe-market-depth
***request instruction***

```
{
	"id": "1630055515109",
	"action": "SUBSCRIBE",
	"event": "API_MARKETDEPTH",
	"symbol": "BTCUSDT",
	"depth": 5
}
```

| request                     | description                      |
|:----------------------------|:---------------------------------|
| ID |  |
| SYMBOL   | subscribe crypto pairs name   |
| DEPTH | crypto pairs name depth |

 ***params of return***

| NAME       | TYPE   | DESCRIBE           |
|:-----------|:-------|:-------------------|
| event      | string | event(API_QUOTE)   |
| commission | number | commission fee     |
| cp         | string | counter party name |
| symbol     | string | crypto pairs name  |
| price      | number | price              |
| volume     | number | volume             |

 ***example of return***

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

### subscribe-order
***order request***

```
{
	"id": "1630055515109",
	"action": "SUBSCRIBE",
	"event": "API_ORDER"
}
```

| request              | description                      |
|:---------------------|:---------------------------------|
| ID |  |

 ***params of order trading result***

| NAME          | TYPE    | DESCRIBE           |
|:--------------|:--------|:-------------------|
| event         | string  | event(API_ORDER)   |
| cpOrderId     | integer | CP order ID        |
| symbol        | string  | crypto pairs name  |
| orderId       | integer | parent order ID    |
| side          | string  | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/api-en/enum.md) |
| status        | string  | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/api-en/enum.md)       |
| orderType     | string  | [Order Type(OrderType)](https://github.com/nexusbroc/nexus/api-en/enum.md)        |
| executeAmount | number  | executed Amount    |
| executeVolume | number  | executed Volume    |
| orderVolume   | number  | order Volume       |
| pendingVolume | number  | pending Volume     |
| executeTime   | integer | executed Time      |
| orderTime     | integer | place order Time   |
| commission    | number  | commission         |
| timeInForce   | string  | [Time In Force(TimeInForce)](https://github.com/nexusbroc/nexus/api-en/enum.md)      |
| orderResult   | string  | [Order Result(OrderResult)](https://github.com/nexusbroc/nexus/api-en/enum.md)       |
| orderComment  | string  | comments           |

  ***example of order trading result***

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
 
### subscribe-risk-position
***request instruction***

```
{
	"id": "1630055515109",
	"action": "SUBSCRIBE",
	"event": "API_RISK_POSITION",
	"symbol": "BTCUSDT",
	"book": "A"
}
```

| request                              | description                                 |
|:-------------------------------------|:--------------------------------------------|
| SYMBOL   | subscribe crypto pairs name   |
| BOOK | book abbreviation |


 ***params of return***

| NAME       | TYPE   | DESCRIBE                  |
|:-----------|:-------|:--------------------------|
| event      | string | event (API_RISK_POSITION) |
| book       | string | book abbreviation         |
| cp         | string | counter party name        |
| openAmount | string | open amount               |
| openVolume | string | open volume               |
| symbol     | string | crypto pair name          |



 ***example of return***
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
***request instruction***

```
{
	"id": "1630055936231",
	"action": "PING",
	"event": ""
}
```
