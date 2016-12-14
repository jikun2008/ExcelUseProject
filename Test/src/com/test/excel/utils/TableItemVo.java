package com.test.excel.utils;

/**
 * Student
 * 
 * @author Hongten
 * @created 2014-5-18
 */
public class TableItemVo {
    /**
     * 索赔单编号 
     */
    private String claimNumber;//索赔单编号
    /**
     * 索赔单类型
     */
    private String claimType;//索赔单类型
    /**
     * 服务站编号
     */
    private String serviceStationNumber;//服务站编号
    /**
     * 单据状态
     */
    private String documentStatus;//单据状态
    /**
     * 是否结算
     */
    private String settlement;//是否结算

    /**
     * 旧件信息
     */
    private String oldPieceInfor;//旧件信息
    
    
    /**
     * 服务科审核意见
     */
    private String auditOpinion;//服务科审核意见
    
    
    /**
     * 回访意见
     */
    private String returnVisit;// 回访意见
    
    
    /**
     * 供应商意见
     */
     private String supplierOpinion;//供应商意见
     
     /**
      * 责任供应商编号
      */
     private String supplierNumber;//责任供应商编号
     
     
     /**
      * 处理意见或措施
      */
     private String treatmentOpinions;//处理意见或措施
     
     /**
      * Vin码
      */
     private String vinCode;//Vin码
     
     /**
      * 联系人
      */
     private String contactsName;//联系人
     
     
     /**
      * 联系人电话
      */
     private String contactsNumber;//联系人电话
     
     
     /**
      * 服务里程
      */
     private String serviceMileage;//服务里程
     
     /**
      * 工时费
      */
     private String workingHoursMoney;//工时费
     
     
     /**
      * 材料费
      */
     private String materialCostMoney;//材料费
     
     /**
      * 维修人员姓名
      */
     private String  repairPeopleName;//维修人员姓名
     
     
     /**
      * 外出维修地点
      */
     private String  repairAdress;//外出维修地点
     
     
     
     /**
      * 服务站名称
      */
     private String  serviceStationName;//服务站名称
     
     
     
     /**
      * 产品系列
      */
     private String  productSeries;//产品系列
     
     
     /**
      * 产品大类
      */
     private String productCategories;//产品大类
     
     
     /**
      * 车型
      */
     private String carModels;//车型
     
     
     /**
      * 销售时间
      */
     private String salestime;//销售时间
     
     
     /**
      * 用户姓名
      */
     private String userName;//用户姓名
     
     
     /**
      * 用户电话
      */
     private String userPhone;//用户电话
     
     
     /**
      * 客户移动电话
      */
     private String userMovePhone;//客户移动电话
     
     
     /**
      *是否已经回访
      */
     private String isreturnVisit;//是否已经回访
     
     /**
      *故障件
      */
     private String faultComponent;//故障件
     
     /**
      * 故障件编号
      */
     private String faultComponentNumber;//故障件编号
     
     
     /**
      * 外出费用
      */
     private String outCostMoney;//外出费用
     
     /**
      * 跨区作业费
      */
     private String crossOperatingMoney;//跨区作业费
     
     
     /**
      * 费用合计
      */
     private String totalCostMoney;//费用合计
     
     
     /**
      * 外购件金额合计
      */
     private String totalAmountPurchasedPartsMoney;//外购件金额合计
     
     
     /**
      * 其他索赔费用
      */
     private String otherClaimsCostsMoney;//其他索赔费用
     
     /**
      * 其他索赔费用原因
      */
     private String therClaimsCostsReason;//其他索赔费用原因
     
     
     /**
      * 申请单编号
      */
     private String applicationFormNumber;//申请单编号
     
     
     /**
      * 申请单类型
      */
     private String applicationFormType;//申请单类型
     
     
     /**
      * 故障原因编码
      */
     private String faultCauseCode;//故障原因编码
     
     
     /**
      * 现场检查情况描述
      */
     private String descriptionOfSiteInspection;//现场检查情况描述
     
     /**
      * 维修日期
      */
     private String repairDate;//维修日期
     
     
     /**
      * 创建人名称
      */
     private String createPeopleName;//创建人名称
     
     
     /**
      * 创建时间
      */
     private String createDate;//创建时间
     
     
     /**
      * 修改人名称
      */
     private String modifierPeopleName;//修改人名称
     
     
     /**
      * 修改时间
      */
     private String modificationDate;//修改时间
     
     
     /**
      * 提交人名称
      */
     private String submissionPeopleName;//提交人名称
     
     
     
     /**
      * 提交日期
      */
     private String submissionDate;//提交日期
     
     
     /**
      * 备注
      */
     private String note;//备注
     
     
     /**
      * 品牌
      */
     private String brand;//品牌
     
     
      /**
       * 派工单号
       */
     private String sendJobNumber;//派工单号
     
     
     /**
      * 外出方式
      */
     private String wayOutType;//外出方式
     
     
     /**
      * 出厂日期
      */
     private String productionDate;//出厂日期 
     
     
     /**
      * 报修时间
      */
     private String reportRepairDate;//报修时间
     
     
     
     /**
      * 用户地址
      */
     private String useAddress;//用户地址
     
     
     /**
      * 主机编号
      */
     private String hostNumber;//主机编号
     
     
     /**
      * 产品代码
      */
     private String productCode;//产品代码
     
     
     /**
      * 购机经销商
      */
     private String purchaseDealer;//购机经销商
     
     
     /**
      * 发动机厂家
      */
     private String engineManufacturers;//发动机厂家
     
     
     /**
      * 发动机型号
      */
     private String engineModel;//发动机型号
     
     
     /**
      * 发动机编号
      */
     private String engineNumber;//发动机编号
     
     
     /**
      * 桥箱编号
      */
     private String bridgeBoxNumber;//桥箱编号
     
     
     /**
      * 维修人员联系电话
      */
     private String repairPersonPhoneNumer;//维修人员联系电话
     
     
     /**
      * 产品满意度
      */
     private String productSatisfaction;//产品满意度
     
     
     /**
      * 服务满意度
      */
     private String serviceSatisfaction;//服务满意度
     
     
     /**
      * 不满意原因
      */
     private String notSatisfactoryReason;//不满意原因
     
     /**
      * 材料费差异
      */
     private String materialCostVariance;//材料费差异
}