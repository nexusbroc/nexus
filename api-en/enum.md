# ENUM(2021-12-10)

##### OrderStatus:

```
[PENDING,EXCUTING,EXECUTED,EXPIRED,REJECTED,CANCELLING,CANCELLED,INTERRUPTED,UKNOWN]
```

##### Side:

```
[BUY,SELL]
```

##### TimeInForce:

```
[IOC,GTC]
```

##### OrderType:

```
[LIMIT,MARKET,SLIPPAGE]
```

##### OrderResult

```
[NONE,FILLED,PARTIAL]
```

##### DealingResult
```
[SUCCESS,TIMEOUT,REJECTED,RESTRICTED,STALE_VERSION,INVALID_REQUEST,INVALID_POSITION,INVALID_ORDER,INSUFFICIENT_LIQUIDITY,INSUFFICIENT_MARGIN,REDISPATCH,INTERNAL_ERROR]
```

##### PriceAdjustType
```
[VALUE,PERCENTAGE]
```

##### LpType
```
[LIQUIDITY_PROVIDER,LIQUIDITY_DEMANDER]
```

##### MakingOrderType
```
[SOURCE_LIQUIDITY,TARGET_PRICE,SYNTHETIC_INDEX]
```

##### MakingTickType
```
[TOB,MID]
```

##### PriceAdjustType
```
[VALUE,PERCENTAGE]
```

##### MakingRestriction
```
[NORMAL,OFF]
```

##### MakingDiffType
```
[MID,BOB]
```

##### SimulateType
```
[DEFAULT,ITO]
```