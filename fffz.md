# ContractLineController


---
## 自营业务-新增线路

### BASIC

**Path：** /business/contractLine/add

**Method：** POST

**Desc：**

自营业务-新增线路

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| contractId | integer |  | 
| lineName | string |  | 
| lineType | integer | 路线类型 1联运 2公路 3铁路 4水路 | 
| siteMap | object |  | 
| &ensp;&ensp;&#124;─0 | object |  | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteId | integer | 站点id | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteName | string | 站点名 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─lineDType | integer | 路线类型 2公路 3铁路 4水路 0无 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteOrder | integer | 站点顺序 从 1 开始 1表示始发站 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─priceTax | number | 成本价 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─price | number | 运费单价 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─ifTaxTransport | integer | 委托承运 1是 0否 | 
| createdBy | integer | 创建人 | 
| createdName | string | 创建人名 | 
| createdTime | string | 创建时间 | 

**Request Demo：**

```json
{
  "contractId": 0,
  "lineName": "",
  "lineType": 0,
  "siteMap": {
    "0": {
      "siteId": 0,
      "siteName": "",
      "lineDType": 0,
      "siteOrder": 0,
      "priceTax": 0.0,
      "price": 0.0,
      "ifTaxTransport": 0
    }
  },
  "createdBy": 0,
  "createdName": "",
  "createdTime": ""
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | string |  | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": ""
}
```



---
## updateContractLine

### BASIC

**Path：** /business/contractLine/update

**Method：** PUT

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| contractLineId | integer |  | 
| lineName | string |  | 
| siteMap | object |  | 
| &ensp;&ensp;&#124;─0 | object |  | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteId | integer | 站点id | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteName | string | 站点名 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─lineDType | integer | 路线类型 2公路 3铁路 4水路 0无 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteOrder | integer | 站点顺序 从 1 开始 1表示始发站 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─priceTax | number | 成本价 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─price | number | 运费单价 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─ifTaxTransport | integer | 委托承运 1是 0否 | 
| updatedBy | integer | 创建人 | 
| updatedName | string | 创建人名 | 
| updatedTime | string | 创建时间 | 

**Request Demo：**

```json
{
  "contractLineId": 0,
  "lineName": "",
  "siteMap": {
    "0": {
      "siteId": 0,
      "siteName": "",
      "lineDType": 0,
      "siteOrder": 0,
      "priceTax": 0.0,
      "price": 0.0,
      "ifTaxTransport": 0
    }
  },
  "updatedBy": 0,
  "updatedName": "",
  "updatedTime": ""
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | string |  | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": ""
}
```



---
## 根据线路ID查询线路详情

### BASIC

**Path：** /business/contractLine/findContractLineById/{contractLineId}

**Method：** GET

**Desc：**

根据线路ID查询线路详情

### REQUEST


**Path：**

| name  |  value   | desc  |
| ------------ | ------------ | ------------ |
| contractLineId |  |  |


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | object |  | 
| &ensp;&ensp;&#124;─startPlate | string | 起始地 | 
| &ensp;&ensp;&#124;─endPlate | string | 目的地 | 
| &ensp;&ensp;&#124;─lineName | string | 线路名称 | 
| &ensp;&ensp;&#124;─contractLineId | integer | 线路ID | 
| &ensp;&ensp;&#124;─contractId | integer | 合同ID | 
| &ensp;&ensp;&#124;─priceTax | number | 合同运价 | 
| &ensp;&ensp;&#124;─dVoList | array |  | 
| &ensp;&ensp;&ensp;&ensp;&#124;─ | object |  | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─contractLineDId | integer | 线路站点ID | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─preSiteId | integer | 上游站点id | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─preSiteName | string | 上游站点名 | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─siteId | integer | 站点id | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─siteName | string | 站点名 | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─priceTax | number | 成本价 | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─price | number | 运费单价 | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ifTaxTransport | integer | 委托承运 1是 0否 | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─contractLineDType | integer | 路线类型 2公路 3铁路 4水路 0无 | 
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ifSePoint | integer | 站点类型 0开始站点 1结束站点 2中间站点 | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": {
    "startPlate": "",
    "endPlate": "",
    "lineName": "",
    "contractLineId": 0,
    "contractId": 0,
    "priceTax": 0.0,
    "dVoList": [
      {
        "contractLineDId": 0,
        "preSiteId": 0,
        "preSiteName": "",
        "siteId": 0,
        "siteName": "",
        "priceTax": 0.0,
        "price": 0.0,
        "ifTaxTransport": 0,
        "contractLineDType": 0,
        "ifSePoint": 0
      }
    ]
  }
}
```



---
## 启用禁用线路

### BASIC

**Path：** /business/contractLine/enableDisable

**Method：** POST

**Desc：**

启用禁用线路

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| contractLineId | integer |  | 
| valStatus | integer | 状态 0代表新增 1代表生效 2代表终止 | 
| updatedBy | integer |  | 
| updatedName | string |  | 
| updatedTime | string |  | 

**Request Demo：**

```json
{
  "contractLineId": 0,
  "valStatus": 0,
  "updatedBy": 0,
  "updatedName": "",
  "updatedTime": ""
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | boolean |  | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": false
}
```



---
## deleteContractLineByContractLineId

### BASIC

**Path：** /business/contractLine/deleteContractLineByContractLineId/{contractLineId}

**Method：** DELETE

### REQUEST


**Path：**

| name  |  value   | desc  |
| ------------ | ------------ | ------------ |
| contractLineId |  |  |

**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/x-www-form-urlencoded | YES |  |


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | string |  | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": ""
}
```



---
## 根据合同ID查询线路列表

### BASIC

**Path：** /business/contractLine/findContractLinesByContractId/{contractId}

**Method：** GET

**Desc：**

根据合同ID查询线路列表

### REQUEST


**Path：**

| name  |  value   | desc  |
| ------------ | ------------ | ------------ |
| contractId |  |  |


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | array |  | 
| &ensp;&ensp;&#124;─ | object |  | 
| &ensp;&ensp;&ensp;&ensp;&#124;─lineName | string | 线路名称 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─contractLineId | integer | 线路ID | 
| &ensp;&ensp;&ensp;&ensp;&#124;─lineInfo | string | 线路详情 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─totWeight | number | 已发单量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─pubWeight | number | 可发单量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─carWeight | number | 总装车量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─disWeight | number | 总收货量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─valStatus | integer | 状态 0代表新增 1代表生效 2代表终止 | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": [
    {
      "lineName": "",
      "contractLineId": 0,
      "lineInfo": "",
      "totWeight": 0.0,
      "pubWeight": 0.0,
      "carWeight": 0.0,
      "disWeight": 0.0,
      "valStatus": 0
    }
  ]
}
```



---
## 根据合同ID、站点查询可发单量

### BASIC

**Path：** /business/contractLine/findContractSiteStock

**Method：** POST

**Desc：**

根据合同ID、站点查询可发单量

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| contractId | integer | 合同ID | 
| siteId | integer | 站点ID | 

**Request Demo：**

```json
{
  "contractId": 0,
  "siteId": 0
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | number |  | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": 0.0
}
```



---
## 合同线路段下拉框

### BASIC

**Path：** /business/contractLine/findContractSites

**Method：** POST

**Desc：**

合同线路段下拉框

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| contractId | integer | 合同ID | 
| siteId | integer | 站点ID | 

**Request Demo：**

```json
{
  "contractId": 0,
  "siteId": 0
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | array |  | 
| &ensp;&ensp;&#124;─ | object |  | 
| &ensp;&ensp;&ensp;&ensp;&#124;─contractLineSiteName | string | 线路站点段名 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─contractSiteType | integer | 路线类型 2公路 3铁路 4水路 0无 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─contractSiteTypeName | string | 路线类型名 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─contractLineDId | integer | 合同站点明细主键ID | 
| &ensp;&ensp;&ensp;&ensp;&#124;─contractId | integer | 合同号ID | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": [
    {
      "contractLineSiteName": "",
      "contractSiteType": 0,
      "contractSiteTypeName": "",
      "contractLineDId": 0,
      "contractId": 0
    }
  ]
}
```



---
## 公路、铁路、水运 根据合同查询线路始发站、终点站信息

### BASIC

**Path：** /business/contractLine/findContractLineD

**Method：** POST

**Desc：**

公路、铁路、水运 根据合同查询线路始发站、终点站信息

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| contractId | integer | 合同id | 
| contractLineType | integer | 线路类型 1联运 2公路 3铁路 4水路 | 

**Request Demo：**

```json
{
  "contractId": 0,
  "contractLineType": 0
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | object |  | 
| &ensp;&ensp;&#124;─contractLineDId | integer | 主键 | 
| &ensp;&ensp;&#124;─contractLineId | integer | 合同路线id | 
| &ensp;&ensp;&#124;─contractId | integer | 合同id | 
| &ensp;&ensp;&#124;─preSiteId | integer | 上游站点id | 
| &ensp;&ensp;&#124;─preSiteName | string | 上游站点名 | 
| &ensp;&ensp;&#124;─siteId | integer | 站点id | 
| &ensp;&ensp;&#124;─siteName | string | 站点名 | 
| &ensp;&ensp;&#124;─priceTax | number | 成本价 | 
| &ensp;&ensp;&#124;─price | number | 运费单价 | 
| &ensp;&ensp;&#124;─ifTaxTransport | integer | 委托承运 1是 0否 | 
| &ensp;&ensp;&#124;─contractLineDType | integer | 路线类型 2公路 3铁路 4水路 0无 | 
| &ensp;&ensp;&#124;─ifSePoint | integer | 站点类型 0开始站点 1结束站点 2中间站点 | 
| &ensp;&ensp;&#124;─totWeight | number | 总发单量 | 
| &ensp;&ensp;&#124;─totWeightUpdateTime | string | 总发单量更新时间 | 
| &ensp;&ensp;&#124;─pubWeight | number | 可发单量 | 
| &ensp;&ensp;&#124;─pubWeightUpdateTime | string | 可发单量更新时间 | 
| &ensp;&ensp;&#124;─carWeight | number | 总装车量 | 
| &ensp;&ensp;&#124;─carWeightUpdateTime | string | 总装车量更新时间 | 
| &ensp;&ensp;&#124;─disWeight | number | 总收货量 | 
| &ensp;&ensp;&#124;─disWeightUpdateTime | string | 总收货量更新时间 | 
| &ensp;&ensp;&#124;─ifComplete | integer | 运输完成标记 0未完成 1完成 | 
| &ensp;&ensp;&#124;─revision | integer | 乐观锁 | 
| &ensp;&ensp;&#124;─createdBy | integer | 创建人 | 
| &ensp;&ensp;&#124;─createdName | string | 创建人名 | 
| &ensp;&ensp;&#124;─createdTime | string | 创建时间 | 
| &ensp;&ensp;&#124;─updatedBy | integer | 更新人 | 
| &ensp;&ensp;&#124;─updatedName | string | 更新人名 | 
| &ensp;&ensp;&#124;─updatedTime | string | 更新时间 | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": {
    "contractLineDId": 0,
    "contractLineId": 0,
    "contractId": 0,
    "preSiteId": 0,
    "preSiteName": "",
    "siteId": 0,
    "siteName": "",
    "priceTax": 0.0,
    "price": 0.0,
    "ifTaxTransport": 0,
    "contractLineDType": 0,
    "ifSePoint": 0,
    "totWeight": 0.0,
    "totWeightUpdateTime": "",
    "pubWeight": 0.0,
    "pubWeightUpdateTime": "",
    "carWeight": 0.0,
    "carWeightUpdateTime": "",
    "disWeight": 0.0,
    "disWeightUpdateTime": "",
    "ifComplete": 0,
    "revision": 0,
    "createdBy": 0,
    "createdName": "",
    "createdTime": "",
    "updatedBy": 0,
    "updatedName": "",
    "updatedTime": ""
  }
}
```



---
## addContractWeightLog

### BASIC

**Path：** /business/contractLine/addContractWeightLog

**Method：** POST

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| docuId | integer | 业务单据id | 
| docuNum | string | 业务单据号 | 
| contractId | integer | 合同id | 
| contractLineDId | integer | 合同线路明细id | 
| docuSource | integer | 单据来源 2公路 3铁路 4水路 | 
| docuType | integer | 操作类型 1装货 2收货 3装收货 4发单量变化 | 
| originZhWeight | number | 装货原始量 | 
| updateZhWeight | number | 装货更新量 | 
| originShWeight | number | 收货原始量 | 
| updateShWeight | number | 收货更新量 | 
| originFdWeight | number | 可发单量原始量 | 
| updateFdWeight | number | 可发单量更新量 | 

**Request Demo：**

```json
{
  "docuId": 0,
  "docuNum": "",
  "contractId": 0,
  "contractLineDId": 0,
  "docuSource": 0,
  "docuType": 0,
  "originZhWeight": 0.0,
  "updateZhWeight": 0.0,
  "originShWeight": 0.0,
  "updateShWeight": 0.0,
  "originFdWeight": 0.0,
  "updateFdWeight": 0.0
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | string |  | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": ""
}
```



---
## findSiteStocksByContractId

### BASIC

**Path：** /business/contractLine/findSiteStocksByContractId/{contractId}

**Method：** GET

### REQUEST


**Path：**

| name  |  value   | desc  |
| ------------ | ------------ | ------------ |
| contractId |  |  |


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | array |  | 
| &ensp;&ensp;&#124;─ | object |  | 
| &ensp;&ensp;&ensp;&ensp;&#124;─ifSePoint | integer | 站点类型 0开始站点 1结束站点 2中间站点 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteId | integer | 站点ID | 
| &ensp;&ensp;&ensp;&ensp;&#124;─siteName | string | 站点名 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─pubWeight | number | 可发单量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─totWeight | number | 已发单量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─disWeight | number | 总收货量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─carWeight | number | 总装车量 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─ifComplete | integer | 运输完成标记 0未完成 1完成 | 
| &ensp;&ensp;&ensp;&ensp;&#124;─contractId | integer | 合同ID | 

**Response Demo：**

```json
{
  "code": 0,
  "message": "",
  "data": [
    {
      "ifSePoint": 0,
      "siteId": 0,
      "siteName": "",
      "pubWeight": 0.0,
      "totWeight": 0.0,
      "disWeight": 0.0,
      "carWeight": 0.0,
      "ifComplete": 0,
      "contractId": 0
    }
  ]
}
```



---
## 自营合同站点-设置/取消运输完成标记

### BASIC

**Path：** /business/contractLine/lineSiteCompletionMark

**Method：** POST

**Desc：**

自营合同站点-设置/取消运输完成标记

### REQUEST


**Headers：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**RequestBody**

| name | type | desc |
| ------------ | ------------ | ------------ |
| contractId | integer | 合同id | 
| siteId | integer | 站点id | 
| ifFinish | integer | 是否运输完成(0：未完成，1：已完成) | 
| createdBy | integer | 创建人 | 
| createdName | string | 创建人名 | 
| createdTime | string | 创建时间 | 

**Request Demo：**

```json
{
  "contractId": 0,
  "siteId": 0,
  "ifFinish": 0,
  "createdBy": 0,
  "createdName": "",
  "createdTime": ""
}
```


### RESPONSE

**Header：**

| name  |  value  |  required  | desc  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |   |

**Body：**

| name | type | desc |
| ------------ | ------------ | ------------ |
| code | integer |  | 
| message | string |  | 
| data | object |  | 

**Response Demo：**

```json
{
  "code": 0,
  "message": ""
}
```


