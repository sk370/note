# PD发布日志

## 2023-02-06

- 开发：安浩东
- 测试：安浩东
- 发布：朱宇琦
- 范围：
  - AT Definitions：OM_DailyReport
  - Event Sheets：OM_DailyReport
- 功能：
  - xxxx
  - xxxxx

## 2023-02-07

- 开发：安浩东
- 测试：安浩东
- 发布：朱宇琦
- 范围：
  - Forms：MES_OM_SliceVisualinAdd
  - Messages：MES_MAINFROM_MSG_TRX

## 2023-02-13

### 功能1

- 开发：朱宇琦
- 测试：朱宇琦
- 发布：朱宇琦
- 范围：
  - Forms：
    - MES_OM_DiamondLineManagement_Review
    - MES_OM_DiamondLineManagement_Judge
    - MES_OM_DiamondLineManagement_MultiJudge
    - MES_PM_Part_Dialog_spare
    - MES_PM_Return_Main
    - MES_PM_ReturnDetail_Dialog
  - Messages：MESChoiceListStrings
- 功能：
  - 钢线评审功能：
    - 仅在线边、未加入退库清单钢线允许评审；
    - 评审结果为【退库】的钢线，修改长度时，同步更新Inventory和MaterialReqItem表，保证钢线长度各表一致。
  - 领料、退料功能：
    - 领料时指定钢线类型为金刚线，与退料时类型统一；
    - 退料单详情展示时，物料大类取parttype字段，而不是remark字段。

### 功能2

- 开发：安浩东
- 测试：张晓辉
- 发布：安浩东
- 范围：

## 2023-02-14

- 开发：程海涛
- 测试：朱宇琦
- 发布：朱宇琦
- 范围：
  - Forms：MES_OM_InsertCleaningInput_Add
- 功能：
    - 插片过站信息补录时，提示无切片过站信息，实际有。
