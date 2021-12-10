# RestfulApi 接口信息(2019-01-29)

REST，即Representational State Transfer的缩写，是目前最流行的一种互联网软件架构。它结构清晰、符合标准、易于理解、扩展方便，正得到越来越多网站的采用。


- API访问地址
    测试环境：https://uat.smartcrypto.cc:444/     
- API测试工具
    测试环境：https://uat.smartcrypto.cc:444/swagger-ui.html 

## 接口列表
a
| 请求方法                                    | 类型 | 描述                   |
| :------------------------------------------ | :--- | :--------------------- |
| [/api/v1/operator/login](#登陆接口)         | POST | 登陆                   |
| [/api/v1/market/cps](#获取交易对手列表信息)       | GET  | 获取交易列表信息         |
| [/api/v1/market/symbols](#获取交易币对列表信息) | GET  | 获取交易币对列表信息     |
| [/api/v1/trades/place](#下单)                | POST | 投递交易               |
| [/api/v1/trades/orderDetail](#根据ID获取订单详情)        | GET  | 根据orderId/cpOrderId获取订单详情           |
| [/api/v1/trades/clientOrderDetail](#根据clientOrderId获取订单详情)        | GET  | 根据clientOrderId获取订单详情           |
| [/api/v1/trades/orderHistory](#获取订单历史列表信息)       | GET  | 获取订单历史列表信息   |
| [/api/v1/trades/cpOrderHistory](#获取CP订单历史列表信息)     | GET  | 获取CP订单历史列表信息 |
| [/api/v1/trades/cpExecutionHistory](#获取CP成交历史列表信息) | GET  | 获取CP成交历史列表信息 |
| [/api/v1/trades/cpExecutionsByCpOrderId](#获取CP订单成交详情)  | GET  | 获取CP成交详细         |
| [/api/v1/trades/cpExecutionsByOrderId](#获取CP订单成交详情)  | GET  | 获取CP成交详细         |
| [/api/v1/market/cpAccountInfo](#获取交易账户信息)  | GET  | 获取交易账户信息         |
| [/api/v1/making/makingConfigs](#获取做市配置)  | GET  | 获取做市配置         |
| [/api/v1/making/makingConfigs/create](#新增做市配置)  | POST  | 新增做市配置         |
| [/api/v1/making/makingConfigs/update](#修改做市配置)  | PUT  | 修改做市配置         |
| [/api/v1/making/makingConfigs/delete](#删除做市配置)  | DELETE  | 删除做市配置         |
| [/api/v1/making/targetPriceConfigs](#get-target-price-config)  | GET  | get |目标价格| config         |
| [/api/v1/making/targetPriceConfigs/create](#add-target-price-config)  | PUT  | add |目标价格| config         |
| [/api/v1/making/targetPriceConfigs/update](#update-target-price-config)  | POST  | update |目标价格| config         |
| [/api/v1/making/targetPriceConfigs/delete](#delete-target-price-config)  | DELETE  | delete |目标价格| config         |

## Restful API
### 登陆接口
POST /api/v1/operator/login

***请求参数***

| NAME     | REQUIRED | TYPE   | DESCRIBE | DEFAULT | VALUES RANGE |
| :------- | :------- | :----- | :------- | :------ | :----------- |
| loginId  | Y        | string | 用户名    |         |              |
| password | Y        | string | 密码      |         |              |

***返回参数***

| NAME  | TYPE   | DESCRIBE |
| :---- | :----- | :------- |
| token | string | token    |

***返回参数示例***

```
{
  "data": {
    "token": "ZElHYkFsRktuZDduUG1udzI2V1lldyIsImFsZyI6IkEyNTZHQ01LVyIsIml2IjoicjZzLU83TGlYTUFYVEVxeSJ9.92p5FlhYwx6YCLeUlh-rlE3o_8.-wL8D1uL3GEEE3kj.qmBbSsSy92nYBt4WOLbU5MCH1NPbXO6k6_b5uGEG63xcQJ-Ny9K1VCMRtChQRol6l6fb5rNobNnOFAgeYzy2cqkje4HgUlL3BSRNlDj7G6W-60MwM2af7U2xshESv8LqLux2GZxPzCBmJz__HgluDxJwX2qNsMvOjM5k7Ckce8E1vHRJA18pkMzAL41HMFyzaf67Mp2SgXIaFHXVKVxdsOI.Gi2YOwudddsfdsafdsafa"
  },
  "result": "SUCCESS",
  "type": "API"
}
```

### 获取交易对手列表信息
GET /api/v1/market/cps

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***返回参数***

| NAME           | TYPE    | DESCRIBE |
| :------------- | :------ | :------- |
| name           | string  | 名称 |
| tradingEnabled | boolean | 交易限制 |

***返回参数示例***

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

### 获取交易币对列表信息
GET  /api/v1/market/symbols

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***返回参数***

| NAME               | TYPE       | DESCRIBE     |
| :----------------- | :--------- | :----------- |
| name               | string     | 交易币对名称         |
| tradingMaxVolume   | number     | 交易最大数量 |
| tradingMinVolume   | number     | 交易最小数量 |
| tradingPriceScale  | integer    | 价格精度     |
| tradingVolumeScale | integer    | 交易量精度   |

***返回参数示例***

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

### 下单
POST /api/v1/trades/place

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***请求参数***

RequestBody:（order）

| NAME          | REQUIRED | TYPE       | DESCRIBE              | DEFAULT | VALUES RANGE |
| :------------ | :------- | :--------- | :-------------------- | :------ | :----------- |
| cps           |         | array      | 交易对手名称            |         |              |
| book          |          | string     | 账簿                    |         |              |
| symbol        | Y        | string     | 交易币对名称            |         |              |
| manualHedge   | Y        | bool       | 是否对冲仓位            |         |              |
| orderPrice    | Y        | number     | 订单价格               |         |              |
| slippage      |          | number     | 滑点                 |         |              |
| orderVolume   | Y        | number     | 订单数量               |         |              |
| side          | Y        | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)        |         |              |
| orderType     | Y        | string     | [订单类型(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |         |              |
| timeInForce   | Y        | string     | [过期类型(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |         |              |
| clientOrderId |          | string     | 客户端订单ID                    |         |              |

***请求参数示例***

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

 ***返回参数***

| NAME          | TYPE       | DESCRIBE              |
| :------------ | :--------- | :-------------------- |
| orderID       | integer    | 订单ID                |
| cpOrderId     | integer    | CP 订单ID                    |
| cp            | string     | 交易对手名称                |
| symbol        | string     | 交易币对名称              |
| executeAmount | number     | 成交金额              |
| executeVolume | number     | 成交数量              |
| orderVolume   | number     | 订单数量              |
| pendingVolume | number     | 未成交数量              |
| side          | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)        |
| orderType     | string     | [订单类型(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| status        | string     | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| timeInForce   | string     | [过期类型(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| orderResult   | string     | [投递结果类型(DealingResult)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderresult) |
| orderTime     | integer    | 订单时间              |
| executeTime   | integer    | 成交时间                  |
| clientOrderId | string     | 客户端订单ID                  |
| commission    | number     | 手续费         |

***返回参数示例***

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

***返回参数示例***

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

### 根据ID获取订单详情
GET /api/v1/trades/orderDetail

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***请求参数***

| NAME     | REQUIRED | TYPE | DESCRIBE | DEFAULT | VALUES RANGE |
| :------- | :------- | :--- | :------- | :------ | :----------- |
| orderId/cpOrderId | Y        | integer | 订单ID/Cp订单ID | -       |              |

***返回参数***

| NAME          | TYPE       | DESCRIBE              |
| :------------ | :--------- | :-------------------- |
| orderID       | integer    | 订单ID                |
| cpOrderId     | integer    | CP 订单ID                    |
| cp            | string     | 交易对手名称                |
| symbol        | string     | 交易币对名称              |
| executeAmount | number     | 成交金额              |
| executeVolume | number     | 成交数量              |
| orderVolume   | number     | 订单数量              |
| pendingVolume | number     | 未成交数量              |
| side          | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)        |
| orderType     | string     | [订单类型(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| status        | string     | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| timeInForce   | string     | [过期类型(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| orderTime     | integer    | 订单时间              |
| executeTime   | integer    | 成交时间              |
| clientOrderId | string     | 客户端订单ID              |

***返回参数示例***

```
{
  "data": {
    "order": {
      "cpOrders": [
        {
          "commission": "0",
          "cp": "okex",
          "cpOrderId": "1004925661049089045",
          "executeAmount": "3.50977992",
          "executeTime": "1571370474395",
          "executeVolume": "0.019951",
          "orderComment": "bands,1004925660201601581.0",
          "orderResult": "FILLED",
          "orderTime": "1571370474105",
          "orderType": "SLIPPAGE",
          "orderVolume": "0.019951",
          "pendingVolume": "0",
          "side": "BUY",
          "status": "EXECUTED",
          "symbol": "ETHUSDT",
          "timeInForce": "IOC"
        }
      ],
      "executeAmount": "3.50977992",
      "executeTime": "1571370474395",
      "executeVolume": "0.019951",
      "orderId": "1004925661040699925",
      "orderTime": "1571370474105",
      "orderVolume": "2",
      "pendingVolume": "1.980049",
      "symbol": "ETHUSDT"
    }
  },
  "result": "SUCCESS",
  "type": "API"
}
```

### 根据clientOrderId获取订单详情
GET /api/v1/trades/clientOrderDetail

request with Headers:    

X-API-TOKEN:token（acquired from login）

***request params***

| NAME          | REQUIRED | TYPE | DESCRIPTION | DEFAULT | VALUES RANGE |
| :------------ | :------- | :--- | :------- | :------ | :----------- |
| clientOrderId | Y        | string | 客户端订单ID | -       |              |

***params of return***

| NAME          | TYPE       | DESCRIBE              |
| :------------ | :--------- | :-------------------- |
| orderID       | integer    | 订单ID                |
| cpOrderId     | integer    | CP 订单ID                    |
| cp            | string     | 交易对手名称                |
| symbol        | string     | 交易币对名称              |
| executeAmount | number     | 成交金额              |
| executeVolume | number     | 成交数量              |
| orderVolume   | number     | 订单数量              |
| pendingVolume | number     | 未成交数量              |
| side          | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)        |
| orderType     | string     | [订单类型(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| status        | string     | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| timeInForce   | string     | [过期类型(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| orderTime     | integer    | 订单时间              |
| executeTime   | integer    | 成交时间              |
| clientOrderId | string     | 客户端订单ID              |

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

### 获取CP订单成交详情
GET  /api/v1/trades/cpExecutionsByCpOrderId
GET  /api/v1/trades/cpExecutionsByOrderId

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***请求参数***

| NAME    | REQUIRED | TYPE | DESCRIBE | DEFAULT | VALUES RANGE |
| :------ | :------- | :--- | :------- | :------ | :----------- |
| cpOrderId/orderId | Y        | integer | CP订单ID/订单ID   | -       |              |

***返回参数***

| NAME           | TYPE       | DESCRIBE       |
| :------------- | :--------- | :------------- |
| cp             | string     | 交易对手名称          |
| symbol         | string     | 交易币对名称       |
| side           | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side) |
| executeAmount  | number     | 成交金额       |
| executePrice   | number     | 成交价格       |
| cpExecutionid  | number     | CP约定ID       |
| executeVolume  | number     | 成交数量       |
| executeTime    | integer    | 成交时间       |
| commission     | number     | 手续费         |
| executeComment | string     | 成交备注       |

***返回参数示例***

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


 ### 获取订单历史列表信息
 GET  /api/v1/trades/orderHistory

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

 ***请求参数***

| NAME     | REQUIRED | TYPE    | DESCRIBE          | DEFAULT | VALUES RANGE |
| :------- | :------- | :------ | :---------------- | :------ | :----------- |
| cp       |          | string   | 交易对手名称             | -       |              |
| symbol   |          | string | 货币对名称          | -       |              |
| status   |          | string  | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus) | -       |              |
| from     | Y        | integer    | 开始时间          | -       |              |
| to       | Y        | integer    | 结束时间          | -       |              |
| pageNo   |          | integer | 页码              | -       |              |

 ***返回参数***

| NAME          | TYPE       | DESCRIBE              |
| :------------ | :--------- | :-------------------- |
| pageCount     | integer    | 总页数                |
| pageNo        | integer    | 页码                  |
| pageSize      | integer    | 每页数量              |
| total         | integer    | 总条数                |
| cpOrderId     | integer    | CP订单ID                    |
| cp            | string     | 交易对手名称              |
| symbol        | string     | 币对名称             |
| orderId       | integer    | 父订单ID              |
| side          | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)        |
| status        | string     | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| orderResult   | string     | [投递结果类型(DealingResult)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderresult)     |
| orderType     | string     | [订单类型(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| orderPrice    | number     | 订单价格              |
| executeAmount | number     | 成交金额              |
| orderVolume   | number     | 订单数量              |
| pendingVolume | number     | 未成交数量              |
| executeVolume | number     | 成交数量              |
| executeTime   | integer    | 成交时间              |
| orderTime     | integer    | 订单时间              |
| timeInForce   | string     | [过期类型(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| commission    | number     | 手续费                |
| rejectReason  | string     | 拒绝原因              |
| orderComment  | string     | 备注                  |

 ***返回参数示例***

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

### 获取CP订单历史列表信息
GET  /api/v1/trades/cpOrderHistory

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***请求参数***

| NAME     | REQUIRED | TYPE    | DESCRIBE          | DEFAULT | VALUES RANGE |
| :------- | :------- | :------ | :---------------- | :------ | :----------- |
| cp       |          | string  | 交易对手名称             | -       |              |
| symbol   |          | string  | 交易币对名称          | -       |              |
| status   |          | string  | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus) | -       |              |
| from     | Y        | integer    | 开始时间          | -       |              |
| to       | Y        | integer    | 结束时间          | -       |              |
| pageNo   |          | integer | 页码              | -       |              |

***返回参数***

| NAME          | TYPE       | DESCRIBE              |
| :------------ | :--------- | :-------------------- |
| pageCount     | integer    | 总页数                |
| pageNo        | integer    | 页码                  |
| pageSize      | integer    | 每页数量              |
| total         | integer    | 总条数                |
| cpOrderid     | integer    | CP OrderID                    |
| cp            | string     | 交易对手名称                |
| symbol        | string     | 交易币对名称              |
| orderId       | integer    | 父订单ID              |
| side          | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side)       |
| status        | string     | [状态(OrderStatus)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderstatus)     |
| orderType     | string     | [订单类型(OrderType)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#ordertype)   |
| orderResult   | string     | [投递结果类型(DealingResult)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#orderresult)     |
| executeAmount | number     | 成交金额              |
| orderPrice    | number     | 订单价格              |
| executeVolume | number     | 成交数量              |
| orderVolume   | number     | 订单数量              |
| pendingVolume | number     | 未成交数量              |
| orderTime     | integer    | 订单时间              |
| executeTime   | integer    | 成交时间              |
| timeInForce   | string     | [过期类型(TimeInForce)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#timeinforce) |
| commission    | number     | 手续费                |
| rejectReason  | string     | 拒绝原因              |
| orderComment  | string     | 备注                  |

***返回参数示例***

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

### 获取CP成交历史列表信息
GET  /api/v1/trades/cpExecutionHistory

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***请求参数***

| NAME     | REQUIRED | TYPE    | DESCRIBE | DEFAULT | VALUES RANGE |
| :------- | :------- | :------ | :------- | :------ | :----------- |
| cp       |          | string   | 交易对手名称    | -       |              |
| symbol   |          | string   | 交易币对名称 | -       |              |
| from     | Y        | integer  | 开始时间 | -       |              |
| to       | Y        | integer  | 结束时间 | -       |              |
| pageNo   |          | integer  | 页码     | -       |              |

***返回参数***

| NAME           | TYPE       | DESCRIBE       |
| :------------- | :--------- | :------------- |
| pageCount      | integer       | 总页数         |
| pageNo         | integer       | 页码           |
| pageSize       | integer       | 每页数量       |
| total          | integer       | 总条数         |
| executeId      | integer       | 成交ID       |
| cp             | string     | 交易对手名称          |
| symbol         | string     | 交易币对名称    |
| cpOrderId      | integer    | CP订单ID         |
| side           | string     | [买卖方向(Side)](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#side) |
| executePrice   | number     | 价格           |
| executeVolume  | number     | 成交数量       |
| executeAmount  | number     | 成交金额       |
| executeTime    | integer       | 成交时间       |
| commission     | number     | 手续费         |
| executeComment | string     | 备注           |

***返回参数示例***

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

### 获取交易账户信息
GET /api/v1/market/cpAccountInfo

请求时必须带Headers:    

X-API-TOKEN:token（通过login获得）

***请求参数***

| NAME     | REQUIRED | TYPE   | DESCRIBE | DEFAULT | VALUES RANGE |
| :------- | :------- | :----- | :------- | :------ | :----------- |
| cp      | Y        | string | 交易对手名称    |         |          |
| accountId  | Y     | string | 账户ID(后台管理-交易对手-账户-ID)    |         |          |
***返回参数***

| NAME  | TYPE   | DESCRIBE |
| :---- | :----- | :------- |
| cp     | string | 交易对手名称    |
| tradeable | boolean | 是否可交易    |
| currency | string | 币种    |
| free | number | 可用金额    |
| frozen | number | 冻结金额    |

***返回参数示例***

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
| cp                            |string       |交易对手|
| symbol                        |string       |币对|
| frequency                     |integer      |频率|
| expiration                    |number       |过期|
| volumeBoundaryDeviation       |number       |量界偏离|
| volumeRoundRatio              |number       |交易量舍入概率|
| volumeMinScale                |integer      |交易量最小精度|
| volumeMaxScale                |integer      |交易量最大精度|
| tickSize                      |number       |最小价格单位|
| restriction                   |string       |[限制](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingRestriction)|
| l1ConfigType                  |string       |[配置类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingOrderType)|
| l1ConfigVersion               |integer      |做市配置版本|
| l1OrderActive                 |string       |L1 订单|
| l1LadderDepth                 |string       |深度|
| l1StepHeight                  |number       |步长|
| l1PriceLimit                  |number       |价格限制|
| l1SpreadType                  |string       |点差类型|
| l1SpreadValue                 |number       |最小点差|
| l1SpreadPercentage            |number       |最小点差百分比|
| tradeActive                   |string       |成交|
| tradeStrict                   |string       |订单成交躲避|
| tradeMidDeviation             |number       |中值偏离|
| tradeMaxVolume                |number       |最大交易量|
| tradeMinVolume                |number       |最小交易量|
| tradeSendRatio                |number       |成交概率|
| tradeVolumeMultiplier         |number       |交易量乘数|
| tradeAnnexableSize            |number       |强制成交阀值|
| l2LadderDepth                 |string       |深度|
| l2PriceLimit                  |number       |价格限制|
| l2Benchmark                   |string       |[基准](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingDiffType)|
| l2BenchmarkType               |string       |[基准类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#PriceAdjustType)|
| l2DifferenceValue             |number       |差价|
| l2DifferencePercentage        |number       |差价百分比|
| l2ThresholdValue              |number       |阈值|
| l2ThresholdPercentage         |number       |阈值百分比|
| l2BidMinVolume                |number       |BID最小交易数量|
| l2BidMaxVolume                |number       |BID最大交易数量|
| l2AskMinVolume                |number       |ASK最小交易数量|
| l2AskMaxVolume                |number       |ASK最大交易数量|
| l2OrderActive                 |bool         |L2 订单|
| simulateType                  |string       |[模拟类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |string       |目标时间|
| targetPrice                   |number       |目标价格|
| startTime                     |string       |开始时间|
| startPrice                    |number       |开始价格|
| volatility                    |number       |波动/秒|
| pressureVolume                |number       |压力数量|
| concessionRatio               |number       |退让比例|
| releasePrice                  |number       |释放价格|
| minPrice                      |number       |最低价|
| maxPrice                      |number       |最高价|


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
| cp                            |     Y       |string       |交易对手|
| symbol                        |     Y       |string       |币对|
| frequency                     |     Y       |integer      |频率|
| expiration                    |     Y       |number       |过期|
| volumeBoundaryDeviation       |     Y       |number       |量界偏离|
| volumeRoundRatio              |             |number       |交易量舍入概率|
| volumeMinScale                |     Y       |integer      |交易量最小精度|
| volumeMaxScale                |     Y       |integer      |交易量最大精度|
| tickSize                      |     Y       |number       |最小价格单位|
| restriction                   |     Y       |string       |[限制](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingRestriction)|
| l1ConfigType                  |     Y       |string       |[配置类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingOrderType)|
| l1ConfigVersion               |     Y       |integer      |做市配置版本|
| l1OrderActive                 |     Y       |string       |L1 订单|
| l1LadderDepth                 |     Y       |string       |深度|
| l1StepHeight                  |     Y       |number       |步长|
| l1PriceLimit                  |     Y       |number       |价格限制|
| l1SpreadType                  |     Y       |string       |点差类型|
| l1SpreadValue                 |             |number       |最小点差|
| l1SpreadPercentage            |             |number       |最小点差百分比|
| tradeActive                   |    Y        |string       |成交|
| tradeStrict                   |    Y        |string       |订单成交躲避|
| tradeMidDeviation             |    Y        |number       |中值偏离|
| tradeMaxVolume                |    Y        |number       |最大交易量|
| tradeMinVolume                |    Y        |number       |最小交易量|
| tradeSendRatio                |    Y        |number       |成交概率|
| tradeVolumeMultiplier         |    Y        |number       |交易量乘数|
| tradeAnnexableSize            |    Y        |number       |强制成交阀值|
| l2LadderDepth                 |    Y        |string       |深度|
| l2PriceLimit                  |    Y        |number       |价格限制|
| l2Benchmark                   |    Y        |string       |基准|
| l2BenchmarkType               |    Y        |string       |[基准类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#PriceAdjustType)|
| l2DifferenceValue             |             |number       |差价|
| l2DifferencePercentage        |             |number       |差价百分比|
| l2ThresholdValue              |             |number       |阈值|
| l2ThresholdPercentage         |             |number       |阈值百分比|
| l2BidMinVolume                |    Y        |number       |BID最小交易数量|
| l2BidMaxVolume                |    Y        |number       |BID最大交易数量|
| l2AskMinVolume                |    Y        |number       |ASK最小交易数量|
| l2AskMaxVolume                |    Y        |number       |ASK最大交易数量|
| l2OrderActive                 |             |bool         |L2 订单|


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

| NAME                          | TYPE        | DESCRIBE                  |
| :---------------------------- | :---------- | :------------------------ |
| cp                            |     Y       |string       |交易对手|
| symbol                        |     Y       |string       |币对|
| frequency                     |     Y       |integer      |频率|
| expiration                    |     Y       |number       |过期|
| volumeBoundaryDeviation       |     Y       |number       |量界偏离|
| volumeRoundRatio              |             |number       |交易量舍入概率|
| volumeMinScale                |     Y       |integer      |交易量最小精度|
| volumeMaxScale                |     Y       |integer      |交易量最大精度|
| tickSize                      |     Y       |number       |最小价格单位|
| restriction                   |     Y       |string       |[限制](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingRestriction)|
| l1ConfigType                  |     Y       |string       |[配置类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#MakingOrderType)|
| l1ConfigVersion               |     Y       |integer      |做市配置版本|
| l1OrderActive                 |     Y       |string       |L1 订单|
| l1LadderDepth                 |     Y       |string       |深度|
| l1StepHeight                  |     Y       |number       |步长|
| l1PriceLimit                  |     Y       |number       |价格限制|
| l1SpreadType                  |     Y       |string       |点差类型|
| l1SpreadValue                 |             |number       |最小点差|
| l1SpreadPercentage            |             |number       |最小点差百分比|
| tradeActive                   |    Y        |string       |成交|
| tradeStrict                   |    Y        |string       |订单成交躲避|
| tradeMidDeviation             |    Y        |number       |中值偏离|
| tradeMaxVolume                |    Y        |number       |最大交易量|
| tradeMinVolume                |    Y        |number       |最小交易量|
| tradeSendRatio                |    Y        |number       |成交概率|
| tradeVolumeMultiplier         |    Y        |number       |交易量乘数|
| tradeAnnexableSize            |    Y        |number       |强制成交阀值|
| l2LadderDepth                 |    Y        |string       |深度|
| l2PriceLimit                  |    Y        |number       |价格限制|
| l2Benchmark                   |    Y        |string       |基准|
| l2BenchmarkType               |    Y        |string       |[基准类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#PriceAdjustType)|
| l2DifferenceValue             |             |number       |差价|
| l2DifferencePercentage        |             |number       |差价百分比|
| l2ThresholdValue              |             |number       |阈值|
| l2ThresholdPercentage         |             |number       |阈值百分比|
| l2BidMinVolume                |    Y        |number       |BID最小交易数量|
| l2BidMaxVolume                |    Y        |number       |BID最大交易数量|
| l2AskMinVolume                |    Y        |number       |ASK最小交易数量|
| l2AskMaxVolume                |    Y        |number       |ASK最大交易数量|
| l2OrderActive                 |             |bool         |L2 订单|


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
| symbol                        |string       |币对|
| simulateType                  |string       |[模拟类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |number       |目标时间|
| targetPrice                   |number       |目标价格|
| startTime                     |number       |开始时间|
| forceApplyPrice               |bool         |强制应用价格|
| volatility                    |number       |波动|
| pressureVolume                |number       |压力数量|
| concessionRatio               |number       |退让比例|
| releasePrice                  |number       |释放价格|
| bidMinVolume                  |number       |Bid最小数量|
| bidMaxVolume                  |number       |Bid最大数量|
| askMinVolume                  |number       |Ask最小数量|
| askMaxVolume                  |number       |Ask最大数量|
| minPrice                      |number       |最低价|
| maxPrice                      |number       |最高价|


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
| symbol                        |     Y        |string       |币对|
| simulateType                  |     Y        |string       |[模拟类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |     Y        |number       |目标时间|
| targetPrice                   |     Y        |number       |目标价格|
| startTime                     |              |number       |开始时间|
| forceApplyPrice               |     Y        |bool         |Force Apply Price|
| volatility                    |     Y        |number       |波动|
| pressureVolume                |     Y        |number       |压力数量|
| concessionRatio               |     Y        |number       |退让比例|
| releasePrice                  |     Y        |number       |释放价格|
| bidMinVolume                  |     Y        |number       |Bid最小数量|
| bidMaxVolume                  |     Y        |number       |Bid最大数量|
| askMinVolume                  |     Y        |number       |Ask最小数量|
| askMaxVolume                  |     Y        |number       |Ask最大数量|
| minPrice                      |              |number       |最低价|
| maxPrice                      |              |number       |最高价|


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
| symbol                        |     Y        |string       |币对|
| simulateType                  |     Y        |string       |[模拟类型](https://github.com/nexusbroc/nexus/blob/main/api-en/enum.md#SimulateType)|
| targetTime                    |     Y        |number       |目标时间|
| targetPrice                   |     Y        |number       |目标价格|
| startTime                     |              |number       |开始时间|
| forceApplyPrice               |     Y        |bool         |Force Apply Price|
| volatility                    |     Y        |number       |波动|
| pressureVolume                |     Y        |number       |压力数量|
| concessionRatio               |     Y        |number       |退让比例|
| releasePrice                  |     Y        |number       |释放价格|
| bidMinVolume                  |     Y        |number       |Bid最小数量|
| bidMaxVolume                  |     Y        |number       |Bid最大数量|
| askMinVolume                  |     Y        |number       |Ask最小数量|
| askMaxVolume                  |     Y        |number       |Ask最大数量|
| minPrice                      |              |number       |最低价|
| maxPrice                      |              |number       |最高价|


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