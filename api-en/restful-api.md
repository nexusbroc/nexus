# Restful API informations(2021-12-10)


- API url 
    test environment：https://demo.go-nexus.io/     
- API test tools  
    test environment：https://demo.go-nexus.io/swagger-ui.html 

## API LIST

| request                                    | type | description                   |
| :------------------------------------------ | :--- | :--------------------- |
| [/api/v1/operator/login](#login)                   | POST | login                   |
| [/api/v1/market/cps](#get-counter-party-list)                       | GET  | get counter party list          |
| [/api/v1/market/symbols](#get-crypto-pairs-list)                   | GET  | get crypto pairs list       |
| [/api/v1/trades/place](#place-order)                     | POST | place orders (SOR)               |
| [/api/v1/trades/orderDetail](#get-detail-of-orders)               | GET  | get  detail of orders         |
| [/api/v1/trades/clientOrderDetail](#get-detail-of-client-Orders)               | GET  | get  detail  of client orders         |
| [/api/v1/trades/orderHistory](#get-history-list-of-orders)              | GET  | get history  list of orders   |
| [/api/v1/trades/cpOrderHistory](#get-CP-history-list-of-placed-orders)            | GET  | get CP history list of placed orders |
| [/api/v1/trades/cpExecutionHistory](#get-CP-history-list-of-executed-orders)        | GET  | get CP history list of executed orders |
| [/api/v1/trades/cpExecutionsByCpOrderId](#get-CP-detail-of-executed-order)         | GET  | get CP detail of executed orders         |
| [/api/v1/trades/cpExecutionsByOrderId](#get-CP-detail-of-executed-order)         | GET  | get CP detail of executed orders         |
| [/api/v1/market/cpAccountInfo](#get-account-info)             | GET  | get account info         |
| [/api/v1/making/makingConfigs](#get-making-config)  | GET  | get making config         |
| [/api/v1/making/makingConfigs/create](#add-making-config)  | PUT  | add making config         |
| [/api/v1/making/makingConfigs/update](#update-making-config)  | POST  | update making config         |
| [/api/v1/making/makingConfigs/delete](#delete-making-config)  | DELETE  | delete making config         |
| [/api/v1/making/targetPriceConfigs](#get-target-price-config)  | GET  | get target price config         |
| [/api/v1/making/targetPriceConfigs/create](#add-target-price-config)  | PUT  | add target price config         |
| [/api/v1/making/targetPriceConfigs/update](#update-target-price-config)  | POST  | update target price config         |
| [/api/v1/making/targetPriceConfigs/delete](#delete-target-price-config)  | DELETE  | delete target price config         |
## Restful API
### login
POST /api/v1/operator/login

***request params***

| NAME     | REQUIRED | TYPE   | DESCRIPTION | DEFAULT | VALUES RANGE |
| :------- | :------- | :----- | :------- | :------ | :----------- |
| loginId  | Y        | string | id    |         |              |
| password | Y        | string | password      |         |              |

***params of return***

| NAME  | TYPE   | DESCRIPTION |
| :---- | :----- | :------- |
| token | string | token    |

***example of return***

```
{
  "data": {
    "token": "ZElHYkFsRktuZDduUG1udzI2V1lldyIsImFsZyI6IkEyNTZHQ01LVyIsIml2IjoicjZzLU83TGlYTUFYVEVxeSJ9.92p5FlhYwx6YCLeUlh-rlE3o_8.-wL8D1uL3GEEE3kj.qmBbSsSy92nYBt4WOLbU5MCH1NPbXO6k6_b5uGEG63xcQJ-Ny9K1VCMRtChQRol6l6fb5rNobNnOFAgeYzy2cqkje4HgUlL3BSRNlDj7G6W-60MwM2af7U2xshESv8LqLux2GZxPzCBmJz__HgluDxJwX2qNsMvOjM5k7Ckce8E1vHRJA18pkMzAL41HMFyzaf67Mp2SgXIaFHXVKVxd32132"
  },
  "result": "SUCCESS",
  "type": "API"
}
```

### get counter party list
GET /api/v1/market/cps

request with Headers:    

X-API-TOKEN:token（acquired from login）

***params of return***

| NAME           | TYPE    | DESCRIPTION |
| :------------- | :------ | :------- |
| name           | string  | name |
| tradingEnabled | boolean | trading constraints |
| type           | string  | [Cp Type(LpType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#LpType)        | 

***example of return***
```
{
  "data": [
    {
      "name": "binance",
      "tradingEnabled": false,
      "type": "LIQUIDITY_PROVIDER"
    },
    {
      "name": "okex",
      "tradingEnabled": false,
      "type": "LIQUIDITY_PROVIDER"
    },
    {
      "name": "hotcoinex",
      "tradingEnabled": true,
      "type": "LIQUIDITY_DEMANDER"
    },
    {
      "name": "AscendEx",
      "tradingEnabled": true,
      "type": "LIQUIDITY_PROVIDER"
    },
    {
      "name": "cex",
      "tradingEnabled": true,
      "type": "LIQUIDITY_PROVIDER"
    },
    {
      "name": "b2c2",
      "tradingEnabled": true,
      "type": "LIQUIDITY_PROVIDER"
    }
  ],
  "result": "SUCCESS",
  "type": "API"
```

### get crypto pairs list
GET  /api/v1/market/symbols

request with Headers:    

X-API-TOKEN:token（acquired from login）

***params of return***

| NAME               | TYPE       | DESCRIPTION     |
| :----------------- | :--------- | :----------- |
| name               | string     | crypto pair name         |
| tradingMaxVolume   | number     | trading Max Volume |
| tradingMinVolume   | number     | trading Min Volume |
| tradingPriceScale  | integer    | trading Price Scale     |
| tradingVolumeScale | integer    | trading Volume Scale   |

***example of return***

```
{
  "data": [
    {
      "name": "ASDUSDT",
      "pricingFrequency": 60,
      "tradingMaxVolume": "100",
      "tradingMinVolume": "1",
      "tradingPriceScale": 5,
      "tradingVolumeScale": 0
    },
    {
      "name": "OMGUSDT",
      "pricingFrequency": 60,
      "tradingMaxVolume": "10",
      "tradingMinVolume": "0.01",
      "tradingPriceScale": 4,
      "tradingVolumeScale": 4
    },
    {
      "name": "BTCUSDT",
      "pricingFrequency": 60,
      "tradingMaxVolume": "1",
      "tradingMinVolume": "0.0001",
      "tradingPriceScale": 2,
      "tradingVolumeScale": 4
    }
  ],
  "result": "SUCCESS",
  "type": "API"
}
```

### place order
POST /api/v1/trades/place

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request***

RequestBody:（order）

| NAME          | REQUIRED | TYPE       | DESCRIPTION              | DEFAULT | VALUES RANGE |
| :------------ | :------- | :--------- | :-------------------- | :------ | :----------- |
| cps           |          | array      | counter party name            |         |              |
| book          |          | string     | book                    |         |              |
| symbol        | Y        | string     | crypto pair name            |         |              |
| manualHedge   | Y        | bool       | manual hedge            |         |              |
| orderPrice    | Y        | number     | order Price               |         |              |
| slippage      |          | number     | slippage                 |         |              |
| orderVolume   | Y        | number     | order Volume               |         |              |
| side          | Y        | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)      |         |              |
| orderType     | Y        | string     | [Order Type(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)  |         |              |
| timeInForce   | Y        | string     | [Time InForce(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |         |              |
| clientOrderId |          | string     | clinet order id |         |              |

***example of request***

```
{
  "book": "A",
  "clientOrderId": "jerry-test",
  "cps": [ ""],
  "manualHedge": false,
  "orderPrice": "0",
  "orderType": "MARKET",
  "orderVolume": "0.1",
  "side": "BUY",
  "slippage": 0,
  "symbol": "BTCUSDT",
  "timeInForce": "IOC"
}
```

 ***params of return***

| NAME          | TYPE       | DESCRIPTION              |
| :------------ | :--------- | :-------------------- |
| orderID       | integer    | order ID                |
| cpOrderId     | integer    | CP order ID                    |
| cp            | string     | counter party name                |
| symbol        | string     | crypto pair name              |
| executeAmount | number     | executed Amount              |
| executeVolume | number     | executed Volume              |
| orderVolume   | number     | order Volume              |
| pendingVolume | number     | pending Volume              |
| side          | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)       |
| orderType     | string     | [Order Type(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)  |
| status        | string     | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| timeInForce   | string     | [Time InForce(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| orderResult   | string     | [Dealing Result(DealingResult)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#dealingresult) |
| orderTime     | integer    | place order Time              |
| executeTime   | integer    | executed Time                  |
| clientOrderId | string     | clinet order id                  |
| commission    | number     | commission         |

***example of return***

```
{
	"data": {
		"order": {
			"clientOrderId": "0001",
			"cpOrders": [{
				"commission": "0",
				"cp": "binance",
				"cpOrderId": "1022333915667835925",
				"executeAmount": "0",
				"executeTime": "0",
				"executeVolume": "0",
				"orderTime": "1573445699770",
				"orderType": "SLIPPAGE",
				"orderVolume": "0.01",
				"pendingVolume": "0",
				"side": "SELL",
				"status": "PENDING",
				"symbol": "BTCUSDT",
				"timeInForce": "IOC"
			}],
			"executeAmount": "0",
			"executeTime": "0",
			"executeVolume": "0",
			"orderId": "1022333915667835413",
			"orderTime": "1573445699770",
			"orderVolume": "0.01",
			"pendingVolume": "0",
			"symbol": "BTCUSDT"
		},
		"orderResult": "SUCCESS"
	},
	"result": "SUCCESS",
	"type": "API"
}
```

***example of return***

```
{
  "data": {
    "data": {
      "side": "INVALID"
    },
    "type": "VALIDATION"
  },
  "result": "INVALID_REQUEST",
  "type": "API"
}
```

### get detail of orders
GET /api/v1/trades/orderDetail

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request params***

| NAME     | REQUIRED | TYPE | DESCRIPTION | DEFAULT | VALUES RANGE |
| :------- | :------- | :--- | :------- | :------ | :----------- |
| orderId/cpOrderId | Y        | integer | order ID/Cp order ID | -       |              |

***params of return***

| NAME          | TYPE       | DESCRIPTION              |
| :------------ | :--------- | :-------------------- |
| orderID       | integer    | order ID   /cp order ID        |
| cpOrderId     | integer    | CP order ID                    |
| cp            | string     | counter party name                |
| symbol        | string     | crypto pair name              |
| executeAmount | number     | executed Amount              |
| executeVolume | number     | executed Volume              |
| orderVolume   | number     | order Volume              |
| pendingVolume | number     | pending Volume              |
| side          | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)        |
| orderType     | string     | [Order Type(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| status        | string     | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| timeInForce   | string     | [Time InForce(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| orderTime     | integer    | place order Time              |
| executeTime   | integer    | executed Time              |
| clientOrderId | string     | client order ID              |

***example of return***

```
{
  "data": {
    "order": {
      "executeAmount": "47861.03368977",
      "executeTime": "1630310121634",
      "executeVolume": "1",
      "id": "1499347243466593301",
      "orderResult": "FILLED",
      "orderTime": "1630310119689",
      "orderType": "MARKET",
      "orderVolume": "1",
      "pendingVolume": "0",
      "side": "SELL",
      "status": "EXECUTED",
      "symbol": "BTCUSDT",
      "timeInForce": "IOC"
    }
  },
  "result": "SUCCESS",
  "type": "API"
}
```

### get detail of client orders
GET /api/v1/trades/clientOrderDetail

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request params***

| NAME          | REQUIRED | TYPE | DESCRIPTION | DEFAULT | VALUES RANGE |
| :------------ | :------- | :--- | :------- | :------ | :----------- |
| clientOrderId | Y        | string | client order ID | -       |              |

***params of return***

| NAME          | TYPE       | DESCRIPTION              |
| :------------ | :--------- | :-------------------- |
| orderID       | integer    | order ID                |
| cpOrderId     | integer    | CP order ID                    |
| cp            | string     | counter party name                |
| symbol        | string     | crypto pair name              |
| executeAmount | number     | executed Amount              |
| executeVolume | number     | executed Volume              |
| orderVolume   | number     | order Volume              |
| pendingVolume | number     | pending Volume              |
| side          | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)        |
| orderType     | string     | [Order Type(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| status        | string     | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| timeInForce   | string     | [Time InForce(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| orderTime     | integer    | place order Time              |
| executeTime   | integer    | executed Time              |
| clientOrderId | string     | client order ID              |

***example of return***

```
{
  "data": {
    "order": {
      "clientOrderId": "0001",
      "cpOrders": [
        {
          "commission": "0",
          "cp": "binance",
          "cpOrderId": "1022333915667835925",
          "executeAmount": "0",
          "executeTime": "0",
          "executeVolume": "0",
          "orderComment": "bands,1022333914200245293.0",
          "orderResult": "NONE",
          "orderTime": "1573445699770",
          "orderType": "SLIPPAGE",
          "orderVolume": "0.01",
          "pendingVolume": "0.01",
          "rejectReason": "",
          "side": "SELL",
          "status": "REJECTED",
          "symbol": "BTCUSDT",
          "timeInForce": "IOC"
        }
      ],
      "executeAmount": "0",
      "executeTime": "0",
      "executeVolume": "0",
      "orderId": "1022333915667835413",
      "orderTime": "1573445699770",
      "orderVolume": "0.01",
      "pendingVolume": "0.01",
      "symbol": "BTCUSDT"
    }
  },
  "result": "SUCCESS",
  "type": "API"
}
```

### get-CP-detail-of-executed-order 
GET  /api/v1/trades/cpExecutionsByCpOrderId
GET  /api/v1/trades/cpExecutionsByOrderId

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request params***

| NAME    | REQUIRED | TYPE | DESCRIPTION | DEFAULT | VALUES RANGE |
| :------ | :------- | :--- | :------- | :------ | :----------- |
| cpOrderId/OrderId | Y        | integer | CP ORDER ID/ORDER ID   | -       |              |

***params of return***

| NAME           | TYPE       | DESCRIPTION       |
| :------------- | :--------- | :------------- |
| cp             | string     | counter party name          |
| symbol         | string     | crypto pair name       |
| side           | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side) |
| executeAmount  | number     | executed Amount       |
| executePrice   | number     | executed Price       |
| cpExecutionid  | number     | CP execution ID       |
| executeVolume  | number     | executed Volume       |
| executeTime    | integer    | executed Time       |
| commission     | number     | commission         |
| executeComment | string     | comment       |

***example of return***

```
{
  "data": {
    "executions": [
      {
        "commission": "8.59965288",
        "commissionCurrency": "USDT",
        "cp": "chainup",
        "cpOrderId": "1023996147503518741",
        "executeAmount": "859.965288",
        "executeComment": "CommissionAsset: USDT",
        "executeId": "1023996281897517589",
        "executePrice": "8775.156",
        "executeTime": "1573643868403",
        "executeVolume": "0.098",
        "orderType": "LIMIT",
        "side": "SELL",
        "symbol": "BTCUSDT"
      }
    ]
  },
  "result": "SUCCESS",
  "type": "API"
}
```


 ### get history list of orders
 GET  /api/v1/trades/orderHistory

request with Headers:    

X-API-TOKEN:token（acquired from login）

 ***request params***

| NAME     | REQUIRED | TYPE    | DESCRIPTION          | DEFAULT | VALUES RANGE |
| :------- | :------- | :------ | :---------------- | :------ | :----------- |
| cp       |          | string   | counter party name             | -       |              |
| symbol   |          | string | crypto pair name          | -       |              |
| status   |          | string  | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus) | -       |              |
| from     | Y        | integer    | time started         | -       |              |
| to       | Y        | integer    | time ended         | -       |              |
| pageNo   |          | integer | page NO              | -       |              |

 ***params of return***

| NAME          | TYPE       | DESCRIPTION              |
| :------------ | :--------- | :-------------------- |
| pageCount     | integer    | Counts of pages                |
| pageNo        | integer    | page NO                  |
| pageSize      | integer    | page Size              |
| total         | integer    | total NO                |
| cpOrderId     | integer    | CP order ID                    |
| cp            | string     | counter party name              |
| symbol        | string     | crypto pair name             |
| orderId       | integer    | parent ID              |
| side          | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)       |
| status        | string     | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)    |
| orderResult   | string     | [Dealing Result(DealingResult)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#dealingresult)    |
| orderType     | string     | [Order Type(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| orderPrice    | number     | order Price              |
| executeAmount | number     | executed Amount              |
| orderVolume   | number     | order Volume              |
| pendingVolume | number     | pending Volume              |
| executeVolume | number     | executed Volume              |
| executeTime   | integer    | executed Time              |
| orderTime     | integer    | place order Time              |
| timeInForce   | string     | [Time InForce(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| commission    | number     | commission                |
| rejectReason  | string     | reject Reason              |
| orderComment  | string     | comment                  |

 ***example of return***

```
{
  "data": {
    "pageCount": 1,
    "pageNo": 1,
    "pageSize": 100,
    "records": [
      {
        "clientOrderId": "jerry-test",
        "cp": "",
        "cpOrderid": "0",
        "executeAmount": "9647.5",
        "executeTime": "1630313019219",
        "executeVolume": "0.2",
        "orderId": "1499371553970853397",
        "orderResult": "FILLED",
        "orderTime": "1630313017721",
        "orderType": "MARKET",
        "orderVolume": "0.2",
        "pendingVolume": "0",
        "side": "BUY",
        "status": "EXECUTED",
        "symbol": "BTCUSDT",
        "timeInForce": "IOC"
      },
      {
        "book": "A",
        "clientOrderId": "jerry-test",
        "cp": "",
        "cpOrderid": "0",
        "executeAmount": "4814.07",
        "executeTime": "1630312583631",
        "executeVolume": "0.1",
        "orderId": "1499367899530321941",
        "orderResult": "FILLED",
        "orderTime": "1630312582078",
        "orderType": "MARKET",
        "orderVolume": "0.1",
        "pendingVolume": "0",
        "side": "BUY",
        "status": "EXECUTED",
        "symbol": "BTCUSDT",
        "timeInForce": "IOC"
      },
      {
        "book": "A",
        "clientOrderId": "jerry-test",
        "cp": "",
        "cpOrderid": "0",
        "executeAmount": "4814.07",
        "executeTime": "1630312583102",
        "executeVolume": "0.1",
        "orderId": "1499367894606206997",
        "orderResult": "FILLED",
        "orderTime": "1630312581491",
        "orderType": "MARKET",
        "orderVolume": "0.1",
        "pendingVolume": "0",
        "side": "BUY",
        "status": "EXECUTED",
        "symbol": "BTCUSDT",
        "timeInForce": "IOC"
      },
      {
        "book": "A",
        "clientOrderId": "jerry-test",
        "cp": "",
        "cpOrderid": "0",
        "executeAmount": "4814.07",
        "executeTime": "1630312564313",
        "executeVolume": "0.1",
        "orderId": "1499367736782838805",
        "orderResult": "FILLED",
        "orderTime": "1630312562677",
        "orderType": "MARKET",
        "orderVolume": "0.1",
        "pendingVolume": "0",
        "side": "BUY",
        "status": "EXECUTED",
        "symbol": "BTCUSDT",
        "timeInForce": "IOC"
      },
      {
        "book": "A",
        "clientOrderId": "jerry-test",
        "cp": "",
        "cpOrderid": "0",
        "executeAmount": "4814.05",
        "executeTime": "1630312552896",
        "executeVolume": "0.1",
        "orderId": "1499367632369779221",
        "orderResult": "FILLED",
        "orderTime": "1630312550230",
        "orderType": "MARKET",
        "orderVolume": "0.1",
        "pendingVolume": "0",
        "side": "BUY",
        "status": "EXECUTED",
        "symbol": "BTCUSDT",
        "timeInForce": "IOC"
      }
    ],
    "total": 47
  },
  "result": "SUCCESS",
  "type": "API"
}
```

### get CP history list of placed orders
GET  /api/v1/trades/cpOrderHistory

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request params***

| NAME     | REQUIRED | TYPE    | DESCRIPTION          | DEFAULT | VALUES RANGE |
| :------- | :------- | :------ | :---------------- | :------ | :----------- |
| cp       |          | string  | counter party name             | -       |              |
| symbol   |          | string  | crypto pair name          | -       |              |
| status   |          | string  | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus) | -       |              |
| from     | Y        | integer | time started          | -       |              |
| to       | Y        | integer | time ended          | -       |              |
| pageNo   |          | integer | page NO              | -       |              |

***params of return***

| NAME          | TYPE       | DESCRIPTION              |
| :------------ | :--------- | :-------------------- |
| pageCount     | integer    | counts of pages                |
| pageNo        | integer    | page NO                  |
| pageSize      | integer    | page Size              |
| total         | integer    | total amount                |
| cpOrderid     | integer    | CP Order ID                    |
| cp            | string     | counter party name                |
| symbol        | string     | crypto pair name              |
| orderId       | integer    | parent order ID              |
| side          | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)      |
| status        | string     | [Order Status(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| orderType     | string     | [Order Type(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| orderResult   | string     | [Dealing Result(DealingResult)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#dealingresult)    |
| executeAmount | number     | executed Amount              |
| orderPrice    | number     | order Price              |
| executeVolume | number     | executed Volume              |
| orderVolume   | number     | order Volume              |
| pendingVolume | number     | pending Volume              |
| orderTime     | integer    | place order Time              |
| executeTime   | integer    | executed Time              |
| timeInForce   | string     | [Time InForce(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| commission    | number     | commission                |
| rejectReason  | string     | reject Reason              |
| orderComment  | string     | comment                  |

***example of return***

```
{
  "data": {
    "pageCount": 57,
    "pageNo": 1,
    "pageSize": 100,
    "records": [
      {
        "commission": "0",
        "cp": "chaoex",
        "cpOrderid": "1022439221994129429",
        "executeAmount": "24598.46717",
        "executeTime": "1573458255000",
        "executeVolume": "126.836",
        "orderId": "0",
        "orderPrice": "194.43",
        "orderResult": "FILLED",
        "orderTime": "1573458253262",
        "orderType": "LIMIT",
        "orderVolume": "126.836",
        "pendingVolume": "0",
        "side": "BUY",
        "status": "EXECUTED",
        "symbol": "ETHUSDT",
        "timeInForce": "GTC"
      },
      {
        "commission": "0",
        "cp": "chaoex",
        "cpOrderid": "1022439182139724309",
        "executeAmount": "23426.4013",
        "executeTime": "1573458249000",
        "executeVolume": "118.48",
        "orderId": "0",
        "orderPrice": "198.51",
        "orderResult": "FILLED",
        "orderTime": "1573458248511",
        "orderType": "LIMIT",
        "orderVolume": "118.48",
        "pendingVolume": "0",
        "side": "BUY",
        "status": "EXECUTED",
        "symbol": "ETHUSDT",
        "timeInForce": "GTC"
      }
    ],
    "total": 5632
  },
  "result": "SUCCESS",
  "type": "API"
}
```

### get CP history list of executed orders
GET  /api/v1/trades/cpExecutionHistory

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request params***

| NAME     | REQUIRED | TYPE    | DESCRIPTION | DEFAULT | VALUES RANGE |
| :------- | :------- | :------ | :------- | :------ | :----------- |
| cp       |          | string   | counter party name    | -       |              |
| symbol   |          | string   | crypto pair name | -       |              |
| from     | Y        | integer  | time started | -       |              |
| to       | Y        | integer  | time ended | -       |              |
| pageNo   |          | integer  | page NO     | -       |              |

***返回参数***

| NAME           | TYPE       | DESCRIPTION       |
| :------------- | :--------- | :------------- |
| pageCount      | integer    | counts of pages         |
| pageNo         | integer    | page NO           |
| pageSize       | integer    | page size       |
| total          | integer    | total amount         |
| executeId      | integer    | executed ID       |
| cp             | string     | couter party name          |
| symbol         | string     | crypto pair name    |
| cpOrderId      | integer    | CP order ID         |
| side           | string     | [BUY or SELL(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side) |
| executePrice   | number     | executed Price           |
| executeVolume  | number     | executed Volume       |
| executeAmount  | number     | executed Amount       |
| executeTime    | integer       | executed Time       |
| commission     | number     | commission         |
| executeComment | string     | comment           |

***example of return***

```
{
  "data": {
    "pageCount": 620,
    "pageNo": 1,
    "pageSize": 100,
    "records": [
      {
        "commission": "0",
        "commissionCurrency": "ETH",
        "cp": "chaoex",
        "cpOrderId": "1022439221994129429",
        "executeAmount": "1086.53999",
        "executeComment": "CommissionAsset: 3",
        "executeId": "1022439268458825749",
        "executePrice": "192.07",
        "executeTime": "1573458255000",
        "executeVolume": "5.657",
        "orderType": "LIMIT",
        "side": "BUY",
        "symbol": "ETHUSDT"
      },
      {
        "commission": "0",
        "commissionCurrency": "ETH",
        "cp": "chaoex",
        "cpOrderId": "1022439221994129429",
        "executeAmount": "170.71136",
        "executeComment": "CommissionAsset: 3",
        "executeId": "1022439268458825237",
        "executePrice": "188.84",
        "executeTime": "1573458255000",
        "executeVolume": "0.904",
        "orderType": "LIMIT",
        "side": "BUY",
        "symbol": "ETHUSDT"
      }
    ],
    "total": 61984
  },
  "result": "SUCCESS",
  "type": "API"
} 

```
### get account info
GET /api/v1/market/cpAccountInfo

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request params***

| NAME     | REQUIRED | TYPE   | DESCRIPTION | DEFAULT | VALUES RANGE |
| :------- | :------- | :----- | :------- | :------ | :----------- |
| cp      | Y        | string | Cp Name    |         |          |
| accountId  | Y     | string | Account ID(Counterparty-accounts-CP Account-ID)    |         |          |

***params of return***

| NAME  | TYPE   | DESCRIPTION |
| :---- | :----- | :------- |
| cp     | string | counter party name    |
| tradeable | boolean | tradeable    |
| currency | string | currency    |
| free | number | free to use amount    |
| frozen | number | frozen amount    |

***example of return***

```

{
  "data": {
    "balance": [
      {
        "currency": "USDT",
        "free": "123.561222104000005444",
        "frozen": "123.561222104000005444"
      },
      {
        "currency": "BTC",
        "free": "0.009982505720491022",
        "frozen": "0.009982505720491022"
      },
      {
        "currency": "ETH",
        "free": "0.0499",
        "frozen": "0.0499"
      },
      {
        "currency": "BCH",
        "free": "0",
        "frozen": "0"
      },
      {
        "currency": "XRP",
        "free": "0",
        "frozen": "0"
      },
      {
        "currency": "LTC",
        "free": "0",
        "frozen": "0"
      },
      {
        "currency": "ETC",
        "free": "0",
        "frozen": "0"
      },
      {
        "currency": "EOS",
        "free": "0",
        "frozen": "0"
      },
      {
        "currency": "XLM",
        "free": "0",
        "frozen": "0"
      },
      {
        "currency": "BCD",
        "free": "0",
        "frozen": "0"
      }
    ],
    "cp": "cp_xx",
    "tradeable": true
  },
  "result": "SUCCESS",
  "type": "API"
} 
```

***params of return***

| NAME                 | TYPE       | DESCRIBE                                                                         |
| :------------------- | :--------- | :------------------------------------------------------------------------------- |   
| result               | string     | 'SUCCESS' or 'INVALID_DATA' or 'REJECTED' or 'STALE_VERSION' or 'INTERNAL_ERROR' |
| type                 | string     | 'API'                                                                            |


***example of return***

###### Execute success：
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

###### Parameter validate fail：
```
{
  "result": "INVALID_DATA",
  "type": "API",
  "validations": {
    "basic.cp": "INVALID"
  }
}
```

###### Target config not exist, reject update：
```
{
  "result": "REJECTED",
  "type": "API"
}
```

###### Version does not match：
```
{
  "result": "STALE_VERSION",
  "type": "API"
}
```

###### Executing error：
```
{
  "result": "INTERNAL_ERROR",
  "type": "API"
}
```


### get-making-config
GET /api/v1/making/makingConfigs

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***

| NAME     | REQUIRED | TYPE   | DESCRIBE          | DEFAULT | VALUES RANGE |
| :------- | :------- | :----- | :---------------- | :------ | :----------- |
| cp       |          | string | CP (Abbreviation) |         |              |
| symbol   |          | string | Symbol            |         |              |

***params of return***

| NAME                          | TYPE        | DESCRIBE                  |
| :---------------------------- | :---------- | :------------------------ |
| cp                            |string       |cp|
| symbol                        |string       |Symbol|
| frequency                     |integer      |Frequency|
| expiration                    |number       |Expiration|
| volumeBoundaryDeviation       |number       |Volume Boundary|
| volumeRoundRatio              |number       |Volume Round Ratio|
| volumeMinScale                |integer      |Volume Min Scale|
| volumeMaxScale                |integer      |Volume Max Scale|
| tickSize                      |number       |Tick Size|
| restriction                   |string       |[Restriction](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingRestriction)|
| l1ConfigType                  |string       |[ConfigT Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingOrderType)|
| l1ConfigVersion               |integer       |Config Version|
| l1OrderActive                 |string       |L1 Order|
| l1LadderDepth                 |string       |Depth|
| l1StepHeight                  |number       |Step Height|
| l1PriceLimit                  |number       |Price Limit|
| l1SpreadType                  |string       |Spread Type|
| l1SpreadValue                 |number       |Min Spread|
| l1SpreadPercentage            |number       |Min Spread %|
| tradeActive                   |string       |Trades|
| tradeStrict                   |string       |Avoid Order Match|
| tradeMidDeviation             |number       |Mid Deviation|
| tradeMaxVolume                |number       |Max Volume|
| tradeMinVolume                |number       |Min Volume|
| tradeSendRatio                |number       |Send Ratio|
| tradeVolumeMultiplier         |number       |Volume Multiplier|
| tradeAnnexableSize            |number       |Force Trade Volume|
| l2LadderDepth                 |string       |Depth|
| l2PriceLimit                  |number       |Price Limit|
| l2Benchmark                   |string       |[Benchmark](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingDiffType)|
| l2BenchmarkType               |string       |[Benchmark Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#PriceAdjustType)|
| l2DifferenceValue             |number       |Difference Value|
| l2DifferencePercentage        |number       |Difference Percentage|
| l2ThresholdValue              |number       |Threshold Value|
| l2ThresholdPercentage         |number       |Threshold Percentage|
| l2BidMinVolume                |number       |Bid Min Volume|
| l2BidMaxVolume                |number       |Bid Max Volume|
| l2AskMinVolume                |number       |Ask Min Volume|
| l2AskMaxVolume                |number       |Ask Max Volume|
| l2OrderActive                 |bool       |L2 Order|

| simulateType                  |string       |[Simulate Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |string       |Target Time|
| targetPrice                   |number       |Target Price|
| startTime                     |string       |Start Time|
| startPrice                    |number       |Start Price|
| volatility                    |number       |Volatility/s|
| pressureVolume                |number       |Pressure Volume|
| concessionRatio               |number       |Concession Ratio|
| releasePrice                  |number       |Release Price|
| minPrice                      |number       |Min Price|
| maxPrice                      |number       |Max Price|


***example of return***

```
{
  "data": [
    {
      "cp": "xxx_mm",
      "expiration": "2222",
      "frequency": 30,
      "l1ConfigType": "TARGET_PRICE",
      "l1ConfigVersion": 2,
      "l1LadderDepth": 5,
      "l1OrderActive": true,
      "l1PriceLimit": "0.33",
      "l1SpreadPercentage": "0",
      "l1SpreadType": "VALUE",
      "l1SpreadValue": "0.05",
      "l1StepHeight": "0.07",
      "l2AskMaxVolume": "2",
      "l2AskMinVolume": "0.01",
      "l2Benchmark": "BOB",
      "l2BenchmarkType": "VALUE",
      "l2BidMaxVolume": "2",
      "l2BidMinVolume": "0.01",
      "l2DifferenceValue": "90",
      "l2LadderDepth": 30,
      "l2OrderActive": true,
      "l2PriceLimit": "0.08",
      "l2ThresholdValue": "30",
      "restriction": "NORMAL",
      "symbol": "BTCUSDT",
      "targetPriceConfig": {
        "askMaxVolume": "1",
        "askMinVolume": "1",
        "bidMaxVolume": "1",
        "bidMinVolume": "1",
        "concessionRatio": "1",
        "cp": "cp_xx",
        "forceApplyPrice": false,
        "maxPrice": "22",
        "minPrice": "1",
        "pressureVolume": "1",
        "releasePrice": "1",
        "simulateType": "DEFAULT",
        "startPrice": "1",
        "startTime": "1637727382000",
        "symbol": "BTCUSDT",
        "targetPrice": "1",
        "targetTime": "1637986582000",
        "volatility": "1"
      },
      "tickSize": "0.01",
      "tradeActive": true,
      "tradeAnnexableSize": "3",
      "tradeMaxVolume": "0.3",
      "tradeMidDeviation": "23",
      "tradeMinVolume": "0.07",
      "tradeSendRatio": "100",
      "tradeStrict": true,
      "tradeVolumeMultiplier": "500",
      "volumeBoundaryDeviation": "0.1",
      "volumeMaxScale": 4,
      "volumeMinScale": 3,
      "volumeRoundRatio": "0.5"
    },
    {
      "cp": "xxx_mm",
      "expiration": "2222",
      "frequency": 30,
      "l1ConfigType": "TARGET_PRICE",
      "l1ConfigVersion": 3,
      "l1LadderDepth": 5,
      "l1OrderActive": true,
      "l1PriceLimit": "0.0033",
      "l1SpreadPercentage": "0.001",
      "l1SpreadType": "VALUE",
      "l1SpreadValue": "0.05",
      "l1StepHeight": "0.07",
      "l2AskMaxVolume": "2",
      "l2AskMinVolume": "0.01",
      "l2Benchmark": "BOB",
      "l2BenchmarkType": "VALUE",
      "l2BidMaxVolume": "2",
      "l2BidMinVolume": "0.01",
      "l2DifferenceValue": "90",
      "l2LadderDepth": 30,
      "l2OrderActive": true,
      "l2PriceLimit": "0.0008",
      "l2ThresholdValue": "30",
      "restriction": "NORMAL",
      "symbol": "ETHUSDT",
      "targetPriceConfig": {
        "askMaxVolume": "1",
        "askMinVolume": "1",
        "bidMaxVolume": "1",
        "bidMinVolume": "1",
        "concessionRatio": "1",
        "cp": "cp_xx",
        "forceApplyPrice": false,
        "maxPrice": "22",
        "minPrice": "1",
        "pressureVolume": "1",
        "releasePrice": "1",
        "simulateType": "DEFAULT",
        "startPrice": "1",
        "startTime": "1637727382000",
        "symbol": "ETHUSDT",
        "targetPrice": "1",
        "targetTime": "1637986582000",
        "volatility": "1"
      },
      "tickSize": "0.01",
      "tradeActive": true,
      "tradeAnnexableSize": "3",
      "tradeMaxVolume": "0.3",
      "tradeMidDeviation": "0.23",
      "tradeMinVolume": "0.07",
      "tradeSendRatio": "100",
      "tradeStrict": true,
      "tradeVolumeMultiplier": "500",
      "volumeBoundaryDeviation": "0.001",
      "volumeMaxScale": 4,
      "volumeMinScale": 3,
      "volumeRoundRatio": "0.5"
    }
  ],
  "type": "DATA"
}
```


### add-making-config
POST /api/v1/making/makingConfigs/create

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***
| NAME                          |REQUIRED     | TYPE        | DESCRIBE                  |
| :---------------------------- | :---------- | :---------- | :------------------------ |
| cp                            |     Y       |string       |cp|
| symbol                        |     Y       |string       |Symbol|
| frequency                     |     Y       |integer      |Frequency|
| expiration                    |     Y       |number       |Expiration|
| volumeBoundaryDeviation       |     Y       |number       |Volume Boundary|
| volumeRoundRatio              |             |number       |Volume Round Ratio|
| volumeMinScale                |     Y       |integer      |Volume Min Scale|
| volumeMaxScale                |     Y       |integer      |Volume Max Scale|
| tickSize                      |     Y       |number       |Tick Size|
| restriction                   |     Y       |string       |[Restriction](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingRestriction)|
| l1ConfigType                  |     Y       |string       |[ConfigT Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingOrderType)|
| l1ConfigVersion               |     Y       |integer       |Config Version|
| l1OrderActive                 |     Y       |string       |L1 Order|
| l1LadderDepth                 |     Y       |string       |Depth|
| l1StepHeight                  |     Y       |number       |Step Height|
| l1PriceLimit                  |     Y       |number       |Price Limit|
| l1SpreadType                  |     Y       |string       |Spread Type|
| l1SpreadValue                 |             |number       |Min Spread|
| l1SpreadPercentage            |             |number       |Min Spread %|
| tradeActive                   |    Y        |string       |Trades|
| tradeStrict                   |    Y        |string       |Avoid Order Match|
| tradeMidDeviation             |    Y        |number       |Mid Deviation|
| tradeMaxVolume                |    Y        |number       |Max Volume|
| tradeMinVolume                |    Y        |number       |Min Volume|
| tradeSendRatio                |    Y        |number       |Send Ratio|
| tradeVolumeMultiplier         |    Y        |number       |Volume Multiplier|
| tradeAnnexableSize            |    Y        |number       |Force Trade Volume|
| l2LadderDepth                 |    Y        |string       |Depth|
| l2PriceLimit                  |    Y        |number       |Price Limit|
| l2Benchmark                   |    Y        |string       |[Benchmark](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingDiffType)|
| l2BenchmarkType               |    Y        |string       |[Benchmark Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#PriceAdjustType)|
| l2DifferenceValue             |             |number       |Difference Value|
| l2DifferencePercentage        |             |number       |Difference Percentage|
| l2ThresholdValue              |             |number       |Threshold Value|
| l2ThresholdPercentage         |             |number       |Threshold Percentage|
| l2BidMinVolume                |    Y        |number       |Bid Min Volume|
| l2BidMaxVolume                |    Y        |number       |Bid Max Volume|
| l2AskMinVolume                |    Y        |number       |Ask Min Volume|
| l2AskMaxVolume                |    Y        |number       |Ask Max Volume|
| l2OrderActive                 |             |bool         |L2 Order|
 
***example of request***

```
{
  "cp": "cp_xx",
  "expiration": "2222",
  "frequency": 30,
  "l1ConfigType": "TARGET_PRICE",
  "l1ConfigVersion": 2,
  "l1LadderDepth": 5,
  "l1OrderActive": true,
  "l1PriceLimit": "0.33",
  "l1SpreadType": "VALUE",
  "l1SpreadValuåe": "0.05",
  "l1StepHeight": "0.07",
  "l2AskMaxVolume": "2",
  "l2AskMinVolume": "0.01",
  "l2Benchmark": "BOB",
  "l2BenchmarkType": "VALUE",
  "l2BidMaxVolume": "2",
  "l2BidMinVolume": "0.01",
  "l2DifferenceValue": "90",
  "l2LadderDepth": 30,
  "l2OrderActive": true,
  "l2PriceLimit": "0.08",
  "l2ThresholdValue": "30",
  "restriction": "NORMAL",
  "symbol": "ETHUSDT",
  "tickSize": "0.01",
  "tradeActive": true,
  "tradeAnnexableSize": "3",
  "tradeMaxVolume": "0.3",
  "tradeMidDeviation": "23",
  "tradeMinVolume": "0.07",
  "tradeSendRatio": "100",
  "tradeStrict": true,
  "tradeVolumeMultiplier": "500",
  "volumeBoundaryDeviation": "0.1",
  "volumeMaxScale": 4,
  "volumeMinScale": 3,
  "volumeRoundRatio": "0.5"
}
```

***params of return***

| NAME                 | TYPE       | DESCRIBE                                                      |
| :------------------- | :--------- | :------------------------------------------------------------ |   
| result               | string     | 'SUCCESS' or 'INVALID_DATA' or 'REJECTED' or 'INTERNAL_ERROR' |
| type                 | string     | 'API'                                                         |

***example of return***

###### Execute success：
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

###### Parameter validate fail：
```
{
  "result": "INVALID_DATA",
  "type": "API",
  "validations": {
    "basic.cp": "INVALID"
  }
}
```

###### Target config already exist, reject duplicate create：
```
{
  "result": "REJECTED",
  "type": "API"
}
```

###### Executing error：
```
{
  "result": "INTERNAL_ERROR",
  "type": "API"
}
```



### update-making-config
PUT /api/v1/making/makingConfigs/update

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***

| NAME                          |REQUIRED     | TYPE        | DESCRIBE                  |
| :---------------------------- | :---------- | :---------- | :------------------------ |
| cp                            |     Y       |string       |cp|
| symbol                        |     Y       |string       |Symbol|
| frequency                     |     Y       |integer      |Frequency|
| expiration                    |     Y       |number       |Expiration|
| volumeBoundaryDeviation       |     Y       |number       |Volume Boundary|
| volumeRoundRatio              |             |number       |Volume Round Ratio|
| volumeMinScale                |     Y       |integer      |Volume Min Scale|
| volumeMaxScale                |     Y       |integer      |Volume Max Scale|
| tickSize                      |     Y       |number       |Tick Size|
| restriction                   |     Y       |string       |[Restriction](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingRestriction)|
| l1ConfigType                  |     Y       |string       |[ConfigT Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingOrderType)|
| l1ConfigVersion               |     Y       |integer       |Config Version|
| l1OrderActive                 |     Y       |string       |L1 Order|
| l1LadderDepth                 |     Y       |string       |Depth|
| l1StepHeight                  |     Y       |number       |Step Height|
| l1PriceLimit                  |     Y       |number       |Price Limit|
| l1SpreadType                  |     Y       |string       |Spread Type|
| l1SpreadValue                 |             |number       |Min Spread|
| l1SpreadPercentage            |             |number       |Min Spread %|
| tradeActive                   |    Y        |string       |Trades|
| tradeStrict                   |    Y        |string       |Avoid Order Match|
| tradeMidDeviation             |    Y        |number       |Mid Deviation|
| tradeMaxVolume                |    Y        |number       |Max Volume|
| tradeMinVolume                |    Y        |number       |Min Volume|
| tradeSendRatio                |    Y        |number       |Send Ratio|
| tradeVolumeMultiplier         |    Y        |number       |Volume Multiplier|
| tradeAnnexableSize            |    Y        |number       |Force Trade Volume|
| l2LadderDepth                 |    Y        |string       |Depth|
| l2PriceLimit                  |    Y        |number       |Price Limit|
| l2Benchmark                   |    Y        |string       |[Benchmark](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingDiffType)|
| l2BenchmarkType               |    Y        |string       |[Benchmark Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#PriceAdjustType)|
| l2DifferenceValue             |             |number       |Difference Value|
| l2DifferencePercentage        |             |number       |Difference Percentage|
| l2ThresholdValue              |             |number       |Threshold Value|
| l2ThresholdPercentage         |             |number       |Threshold Percentage|
| l2BidMinVolume                |    Y        |number       |Bid Min Volume|
| l2BidMaxVolume                |    Y        |number       |Bid Max Volume|
| l2AskMinVolume                |    Y        |number       |Ask Min Volume|
| l2AskMaxVolume                |    Y        |number       |Ask Max Volume|
| l2OrderActive                 |             |bool         |L2 Order|


***example of request***
```
{
  "cp": "cp_xx",
  "expiration": "2222",
  "frequency": 30,
  "l1ConfigType": "TARGET_PRICE",
  "l1ConfigVersion": 2,
  "l1LadderDepth": 5,
  "l1OrderActive": true,
  "l1PriceLimit": "0.33",
  "l1SpreadType": "VALUE",
  "l1SpreadValue": "0.05",
  "l1StepHeight": "0.07",
  "l2AskMaxVolume": "2",
  "l2AskMinVolume": "0.01",
  "l2Benchmark": "BOB",
  "l2BenchmarkType": "VALUE",
  "l2BidMaxVolume": "2",
  "l2BidMinVolume": "0.01",
  "l2DifferenceValue": "90",
  "l2LadderDepth": 30,
  "l2OrderActive": true,
  "l2PriceLimit": "0.08",
  "l2ThresholdValue": "30",
  "restriction": "NORMAL",
  "symbol": "ETHUSDT",
  "tickSize": "0.01",
  "tradeActive": true,
  "tradeAnnexableSize": "3",
  "tradeMaxVolume": "0.3",
  "tradeMidDeviation": "23",
  "tradeMinVolume": "0.07",
  "tradeSendRatio": "100",
  "tradeStrict": true,
  "tradeVolumeMultiplier": "500",
  "volumeBoundaryDeviation": "0.1",
  "volumeMaxScale": 4,
  "volumeMinScale": 3,
  "volumeRoundRatio": "0.5"
}
```

***params of return***

| NAME                 | TYPE       | DESCRIBE                                                                         |
| :------------------- | :--------- | :------------------------------------------------------------------------------- |   
| result               | string     | 'SUCCESS' or 'INVALID_DATA' or 'REJECTED' or 'STALE_VERSION' or 'INTERNAL_ERROR' |
| type                 | string     | 'API'                                                                            |


***example of return***

###### Execute success：
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

###### Parameter validate fail：
```
{
  "result": "INVALID_DATA",
  "type": "API",
  "validations": {
    "basic.cp": "INVALID"
  }
}
```

###### Target config not exist, reject update：
```
{
  "result": "REJECTED",
  "type": "API"
}
```

###### Version does not match：
```
{
  "result": "STALE_VERSION",
  "type": "API"
}
```

###### Executing error：
```
{
  "result": "INTERNAL_ERROR",
  "type": "API"
}
```



### delete-making-config
DELETE /api/v1/making/makingConfigs/delete

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***

| NAME                 | REQUIRED | TYPE       | DESCRIBE          | DEFAULT | VALUES RANGE |
| :------------------- | :------- | :--------- | :---------------- | :------ | :----------- |
| cp                   | Y        |string      |  CP               |         |  Must be included in cp                                |
| symbol               | Y        |string      |  Symbol           |         |                                                        | 


***params of return***

| NAME                 | TYPE       | DESCRIBE                                                                          |
| :------------------- | :--------- | :-------------------------------------------------------------------------------- |   
| result               | string     |  'SUCCESS' or 'REJECTED' or 'UNAUTHORIZED' or 'STALE_VERSION' or 'INTERNAL_ERROR' |
| type                 | string     | 'API'                                                                             |


***example of return***

###### Execute success：
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

###### Target config not exist, reject delete：
```
{
  "result": "REJECTED",
  "type": "API"
}
```

###### No permission to delete：
```
{
  "result": "UNAUTHORIZED",
  "type": "API"
}
```

###### Version does not match：
```
{
  "result": "STALE_VERSION",
  "type": "API"
}
```

###### Executing error：
```
{
  "result": "INTERNAL_ERROR",
  "type": "API"
}
```



### get-target-price-config
GET /api/v1/making/targetPriceConfigs

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***

| NAME     | REQUIRED | TYPE   | DESCRIBE          | DEFAULT | VALUES RANGE |
| :------- | :------- | :----- | :---------------- | :------ | :----------- |
| symbol   |          | string | Symbol            |         |              |

***params of return***

| NAME                          | TYPE        | DESCRIBE                  |
| :---------------------------- | :---------- | :------------------------ |
| cp                            |string       |cp|
| symbol                        |string       |Symbol|
| simulateType                  |string       |[Simulate Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |number       |Target Time|
| targetPrice                   |number       |Target Price|
| startTime                     |number       |Start Time|
| forceApplyPrice               |bool         |Force Apply Price|
| volatility                    |number       |Volatility|
| pressureVolume                |number       |Pressure Volume|
| concessionRatio               |number       |Concession Ratio|
| releasePrice                  |number       |Release Price|
| bidMinVolume                  |number       |Bid Min Volume|
| bidMaxVolume                  |number       |Bid Max Volume|
| askMinVolume                  |number       |Ask Min Volume|
| askMaxVolume                  |number       |Ask Max Volume|
| minPrice                      |number       |Min Price|
| maxPrice                      |number       |Max Price|


***example of return***

```
{
  "data": [
    {
      "askMaxVolume": "1",
      "askMinVolume": "1",
      "bidMaxVolume": "1",
      "bidMinVolume": "1",
      "concessionRatio": "1",
      "cp": "xxxx",
      "forceApplyPrice": false,
      "maxPrice": "22",
      "minPrice": "1",
      "pressureVolume": "1",
      "releasePrice": "1",
      "simulateType": "DEFAULT",
      "startPrice": "1",
      "startTime": "1637727382000",
      "symbol": "BTCUSDT",
      "targetPrice": "1",
      "targetTime": "1637986582000",
      "volatility": "1"
    }
  ],
  "type": "DATA"
}
```


### add-target-price-config
POST /api/v1/making/targetPriceConfigs/create

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***
| NAME                          | REQUIRED     | TYPE        | DESCRIBE                  |
| :---------------------------- | :----------  | :---------- | :------------------------ |
| cp                            |     Y        |string       |cp|
| symbol                        |     Y        |string       |Symbol|
| simulateType                  |     Y        |string       |[Simulate Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |     Y        |number       |Target Time|
| targetPrice                   |     Y        |number       |Target Price|
| startTime                     |              |number       |Start Time|
| forceApplyPrice               |     Y        |bool         |Force Apply Price|
| volatility                    |     Y        |number       |Volatility|
| pressureVolume                |     Y        |number       |Pressure Volume|
| concessionRatio               |     Y        |number       |Concession Ratio|
| releasePrice                  |     Y        |number       |Release Price|
| bidMinVolume                  |     Y        |number       |Bid Min Volume|
| bidMaxVolume                  |     Y        |number       |Bid Max Volume|
| askMinVolume                  |     Y        |number       |Ask Min Volume|
| askMaxVolume                  |     Y        |number       |Ask Max Volume|
| minPrice                      |              |number       |Min Price|
| maxPrice                      |              |number       |Max Price|


***example of request***

```
{
  "askMaxVolume": "1",
  "askMinVolume": "1",
  "bidMaxVolume": "1",
  "bidMinVolume": "1",
  "concessionRatio": "1",
  "cp": "xxx_mm",
  "forceApplyPrice": false,
  "maxPrice": "22",
  "minPrice": "1",
  "pressureVolume": "1",
  "releasePrice": "1",
  "simulateType": "DEFAULT",
  "startPrice": "1",
  "startTime": "1637727382000",
  "symbol": "BTCUSDT",
  "targetPrice": "1",
  "targetTime": "1637986582000",
  "volatility": "1"
}
```

***params of return***

| NAME                 | TYPE       | DESCRIBE                                                      |
| :------------------- | :--------- | :------------------------------------------------------------ |   
| result               | string     | 'SUCCESS' or 'INVALID_DATA' or 'REJECTED' or 'INTERNAL_ERROR' |
| type                 | string     | 'API'                                                         |

***example of return***

###### Execute success：
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

###### Parameter validate fail：
```
{
  "result": "INVALID_DATA",
  "type": "API",
  "validations": {
    "basic.cp": "INVALID"
  }
}
```

###### Target config already exist, reject duplicate create：
```
{
  "result": "REJECTED",
  "type": "API"
}
```

###### Executing error：
```
{
  "result": "INTERNAL_ERROR",
  "type": "API"
}
```



### update-target-price-config

###### PUT /api/v1/making/targetPriceConfigs/update

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***

| NAME                          | REQUIRED     | TYPE        | DESCRIBE                  |
| :---------------------------- | :----------  | :---------- | :------------------------ |
| cp                            |     Y        |string       |cp|
| symbol                        |     Y        |string       |Symbol|
| simulateType                  |     Y        |string       |[Simulate Type](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |     Y        |number       |Target Time|
| targetPrice                   |     Y        |number       |Target Price|
| startTime                     |              |number       |Start Time|
| forceApplyPrice               |     Y        |bool         |Force Apply Price|
| volatility                    |     Y        |number       |Volatility|
| pressureVolume                |     Y        |number       |Pressure Volume|
| concessionRatio               |     Y        |number       |Concession Ratio|
| releasePrice                  |     Y        |number       |Release Price|
| bidMinVolume                  |     Y        |number       |Bid Min Volume|
| bidMaxVolume                  |     Y        |number       |Bid Max Volume|
| askMinVolume                  |     Y        |number       |Ask Min Volume|
| askMaxVolume                  |     Y        |number       |Ask Max Volume|
| minPrice                      |              |number       |Min Price|
| maxPrice                      |              |number       |Max Price|


***example of request***
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

***params of return***

| NAME                 | TYPE       | DESCRIBE                                                                         |
| :------------------- | :--------- | :------------------------------------------------------------------------------- |   
| result               | string     | 'SUCCESS' or 'INVALID_DATA' or 'REJECTED' or 'STALE_VERSION' or 'INTERNAL_ERROR' |
| type                 | string     | 'API'                                                                            |


***example of return***

###### Execute success：
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

###### Parameter validate fail：
```
{
  "result": "INVALID_DATA",
  "type": "API",
  "validations": {
    "basic.cp": "INVALID"
  }
}
```

###### Target config not exist, reject update：
```
{
  "result": "REJECTED",
  "type": "API"
}
```

###### Version does not match：
```
{
  "result": "STALE_VERSION",
  "type": "API"
}
```

###### Executing error：
```
{
  "result": "INTERNAL_ERROR",
  "type": "API"
}
```



### delete-target-price-config
DELETE /api/v1/making/targetPriceConfigs/delete

request with Headers:

X-API-TOKEN:token（acquired from login）

***request params***

| NAME                 | REQUIRED | TYPE       | DESCRIBE          | DEFAULT | VALUES RANGE |
| :------------------- | :------- | :--------- | :---------------- | :------ | :----------- |
| cp                   | Y        |string      |  CP               |         |  Must be included in cp                                |
| symbol               | Y        |string      |  Symbol           |         |                                                        | 


***params of return***

| NAME                 | TYPE       | DESCRIBE                                                                          |
| :------------------- | :--------- | :-------------------------------------------------------------------------------- |   
| result               | string     |  'SUCCESS' or 'REJECTED' or 'UNAUTHORIZED' or 'STALE_VERSION' or 'INTERNAL_ERROR' |
| type                 | string     | 'API'                                                                             |


***example of return***

###### Execute success：
```
{
  "result": "SUCCESS",
  "type": "API"
}
```

###### Target config not exist, reject delete：
```
{
  "result": "REJECTED",
  "type": "API"
}
```

###### No permission to delete：
```
{
  "result": "UNAUTHORIZED",
  "type": "API"
}
```

###### Version does not match：
```
{
  "result": "STALE_VERSION",
  "type": "API"
}
```

###### Executing error：
```
{
  "result": "INTERNAL_ERROR",
  "type": "API"
}
```